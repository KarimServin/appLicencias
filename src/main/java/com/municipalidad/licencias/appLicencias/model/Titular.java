
package com.municipalidad.licencias.appLicencias.model;

import jakarta.persistence.Column;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
@Entity
public class Titular {
    
    @Id
    @Column(name = "titular_dni")
    private Long dni;
    
    private boolean esDonante;
    
    private char grupoSanguineo;
    
    private char factorSanguineo;
    
    private String nombre;
    
    private String apellido; 
    
    private String domicilio;

    private LocalDate fechaNacimiento;

    private boolean tuvoLicenciaProfesional;

    private LocalDate fechaLicenciaClaseB;

    private long telefono;

    private String email;
    
    @ManyToOne      //asociado al usuario que generó el titular
    private Usuario usuario;
    
}