package com.municipalidad.licencias.appLicencias.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class TitularDTO {

    private Long dni;
    private String nombre;
    private String apellido;
    private String domicilio;
    private LocalDate fechaNacimiento;
    private Long telefono;
    private String email;
    private Boolean esDonante;
    private Boolean tuvoLicenciaProfesional;
    private LocalDate fechaLicenciaClaseB;
    private String grupoSanguineo;   // "A", "B", "AB", "O"
    private String factorSanguineo; 

}