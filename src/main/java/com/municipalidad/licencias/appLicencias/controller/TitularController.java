package com.municipalidad.licencias.appLicencias.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.service.TitularService;



@Controller
public class TitularController {

    private final TitularService titularService;

    public TitularController(TitularService titularService) {
        this.titularService = titularService;
    }

    public Titular crearTitular(Long dni, String nombre, LocalDate fechaNacimiento,
                                char grupoSanguineo, char factorSanguineo,
                                boolean esDonante, boolean tuvoLicenciaProfesional,
                                LocalDate fechaLicenciaClaseB, Long telefono, String email) {
        return titularService.crearTitular(dni, nombre, fechaNacimiento,
                                           grupoSanguineo, factorSanguineo,
                                           esDonante, tuvoLicenciaProfesional,
                                           fechaLicenciaClaseB, telefono, email);
    }

    public Titular buscarTitular(Long dni) {
        return titularService.buscarPorDni(dni)
                .orElseThrow(() -> new RuntimeException("Titular no encontrado"));
    }
}
