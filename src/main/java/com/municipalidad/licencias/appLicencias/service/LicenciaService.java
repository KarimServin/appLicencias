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

    public boolean puedeEmitirLicencia(Long titularId, ClaseLicencia claseSolicitada) {
        Titular titular = titularRepo.findById(titularId)
                .orElseThrow(() -> new RuntimeException("Titular no encontrado"));
        int edad = Period.between(titular.getFechaNacimiento(), LocalDate.now()).getYears();

        // Lógica según clase (igual que antes, con Enum)
        switch (claseSolicitada) {
            case A:
            case B:
            case F:
            case G:
                return edad >= 17;
            case C:
            case D:
            case E:
                if (edad < 21) return false;
                if (titular.isTuvoLicenciaProfesional()) return true;
                if (edad >= 65) return false;
                List<Licencia> licenciasClaseB = licenciaRepo.findByTitularIdAndClase(titularId, ClaseLicencia.B);
                if (licenciasClaseB.isEmpty()) return false;
                LocalDate fechaDesde = licenciasClaseB.stream()
                        .map(Licencia::getFechaEmision)
                        .min(LocalDate::compareTo)
                        .orElse(LocalDate.now());
                return Period.between(fechaDesde, LocalDate.now()).getYears() >= 1;
            default:
                return false;
        }
    }

     public Licencia emitirLicencia(Long titularId, Licencia licencia) {
        Titular titular = titularRepo.findById(titularId)
                .orElseThrow(() -> new RuntimeException("Titular no encontrado"));

        if (!puedeEmitirLicencia(titularId, licencia.getClase())) {
            throw new RuntimeException("No cumple los requisitos para emitir esta licencia");
        }

        licencia.setTitular(titular);
        licencia.setFechaEmision(LocalDate.now());
        return licenciaRepo.save(licencia);
    }
    // Otros métodos: emitir licencia, registrar titular, etc.
}
