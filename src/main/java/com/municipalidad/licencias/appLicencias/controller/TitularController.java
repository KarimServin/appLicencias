package com.municipalidad.licencias.appLicencias.controller;

import com.municipalidad.licencias.appLicencias.exception.TitularExistenteException;
import java.time.LocalDate;

import org.springframework.stereotype.Controller;


import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.model.Usuario;
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
                            LocalDate fechaLicenciaClaseB, Long telefono, String email, String direccion, Usuario usuario) {
    if (buscarTitularPorDni(dni).isPresent()) {
        throw new TitularExistenteException("Ya existe un titular con ese DNI");
    }

    int edad = calcularEdad(fechaNacimiento);
    if (edad < 17 || edad > 90) {
        throw new RuntimeException("La edad del titular debe estar entre 17 y 90 años.");
    }

    return titularService.guardarTitular(dni, nombre, fechaNacimiento,
                                         grupoSanguineo, factorSanguineo,
                                         esDonante, tuvoLicenciaProfesional,
                                         fechaLicenciaClaseB, telefono, email, direccion, usuario);
}

    private int calcularEdad(LocalDate fechaNacimiento) {
        return java.time.Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public Optional<Titular> buscarTitularPorDni(Long dni){
        return titularService.buscarPorDni(dni);
    }
    
    public Titular buscarTitular(Long dni) {
        return titularService.buscarPorDni(dni)
                .orElseThrow(() -> new RuntimeException("Titular no encontrado"));
    }

    public void actualizarTitular(Long dni, String nombre, boolean esDonante, Long telefono, String email, String direccion, Usuario usuario) {
        titularService.actualizarTitular(dni, nombre, esDonante, telefono, email, direccion, usuario);
    }
}
