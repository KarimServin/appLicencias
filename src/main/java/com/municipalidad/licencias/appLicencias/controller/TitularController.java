package com.municipalidad.licencias.appLicencias.controller;

import com.municipalidad.licencias.appLicencias.exception.TitularExistenteException;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;


import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.service.TitularServiceImpl;
import java.util.Optional;



@Controller
public class TitularController {

    private final TitularServiceImpl titularService;

    public TitularController(TitularServiceImpl titularService) {
        this.titularService = titularService;
    }

    public Titular crearTitular(Long dni, String nombre, LocalDate fechaNacimiento,
                                char grupoSanguineo, char factorSanguineo,
                                boolean esDonante, boolean tuvoLicenciaProfesional,
                                LocalDate fechaLicenciaClaseB, Long telefono, String email) {
       if(buscarTitularPorDni(dni).isPresent()){ throw new TitularExistenteException("Ya existe un titular con ese dni");}
        return titularService.guardarTitular(dni, nombre, fechaNacimiento,
                                           grupoSanguineo, factorSanguineo,
                                           esDonante, tuvoLicenciaProfesional,
                                           fechaLicenciaClaseB, telefono, email);
      
    }

    public Optional<Titular> buscarTitularPorDni(Long dni){
        return titularService.buscarPorDni(dni);
    }
    
    public Titular buscarTitular(Long dni) {
        return titularService.buscarPorDni(dni)
                .orElseThrow(() -> new RuntimeException("Titular no encontrado"));
    }
}
