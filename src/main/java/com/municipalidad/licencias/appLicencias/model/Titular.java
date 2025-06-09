
package com.municipalidad.licencias.appLicencias.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
@Entity
public class Titular {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long dni;
    
    private String nombre;

    private LocalDate fechaNacimiento;

    private char grupoSanguineo;

    private char factorSanguineo;

    private boolean esDonante;

    private boolean tuvoLicenciaProfesional;

    private LocalDate fechaLicenciaClaseB;

    private long telefono;

    private String email;
    
    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;
}