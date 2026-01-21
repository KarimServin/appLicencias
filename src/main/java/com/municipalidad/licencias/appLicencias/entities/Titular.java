
package com.municipalidad.licencias.appLicencias.entities;

import jakarta.persistence.Column;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "titular")
public class Titular {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // ID interno de la base de datos

    @Column(name = "dni", unique = true, nullable = false)
    private Long dni; // Identificación única del ciudadano
    
    private String nombre;
    private String apellido; 
    private String domicilio;
    private LocalDate fechaNacimiento;
    
    private boolean esDonante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tipo_sangre", nullable = false)
    private TipoSangre tipoSangre;

    private boolean tuvoLicenciaProfesional;
    private LocalDate fechaLicenciaClaseB;
    private long telefono;
    private String email;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}

