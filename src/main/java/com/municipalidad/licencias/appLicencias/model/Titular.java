/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.municipalidad.licencias.appLicencias.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Miguel
 */

@Getter
@Setter
@Entity
public class Titular {
    @Id
    private Long id;
    
    private boolean esDonante;
    
    private char grupoSanguineo;
    
    private char factorSanguineo;

    private String nombre;

    private LocalDate fechaNacimiento;

    private boolean tuvoLicenciaProfesional;

    private LocalDate fechaLicenciaClaseB;
}
