package com.municipalidad.licencias.appLicencias.modules.consultarlicencias;

import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Builder
public class LicenciaReporteDTO {
    private Long id;
    private Set<ClaseLicencia> clases;
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;
    private Boolean vigente;

    // Titular
    private Long titularDni;
    private String titularNombreCompleto;

    // Usuario que emitió
    private String emitidaPor;
}
