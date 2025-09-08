package com.municipalidad.licencias.appLicencias.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.stereotype.Service;

import com.municipalidad.licencias.appLicencias.model.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.model.Licencia;
import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.model.Usuario;
import com.municipalidad.licencias.appLicencias.repository.LicenciaRepository;
import com.municipalidad.licencias.appLicencias.repository.TitularRepository;
import com.municipalidad.licencias.appLicencias.repository.UsuarioRepository;
import com.municipalidad.licencias.appLicencias.session.SessionInfo;

@Service
public class LicenciaService {

    private final TitularRepository titularRepository;
    private final LicenciaRepository licenciaRepository;
    private final UsuarioRepository usuarioRepository;
    private final SessionInfo sessionInfo;

    public LicenciaService(TitularRepository titularRepo,
                           LicenciaRepository licenciaRepo,
                           UsuarioRepository usuarioRepo,
                           SessionInfo sessionInfo) {
        this.titularRepository = titularRepo;
        this.licenciaRepository = licenciaRepo;
        this.usuarioRepository = usuarioRepo;
        this.sessionInfo = sessionInfo;
    }

    /**
     * 
     * @param dni  Numero de documento de un titular
     * @param claseSolicitada clase que solicita
     * @return true si se puede emitir una licencia de esa clase para ese titular
     */
    public boolean puedeEmitirLicencia(Long dni, ClaseLicencia claseSolicitada) {
        Titular titular = titularRepository.findById(dni)
                .orElseThrow(() -> new RuntimeException("Titular no encontrado"));

        int edad = Period.between(titular.getFechaNacimiento(), LocalDate.now()).getYears();

        if (edad < 17) return false;

        switch (claseSolicitada) {
            case A, B, F, G -> {
                return edad >= 17;
            }
            case C, D, E -> {
                if (edad < 21) return false;
                if (titular.isTuvoLicenciaProfesional()) return true;
                if (edad >= 65) return false;
                if (titular.getFechaLicenciaClaseB() == null) return false;
                return Period.between(titular.getFechaLicenciaClaseB(), LocalDate.now()).getYears() >= 1;
            }
            default -> {
                return false;
            }
        }
    }

    // Nuevos métodos: el usuario se toma de la sesión
    public Licencia emitirLicencia(Long dni, ClaseLicencia clase, String observaciones) {
        Titular titular = titularRepository.findById(dni)
                .orElseThrow(() -> new RuntimeException("Titular no encontrado"));

        if (!puedeEmitirLicencia(dni, clase)) {
            throw new RuntimeException("No cumple los requisitos para emitir esta licencia");
        }

        Usuario actor = usuarioActualOrThrow();

        Licencia licencia = new Licencia();
        licencia.setClaseLicencia(clase);
        licencia.setTitular(titular);
        licencia.setFechaEmision(LocalDate.now());
        licencia.setFechaVencimiento(calcularVigencia(licencia, titular), titular.getFechaNacimiento());
        licencia.setObservaciones(observaciones);
        licencia.setUsuario(actor);

        return licenciaRepository.save(licencia);
    }

    public Licencia emitirLicencia(Licencia licencia, String observaciones) {
        Licencia copia = licencia; 
        copia.setObservaciones(observaciones);
        copia.setFechaEmision(LocalDate.now());
        copia.setUsuario(usuarioActualOrThrow());
        return licenciaRepository.save(copia);
    }

    public Licencia renovarLicencia(Licencia licencia, String observaciones) {
        Titular titular = licencia.getTitular();

        Licencia renovacion = new Licencia();
        renovacion.setClaseLicencia(licencia.getClaseLicencia());
        renovacion.setTitular(titular);
        renovacion.setObservaciones(observaciones);
        renovacion.setUsuario(usuarioActualOrThrow());
        renovacion.setFechaEmision(LocalDate.now());
        renovacion.setFechaVencimiento(calcularVigencia(licencia, titular), titular.getFechaNacimiento());
        renovacion.setVersionAnterior(licencia);

        return licenciaRepository.save(renovacion);
    }

    public boolean estaVigente(ClaseLicencia clase, Titular titular){
        return licenciaRepository.existsByClaseLicenciaAndTitularDni(clase, titular.getDni());
    }

    public int calcularVigencia(Licencia licencia, Titular titular) {
        int edad = Period.between(titular.getFechaNacimiento(), LocalDate.now()).getYears();
        if (edad < 21) {
            if (licenciaRepository.findByTitularDni(titular.getDni()).isEmpty())
                return 1;
            else return 3;
        }
        if (edad >= 21 && edad < 47) return 5;
        if (edad >= 47 && edad < 61) return 4;
        if (edad >= 61 && edad <= 70) return 3;
        if (edad > 70) return 1;
        return 0;
    }

    public int calcularCosto(Licencia licencia, boolean esCopia) {
        if (esCopia) return 50;

        ClaseLicencia clase = licencia.getClaseLicencia();
        int vigencia = this.calcularVigencia(licencia, licencia.getTitular());
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


    public List<Licencia> obtenerLicenciasExpiradas(){
        return licenciaRepository.findByFechaVencimientoLessThanEqual(LocalDate.now());
    }

    public List<Licencia> obtenerLicenciasPorTitular(Long dni) {
        return licenciaRepository.findByTitularDni(dni);
    }

    
      /**
     * Obtener licencias vigentes aplicando filtros opcionales. (null = no filtrar)
     * @param nombre Filtro por nombre 
     * @param apellido Filtro por apellido 
     * @param grupoSanguineo Filtro por grupo sanguíneo 
     * @param factorSanguineo Filtro por factor sanguineo 
     * @param esDonante Filtro por donante 
     * @return Lista de licencias que cumplen los criterios
     */
    public List<Licencia> obtenerLicenciasVigentesFiltradas(String nombre, 
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
        );
    }

    /* ================= Helpers ================= */

    private Usuario usuarioActualOrThrow() {
        String nombre = sessionInfo.getNombreUsuarioActual();
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalStateException("No hay usuario autenticado en sesión.");
        }
        Usuario u = usuarioRepository.findByNombreUsuario(nombre)
                .orElseThrow(() -> new IllegalStateException("Usuario en sesión no encontrado: " + nombre));
        if (!u.isActivo()) {
            throw new IllegalStateException("El usuario en sesión está inactivo.");
        }
        return u;
    }
}