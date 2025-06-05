package com.municipalidad.licencias.appLicencias.service;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Service;

import com.municipalidad.licencias.appLicencias.model.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.model.Licencia;
import com.municipalidad.licencias.appLicencias.model.Titular;
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

    public Licencia emitirLicencia(Long dni, ClaseLicencia clase) {
        Titular titular = titularRepo.findById(dni)
                .orElseThrow(() -> new RuntimeException("Titular no encontrado"));

        if (!puedeEmitirLicencia(dni, clase)) {
            throw new RuntimeException("No cumple los requisitos para emitir esta licencia");
        }

        Licencia licencia = new Licencia();
        licencia.setClase(clase);
        licencia.setTitular(titular);
        licencia.setFechaEmision(LocalDate.now());

        return licenciaRepo.save(licencia);
    }
}