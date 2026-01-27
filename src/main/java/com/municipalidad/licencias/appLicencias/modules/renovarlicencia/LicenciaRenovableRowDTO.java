package com.municipalidad.licencias.appLicencias.modules.renovarlicencia;

import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import java.time.LocalDate;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class LicenciaRenovableRowDTO {
    private Long licenciaId;
    private ClaseLicencia clase;
    private Boolean vigente;          // calculado
    private LocalDate fechaVencimiento;
    private Boolean renovar;          // checkbox (solo UI)
}
