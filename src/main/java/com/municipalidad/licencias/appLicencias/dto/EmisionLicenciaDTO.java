package com.municipalidad.licencias.appLicencias.dto;

import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmisionLicenciaDTO {
    private Long dniTitular;
    private ClaseLicencia clase;
    private String observaciones;
}