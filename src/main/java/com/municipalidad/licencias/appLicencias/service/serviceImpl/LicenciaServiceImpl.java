package com.municipalidad.licencias.appLicencias.service;

import com.municipalidad.licencias.appLicencias.dto.EmisionLicenciaDTO;
import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import com.municipalidad.licencias.appLicencias.modules.renovarlicencia.LicenciaRenovableRowDTO;
import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import static com.municipalidad.licencias.appLicencias.entities.ClaseLicencia.*;
import com.municipalidad.licencias.appLicencias.entities.Licencia;
import com.municipalidad.licencias.appLicencias.entities.Titular;
import com.municipalidad.licencias.appLicencias.entities.Usuario;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.mapper.LicenciaMapper;
import com.municipalidad.licencias.appLicencias.repository.LicenciaRepository;
import com.municipalidad.licencias.appLicencias.repository.TitularRepository;
import com.municipalidad.licencias.appLicencias.repository.UsuarioRepository;
import com.municipalidad.licencias.appLicencias.session.CurrentUserProvider;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
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
                       UsuarioRepository usuarioRepo,
                       CurrentUserProvider currentUserProvider) {
    this.titularRepository = titularRepo;
    this.licenciaRepository = licenciaRepo;
    this.licenciaMapper = licenciaMapper;
    this.currentUserProvider = currentUserProvider;
    }

    @Override
    @Transactional
    public LicenciaDTO emitirLicencia(EmisionLicenciaDTO emisionLicenciaDTO) {
        
        LocalDate hoy = LocalDate.now();
        Titular titular = titularRepository.findByDni(emisionLicenciaDTO.getDniTitular())
                .orElseThrow(() -> new RuntimeException("Titular no encontrado"));
        ClaseLicencia clase = emisionLicenciaDTO.getClase();
        String observaciones = emisionLicenciaDTO.getObservaciones();
        if (!puedeEmitirLicencia(titular, clase, hoy)) {
            throw new RuntimeException("No cumple los requisitos para emitir esta licencia");
        }
        Licencia licencia = new Licencia();
        licencia.setClaseLicencia(clase);
        licencia.setTitular(titular);
        licencia.setFechaEmision(hoy);
        int vigencia = calcularVigencia(titular);
        licencia.setFechaVencimiento(hoy.plusYears(vigencia));
        licencia.setObservaciones(observaciones);
        licencia.setUsuario(currentUserProvider.getOrThrow());
        Licencia guardada = licenciaRepository.save(licencia);
        return licenciaMapper.toDTO(guardada); 
    
    }
    
    @Override
    public boolean puedeEmitirLicencia(Titular titular, ClaseLicencia claseSolicitada, LocalDate hoy) {

        int edad = Period.between(titular.getFechaNacimiento(), hoy).getYears();
        return switch (claseSolicitada) {
            case A, B, F, G -> edad >= 17;
            case C, D, E -> {
                if (edad < 21) yield false;
                if (edad >= 65) yield false;
                if (titular.isTuvoLicenciaProfesional()) yield true;
                if (titular.getFechaLicenciaClaseB() == null) yield false;
                yield Period.between(titular.getFechaLicenciaClaseB(), hoy).getYears() >= 1;
            }
            default -> false;
        };
    
    }
    @Override
    public Licencia renovarLicencia(Licencia licencia, String observaciones) {
        Titular titular = licencia.getTitular();
        Licencia renovacion = new Licencia();
        renovacion.setClaseLicencia(licencia.getClaseLicencia());
        renovacion.setTitular(titular);
        renovacion.setObservaciones(observaciones);
        renovacion.setUsuario(currentUserProvider.getOrThrow());
        LocalDate hoy = LocalDate.now();
        int vigencia = calcularVigencia(titular);
        renovacion.setFechaEmision(hoy);
        renovacion.setFechaVencimiento(hoy.plusYears(vigencia));
        renovacion.setLicenciaAnterior(licencia);

        return licenciaRepository.save(renovacion);
    }
    @Override
    public boolean estaVigente(ClaseLicencia clase, Titular titular){
        return licenciaRepository.existsByClaseLicenciaAndTitularDni(clase, titular.getDni());
    }
    @Override
    public int calcularVigencia(Titular titular) {
                int edad = Period.between(titular.getFechaNacimiento(), LocalDate.now()).getYears();

        if (edad < 21) {
            boolean esPrimeraLicencia = licenciaRepository.findByTitularDni(titular.getDni()).isEmpty();
            return esPrimeraLicencia ? 1 : 3;
        }

        if (edad < 47) return 5;
        if (edad < 61) return 4;
        if (edad <= 70) return 3;
        return 1;
    }
    
    @Override
    public int calcularCosto(ClaseLicencia clase, boolean esCopia, int vigencia) {
        if (esCopia) return 50;

       
        switch (clase) {
            case A:
                return switch (vigencia) {
                    case 5 -> 48;
                    case 4 -> 38;
                    case 3 -> 33;
                    case 1 -> 28;
                    default -> 0;
                };
            case B:
                return switch (vigencia) {
                    case 5 -> 48;
                    case 4 -> 38;
                    case 3 -> 33;
                    case 1 -> 28;
                    default -> 0;
                };
            case C:
                return switch (vigencia) {
                    case 5 -> 55;
                    case 4 -> 43;
                    case 3 -> 38;
                    case 1 -> 31;
                    default -> 0;
                };
            case D:
                return switch (vigencia) {
                    case 5 -> 108;
                    case 4 -> 98;
                    case 3 -> 78;
                    case 1 -> 58;
                    default -> 0;
                };
            case E:
                return switch (vigencia) {
                    case 5 -> 67;
                    case 4 -> 52;
                    case 3 -> 47;
                    case 1 -> 37;
                    default -> 0;
                };
            case F:
                return switch (vigencia) {
                    case 5 -> 28;
                    case 4 -> 23;
                    case 3 -> 18;
                    case 1 -> 13;
                    default -> 0;
                };
            case G:
                return switch (vigencia) {
                    case 5 -> 48;
                    case 4 -> 38;
                    case 3 -> 33;
                    case 1 -> 28;
                    default -> 0;
                };
            default:
                return 0;
        }
    }
    @Override
    public List<LicenciaDTO> obtenerLicenciasExpiradas() {
    
        List<Licencia> licencias = licenciaRepository.findByFechaVencimientoLessThanEqual(LocalDate.now());
        return licencias.stream()
                        .map(licenciaMapper::toDTO)
                        .collect(Collectors.toList());
    
    }
    public List<LicenciaDTO> obtenerLicenciasPorTitular(Long dni) {
   
        List<Licencia> licencias = licenciaRepository.findByTitularDni(dni);
        return licencias.stream()
                        .map(licenciaMapper::toDTO)
                        .collect(Collectors.toList());
    
    }
    public List<LicenciaDTO> obtenerLicenciasVigentesFiltradas(String nombre, 
                                                               String apellido, 
                                                               String grupoSanguineo, 
                                                               String factorSanguineo, 
                                                               Boolean esDonante) {
        List<Licencia> licencias = licenciaRepository.findLicenciasVigentesFiltradas(
            nombre != null && !nombre.isBlank() ? nombre : null,
            apellido != null && !apellido.isBlank() ? apellido : null,
            grupoSanguineo != null && !grupoSanguineo.isBlank() ? grupoSanguineo : null,
            factorSanguineo != null && !factorSanguineo.isBlank() ? factorSanguineo : null,
            esDonante
        );
        return licencias.stream()
                        .map(licenciaMapper::toDTO)
                        .collect(Collectors.toList());
    }
    
    @Transactional
    @Override
    public List<LicenciaDTO> renovarLicencias(Long dniTitular, List<ClaseLicencia> clasesARenovar) {

        if (dniTitular == null) throw new ServiceException("DNI requerido.");
        if (clasesARenovar == null || clasesARenovar.isEmpty())
            throw new ServiceException("Seleccioná al menos una licencia para renovar.");

        Titular titular = titularRepository.findByDni(dniTitular)
            .orElseThrow(() -> new ServiceException("Titular no encontrado."));

        Usuario usuario = currentUserProvider.getOrThrow();
            

        List<Licencia> ultimas = licenciaRepository.findUltimasPorClaseByTitularDni(dniTitular);
        Map<ClaseLicencia, Licencia> ultimaPorClase = ultimas.stream()
            .collect(java.util.stream.Collectors.toMap(Licencia::getClaseLicencia, l -> l, (a,b) -> a));

        LocalDate hoy = LocalDate.now();
        int vigenciaAnios = calcularVigencia(titular);
        LocalDate vencimientoNuevo = hoy.plusYears(vigenciaAnios);

        List<Licencia> nuevas = new java.util.ArrayList<>();

        for (ClaseLicencia clase : clasesARenovar) {
            Licencia anterior = ultimaPorClase.get(clase);
            if (anterior == null) {
                throw new ServiceException("No existe licencia previa para la clase " + clase + ".");
            }

            Licencia nueva = Licencia.builder()
                .claseLicencia(clase)
                .fechaEmision(hoy)
                .fechaVencimiento(vencimientoNuevo)
                .observaciones("Renovación")
                .titular(titular)
                .usuario(usuario)
                .licenciaAnterior(anterior)
                .build();

            nuevas.add(nueva);
        }

        List<Licencia> guardadas = licenciaRepository.saveAll(nuevas);

        return guardadas.stream()
            .map(licenciaMapper::toDTO)
            .toList();
    }
    
    
    @Transactional(readOnly = true)
    @Override
    public List<LicenciaRenovableRowDTO> buscarUltimasPorClaseParaRenovar(Long dni) {

        if (dni == null) {
            throw new ServiceException("DNI requerido.");
        }

        List<Licencia> ultimas = licenciaRepository.findUltimasPorClaseByTitularDni(dni);
        LocalDate hoy = LocalDate.now();

        return ultimas.stream()
            .map(l -> LicenciaRenovableRowDTO.builder()
                .licenciaId(l.getId())
                .clase(l.getClaseLicencia())
                // informativo: NO filtra, solo muestra
                .vigente(l.getFechaVencimiento().isAfter(hoy))
                .fechaVencimiento(l.getFechaVencimiento())
                .renovar(false) // checkbox desmarcado por defecto
                .build())
            .collect(Collectors.toList());
    }
}





