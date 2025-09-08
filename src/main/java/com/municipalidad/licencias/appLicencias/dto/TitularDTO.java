package com.municipalidad.licencias.appLicencias.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TitularDTO {

    private Long dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private char grupoSanguineo;
    private char factorSanguineo;
    private boolean esDonante;
    private Long telefono;
    private String email;
    private String domicilio;
    
    public TitularDTO(Long dni, String nombre, String apellido,    
                      LocalDate fechaNacimiento, char grupoSanguineo, 
                      char factorSanguineo, boolean esDonante, long telefono, 
                      String correo, String domicilio) {
        
            this.dni = dni;
            this.nombre = nombre;
            this.apellido = apellido;
            this.fechaNacimiento = fechaNacimiento;
            this.grupoSanguineo = grupoSanguineo;
            this.telefono = telefono;
            this.email = correo;
            this.domicilio = domicilio;
        
        
    }

}
