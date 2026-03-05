package com.municipalidad.licencias.appLicencias.modules.emitirlicencia;

import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import java.util.Set;
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
    private Set<ClaseLicencia> clases;
}