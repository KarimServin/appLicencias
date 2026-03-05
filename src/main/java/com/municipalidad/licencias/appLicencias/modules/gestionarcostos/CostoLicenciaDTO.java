package com.municipalidad.licencias.appLicencias.modules.gestionarcostos;

import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostoLicenciaDTO {
    private Long id;
    private ClaseLicencia claseLicencia;
    private Integer vigencia;
    private Integer costo;
    private Boolean esCopia;
}
