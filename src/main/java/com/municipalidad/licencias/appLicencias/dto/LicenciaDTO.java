package com.municipalidad.licencias.appLicencias.dto;

import com.municipalidad.licencias.appLicencias.model.ClaseLicencia;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LicenciaDTO {
    
    private ClaseLicencia claseLicencia;
    private LocalDate fechaEmision;
    private LocalDate fechaVencimiento;
    private String observaciones;
    // Campos del titular 
    private Long titularDni;
    private String titularNombre;   
}