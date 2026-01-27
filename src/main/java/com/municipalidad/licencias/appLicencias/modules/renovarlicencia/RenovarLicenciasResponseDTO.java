package com.municipalidad.licencias.appLicencias.modules.renovarlicencia;

import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import java.util.List;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class RenovarLicenciasResponseDTO {
    private Long titularDni;
    private List<LicenciaDTO> licenciasRenovadas;
}
