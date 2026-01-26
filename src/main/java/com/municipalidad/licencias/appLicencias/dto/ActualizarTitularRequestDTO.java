package com.municipalidad.licencias.appLicencias.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ActualizarTitularRequestDTO {
    private String domicilio;
    private Boolean esDonante;
}
