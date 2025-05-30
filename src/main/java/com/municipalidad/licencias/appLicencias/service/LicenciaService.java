package com.municipalidad.licencias.appLicencias.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

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

    public boolean puedeEmitirLicencia(Long titularId, String claseSolicitada) {
        Titular titular = titularRepo.findById(titularId).orElseThrow(() -> new RuntimeException("Titular no encontrado"));
        int edad = Period.between(titular.getFechaNacimiento(), LocalDate.now()).getYears();

        if ("A".equalsIgnoreCase(claseSolicitada) || "B".equalsIgnoreCase(claseSolicitada) 
            || "F".equalsIgnoreCase(claseSolicitada) || "G".equalsIgnoreCase(claseSolicitada)) {
            return edad >= 17;
        }

        if ("C".equalsIgnoreCase(claseSolicitada) || "D".equalsIgnoreCase(claseSolicitada) || "E".equalsIgnoreCase(claseSolicitada)) {
            if (edad < 21) return false;
            if (titular.isTuvoLicenciaProfesional()) return true;
            if (edad >= 65) return false;

            // Validar que tuvo licencia clase B al menos 1 año
            List<Licencia> licenciasClaseB = licenciaRepo.findByTitularIdAndClase(titularId, "B");
            if (licenciasClaseB.isEmpty()) return false;

            LocalDate fechaDesde = licenciasClaseB.stream()
                .map(Licencia::getFechaEmision)
                .min(LocalDate::compareTo)
                .orElse(LocalDate.now());

            return Period.between(fechaDesde, LocalDate.now()).getYears() >= 1;
        }

        return false;
    }

    // Otros métodos: emitir licencia, registrar titular, etc.
}
