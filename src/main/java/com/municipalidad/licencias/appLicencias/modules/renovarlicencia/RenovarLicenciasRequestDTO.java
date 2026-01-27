package com.municipalidad.licencias.appLicencias.modules.renovarlicencia;


import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import java.util.Set;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class RenovarLicenciasRequestDTO {
    private Long titularDni;
    private Set<ClaseLicencia> clasesARenovar;
    private String observaciones; // opcional, si tu UI lo agrega
}