package com.municipalidad.licencias.appLicencias.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.municipalidad.licencias.appLicencias.model.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.model.Licencia;
import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.model.Usuario;
import com.municipalidad.licencias.appLicencias.repository.LicenciaRepository;
import com.municipalidad.licencias.appLicencias.repository.TitularRepository;

@Service
public class LicenciaService {

    private final TitularRepository titularRepo;
    private final LicenciaRepository licenciaRepo;

    public LicenciaService(TitularRepository titularRepo, LicenciaRepository licenciaRepo) {
        this.titularRepo = titularRepo;
        this.licenciaRepo = licenciaRepo;
    }

    public boolean puedeEmitirLicencia(Long dni, ClaseLicencia claseSolicitada) {
        Titular titular = titularRepo.findById(dni)
                .orElseThrow(() -> new RuntimeException("Titular no encontrado"));

        int edad = Period.between(titular.getFechaNacimiento(), LocalDate.now()).getYears();

        if (edad < 17) return false;
        if(edad > 90) return false;
        switch (claseSolicitada) {
            case A, B, F, G:
                return edad >= 17;
            case C, D, E:
                if (edad < 21) return false;
                if (titular.isTuvoLicenciaProfesional()) return true;
                if (edad >= 65) return false;
                if (titular.getFechaLicenciaClaseB() == null) return false;
                return Period.between(titular.getFechaLicenciaClaseB(), LocalDate.now()).getYears() >= 1;
            default:
                return false;
        }
    }

    public Licencia emitirLicencia(Long dni, ClaseLicencia clase, String observaciones, Usuario usuario) {
        Titular titular = titularRepo.findById(dni)
                .orElseThrow(() -> new RuntimeException("Titular no encontrado"));

        if (!puedeEmitirLicencia(dni, clase)) {
            throw new RuntimeException("No cumple los requisitos para emitir esta licencia");
        }

        Licencia licencia = new Licencia();
        licencia.setClaseLicencia(clase);
        licencia.setTitular(titular);
        licencia.setFechaEmision(LocalDate.now());
        licencia.setFechaVencimiento(calcularVigencia(licencia, titular), titular.getFechaNacimiento());
        licencia.setObservaciones(observaciones);
        licencia.setUsuario(usuario);

        return licenciaRepo.save(licencia);
    }
    
    public Licencia emitirLicencia(Licencia licencia,  String observaciones, Usuario usuario) {
        Licencia copiaLicencia = licencia;
        copiaLicencia.setObservaciones(observaciones);
        copiaLicencia.setFechaEmision(LocalDate.now());
        copiaLicencia.setUsuario(usuario);
        
        return licenciaRepo.save(copiaLicencia);
    }
    
    public boolean estaVigente(ClaseLicencia clase,Titular titular){
        return licenciaRepo.existsByClaseLicenciaAndTitularDni(clase, titular.getDni());
    }
    
    public int calcularVigencia(Licencia licencia, Titular titular) {
        int edad = Period.between(titular.getFechaNacimiento(), LocalDate.now()).getYears();
        if(edad < 21) {
            if(licenciaRepo.findByTitularDni(titular.getDni()).isEmpty())
                return 1;
            else return 3;
        }
        if(edad >= 21 && edad < 47) return 5;
        if(edad >= 47 && edad < 61) return 4;
        if(edad >= 61 && edad <= 70) return 3;
        if(edad > 70) return 1;
        return 0;
    }
    
    public int calcularCosto(Licencia licencia, boolean esCopia) {
        
        if(esCopia) return 50;
        
        ClaseLicencia claseLicencia = licencia.getClaseLicencia();
        int vigencia = this.calcularVigencia(licencia, licencia.getTitular());
        switch(claseLicencia) {
            case ClaseLicencia.A:
                switch(vigencia) {
                    case(5) -> {
                        return 48;
                }
                    case(4) -> {
                        return 38;
                }
                    case(3) -> {
                        return 33;
                }
                    case(1) -> {
                        return 28;
                }
                }

            case ClaseLicencia.B:
                switch(vigencia) {
                    case(5) -> {
                        return 48;
                }
                    case(4) -> {
                        return 38;
                }
                    case(3) -> {
                        return 33;
                }
                    case(1) -> {
                        return 28;
                }
                }

            case ClaseLicencia.C:
                switch(vigencia) {
                    case(5) -> {
                        return 55;
                }
                    case(4) -> {
                        return 43;
                }
                    case(3) -> {
                        return 38;
                }
                    case(1) -> {
                        return 31;
                }
                }

            case ClaseLicencia.D:
                switch(vigencia) {
                    case(5) -> {
                        return 108;
                }
                    case(4) -> {
                        return 98;
                }
                    case(3) -> {
                        return 78;
                }
                    case(1) -> {
                        return 58;
                }
                }

            case ClaseLicencia.E:
                switch(vigencia) {
                    case(5) -> {
                        return 67;
                }
                    case(4) -> {
                        return 52;
                }
                    case(3) -> {
                        return 47;
                }
                    case(1) -> {
                        return 37
                                ;
                }
                }

            case ClaseLicencia.F:
                switch(vigencia) {
                    case(5) -> {
                        return 28;
                }
                    case(4) -> {
                        return 23;
                }
                    case(3) -> {
                        return 18;
                }
                    case(1) -> {
                        return 13;
                }
                }

            case ClaseLicencia.G:
                switch(vigencia) {
                    case(5) -> {
                        return 48;
                }
                    case(4) -> {
                        return 38;
                }
                    case(3) -> {
                        return 33;
                }
                    case(1) -> {
                        return 28;
                }
                }

        }
        return 0;
    }
    
    public Licencia renovarLicencia(Licencia licencia, String observaciones, Usuario usuario) {

        Titular titular = licencia.getTitular();
        
        Licencia renovacionLicencia = new Licencia();
        renovacionLicencia.setClaseLicencia(licencia.getClaseLicencia());
        renovacionLicencia.setTitular(titular);
        renovacionLicencia.setObservaciones(observaciones);
        renovacionLicencia.setUsuario(usuario);
        renovacionLicencia.setFechaEmision(licencia.getFechaEmision());
        renovacionLicencia.setFechaVencimiento(calcularVigencia(licencia, titular), titular.getFechaNacimiento());
        renovacionLicencia.setFechaEmision(LocalDate.now());
        renovacionLicencia.setVersionAnterior(licencia);
        
        return licenciaRepo.save(renovacionLicencia);
    }

    public java.util.List<Licencia> obtenerLicenciasPorTitular(Long dni) {
        return licenciaRepo.findByTitularDni(dni);
    }
    
}