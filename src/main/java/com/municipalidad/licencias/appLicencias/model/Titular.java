
package com.municipalidad.licencias.appLicencias.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
@Entity
public class Titular {
    @Id 
    private Long dni;
    
    private boolean esDonante;
    
    private char grupoSanguineo;
    
    private char factorSanguineo;
    
    private String nombre;

    private String direccion;

    private LocalDate fechaNacimiento;

    private boolean tuvoLicenciaProfesional;

    private LocalDate fechaLicenciaClaseB;

    private long telefono;

    private String email;
    
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;
}