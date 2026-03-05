package com.municipalidad.licencias.appLicencias.dto;

import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import java.time.LocalDate;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LicenciaDTO {
    private Long id;
    private Set<ClaseLicencia> clases;   // <--- antes: una sola
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;
    private String observaciones;
    private Boolean vigente;

    // Campos del titular
    private Long titularDni;
}