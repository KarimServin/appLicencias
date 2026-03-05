package com.municipalidad.licencias.appLicencias.service.serviceImpl;

import com.municipalidad.licencias.appLicencias.modules.emitirlicencia.EmisionLicenciaDTO;
import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import static com.municipalidad.licencias.appLicencias.entities.ClaseLicencia.*;
import com.municipalidad.licencias.appLicencias.entities.Licencia;
import com.municipalidad.licencias.appLicencias.entities.Titular;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.mapper.LicenciaMapper;
import com.municipalidad.licencias.appLicencias.repository.LicenciaRepository;
import com.municipalidad.licencias.appLicencias.repository.TitularRepository;
import com.municipalidad.licencias.appLicencias.service.LicenciaService;
import com.municipalidad.licencias.appLicencias.session.CurrentUserProvider;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LicenciaServiceImpl implements LicenciaService {

    private final TitularRepository titularRepository;
    private final LicenciaRepository licenciaRepository;
    private final LicenciaMapper licenciaMapper;
    private final CurrentUserProvider currentUserProvider;

    public LicenciaServiceImpl(TitularRepository titularRepo,
                               LicenciaRepository licenciaRepo,
                               LicenciaMapper licenciaMapper,
                               CurrentUserProvider currentUserProvider) {
        this.titularRepository = titularRepo;
        this.licenciaRepository = licenciaRepo;
        this.licenciaMapper = licenciaMapper;
        this.currentUserProvider = currentUserProvider;
    }

    @Override
    @Transactional
    public LicenciaDTO emitirLicencia(EmisionLicenciaDTO dto) {
        if (dto == null) throw new ServiceException("Datos requeridos.");
        if (dto.getDniTitular() == null) throw new ServiceException("DNI requerido.");
        if (dto.getClases() == null || dto.getClases().isEmpty())
            throw new ServiceException("Debe seleccionar al menos una clase.");

        LocalDate hoy = LocalDate.now();

        Titular titular = titularRepository.findByDni(dto.getDniTitular())
                .orElseThrow(() -> new ServiceException("Titular no encontrado."));

        Licencia licenciaAnterior = licenciaRepository
                .findTopByTitularDniOrderByFechaEmisionDesc(dto.getDniTitular())
                .orElse(null);

        if (!puedeEmitirLicencia(titular, dto.getClases(), hoy)) {
            throw new ServiceException("No cumple los requisitos para emitir las clases solicitadas.");
        }

        int vigencia = calcularVigencia(titular);
        
        desactivarLicenciasAnteriores(titular.getDni());

        Licencia nueva = Licencia.builder()
        .titular(titular)
        .usuario(currentUserProvider.getOrThrow())
        .fechaEmision(hoy)
        .fechaVencimiento(hoy.plusYears(vigencia))
        .licenciaAnterior(licenciaAnterior)
        .vigente(true)  // ← FALTABA ESTO
        .build();

        dto.getClases().forEach(nueva::addClase);

        Licencia guardada = licenciaRepository.save(nueva);
        return licenciaMapper.toDTO(guardada);
    }

    @Override
    public boolean puedeEmitirLicencia(Titular titular, Set<ClaseLicencia> clasesSolicitadas, LocalDate hoy) {
        for (ClaseLicencia clase : clasesSolicitadas) {
            if (!puedeEmitirClase(titular, clase, hoy)) return false;
        }
        return true;
    }

    private boolean puedeEmitirClase(Titular titular, ClaseLicencia claseSolicitada, LocalDate hoy) {
        int edad = Period.between(titular.getFechaNacimiento(), hoy).getYears();
        return switch (claseSolicitada) {
            case A, B, F, G -> edad >= 17;
            case C, D, E -> {
                if (edad < 21) yield false;
                if (edad >= 65) yield false;
                if (titular.isTuvoLicenciaProfesional()) yield true;
                Optional<LocalDate> fechaClaseB = licenciaRepository
                        .findFechaEmisionMasAntiguaByTitularDniAndClase(titular.getDni(), B);
                if (fechaClaseB.isEmpty()) yield false;
                yield Period.between(fechaClaseB.get(), hoy).getYears() >= 1;
            }
        };
    }

    @Override
    public boolean estaVigente(ClaseLicencia clase, Titular titular) {
        return licenciaRepository.existsVigenteByTitularDniAndClase(titular.getDni(), clase, LocalDate.now());
    }

    @Override
    @Transactional(readOnly = true)
    public int calcularVigencia(Long dni) {
        Titular titular = titularRepository.findByDni(dni)
                .orElseThrow(() -> new ServiceException("Titular no encontrado."));
        return calcularVigencia(titular);
    }
    
    @Override
    public int calcularVigencia(Titular titular) {
        int edad = Period.between(titular.getFechaNacimiento(), LocalDate.now()).getYears();
        if (edad < 21) {
            boolean esPrimeraLicencia = licenciaRepository.findByTitularDni(titular.getDni()).isEmpty();
            return esPrimeraLicencia ? 1 : 3;
        }
        if (edad <= 65) return 5;
        if (edad <= 70) return 3;
        return 1;
    }

    @Override
    public int calcularCosto(ClaseLicencia clase, boolean esCopia, int vigencia) {
        if (esCopia) return 50;
        return switch (clase) {
            case A, B, G -> switch (vigencia) { case 5 -> 48; case 4 -> 38; case 3 -> 33; case 1 -> 28; default -> 0; };
            case C       -> switch (vigencia) { case 5 -> 55; case 4 -> 43; case 3 -> 38; case 1 -> 31; default -> 0; };
            case D       -> switch (vigencia) { case 5 -> 108; case 4 -> 98; case 3 -> 78; case 1 -> 58; default -> 0; };
            case E       -> switch (vigencia) { case 5 -> 67; case 4 -> 52; case 3 -> 47; case 1 -> 37; default -> 0; };
            case F       -> switch (vigencia) { case 5 -> 28; case 4 -> 23; case 3 -> 18; case 1 -> 13; default -> 0; };
        };
    }

    @Override
    @Transactional(readOnly = true)
    public List<LicenciaDTO> obtenerLicenciasExpiradas() {
        return licenciaRepository.findExpiradasFetchClases(LocalDate.now())
                .stream().map(licenciaMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<LicenciaDTO> obtenerLicenciasPorTitular(Long dni) {
        return licenciaRepository.findByTitularDniFetchClases(dni)
                .stream().map(licenciaMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LicenciaDTO> obtenerUltimaLicencia(Long dni) {
        if (dni == null) throw new ServiceException("DNI requerido.");
        return licenciaRepository
                .findTopByTitularDniFetchClasesOrderByFechaEmisionDesc(dni)
                .map(licenciaMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LicenciaDTO> obtenerLicenciasVigentesFiltradas(String nombre,
                                                               String apellido,
                                                               String grupoSanguineo,
                                                               String factorSanguineo,
                                                               Boolean esDonante) {
        return licenciaRepository.findLicenciasVigentesFiltradas(
                nombre != null && !nombre.isBlank() ? nombre : null,
                apellido != null && !apellido.isBlank() ? apellido : null,
                grupoSanguineo != null && !grupoSanguineo.isBlank() ? grupoSanguineo : null,
                factorSanguineo != null && !factorSanguineo.isBlank() ? factorSanguineo : null,
                esDonante
        ).stream().map(licenciaMapper::toDTO).collect(Collectors.toList());
    }
    
        @Override
        @Transactional(readOnly = true)
        public LicenciaDTO obtenerCopiaLicenciaVigente(Long dni) {
            if (dni == null) throw new ServiceException("DNI requerido.");

            Licencia ultima = licenciaRepository
                    .findTopByTitularDniFetchClasesOrderByFechaEmisionDesc(dni)
                    .orElseThrow(() -> new ServiceException("No se encontró ninguna licencia para el DNI: " + dni));

            if (ultima.getFechaVencimiento().isBefore(LocalDate.now())) {
                throw new ServiceException("La última licencia del titular se encuentra vencida. No se puede emitir copia.");
            }

            return licenciaMapper.toDTO(ultima);
        }
        
        // Al emitir una nueva licencia
    private void desactivarLicenciasAnteriores(Long dniTitular) {
        List<Licencia> anteriores = licenciaRepository.findByTitularDniAndVigenteTrue(dniTitular);
        for (Licencia lic : anteriores) {
            lic.setVigente(false);
        }
        licenciaRepository.saveAll(anteriores);
    }
    
    @Override
    public List<LicenciaDTO> obtenerLicenciasPorIds(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) return Collections.emptyList();

        return licenciaRepository.findByIdInFetchAll(ids)
            .stream()
            .map(licenciaMapper::toDTO)
            .collect(Collectors.toList());
    }
}
    
    
    






