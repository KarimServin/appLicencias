
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

    private String nombre;

    private LocalDate fechaNacimiento;

    private char grupoSanguineo;

    private char factorSanguineo;

    private boolean esDonante;

    private boolean tuvoLicenciaProfesional;

    private LocalDate fechaLicenciaClaseB;

    private long telefono;

    private String email;
}
