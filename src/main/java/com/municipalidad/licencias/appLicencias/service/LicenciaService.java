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
        licencia.setFechaVencimiento(calcularVigencia(licencia, titular));
        licencia.setObservaciones(observaciones);
        licencia.setUsuario(usuario);

        return licenciaRepo.save(licencia);
    }
    
    public boolean estaVigente(ClaseLicencia clase,Titular titular){
        return licenciaRepo.existsByClaseLicenciaAndTitularId(clase, titular.getId());
    }
    
    public int calcularVigencia(Licencia licencia, Titular titular) {
    int edad = Period.between(titular.getFechaNacimiento(), LocalDate.now()).getYears();
    if(edad < 21) {
        if(licenciaRepo.findByTitularId(titular.getId()).isEmpty())
            return 1;
        else return 3;
    }
    if(edad >= 21 && edad < 47) return 5;
    if(edad >= 47 && edad < 61) return 4;
    if(edad >= 61 && edad <= 70) return 3;
    if(edad > 70) return 1;
    return 0;
    }
    
    public int calcularCosto(Licencia licencia) {
    ClaseLicencia claseLicencia = licencia.getClaseLicencia();
    int vigencia = this.calcularVigencia(licencia, licencia.getTitular());
    switch(claseLicencia) {
        case ClaseLicencia.A:
            switch(vigencia) {
                case(5) -> {
                    return 40;
            }
                case(4) -> {
                    return 30;
            }
                case(3) -> {
                    return 25;
            }
                case(1) -> {
                    return 20;
            }
            }

        case ClaseLicencia.B:
            switch(vigencia) {
                case(5) -> {
                    return 40;
            }
                case(4) -> {
                    return 30;
            }
                case(3) -> {
                    return 25;
            }
                case(1) -> {
                    return 20;
            }
            }

        case ClaseLicencia.C:
            switch(vigencia) {
                case(5) -> {
                    return 47;
            }
                case(4) -> {
                    return 35;
            }
                case(3) -> {
                    return 30;
            }
                case(1) -> {
                    return 23;
            }
            }

        case ClaseLicencia.D:
            switch(vigencia) {
                case(5) -> {
                    return 100;
            }
                case(4) -> {
                    return 90;
            }
                case(3) -> {
                    return 70;
            }
                case(1) -> {
                    return 50;
            }
            }

        case ClaseLicencia.E:
            switch(vigencia) {
                case(5) -> {
                    return 59;
            }
                case(4) -> {
                    return 44;
            }
                case(3) -> {
                    return 39;
            }
                case(1) -> {
                    return 29
                            ;
            }
            }

        case ClaseLicencia.F:
            switch(vigencia) {
                case(5) -> {
                    return 20;
            }
                case(4) -> {
                    return 15;
            }
                case(3) -> {
                    return 10;
            }
                case(1) -> {
                    return 5;
            }
            }

        case ClaseLicencia.G:
            switch(vigencia) {
                case(5) -> {
                    return 40;
            }
                case(4) -> {
                    return 30;
            }
                case(3) -> {
                    return 25;
            }
                case(1) -> {
                    return 20;
            }
            }

    }
    return 0;
    }
    
    
}