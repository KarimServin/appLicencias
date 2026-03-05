package com.municipalidad.licencias.appLicencias.modules.emitirlicencia;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ActualizarTitularRequestDTO {
    private Long dni;        // para identificar al titular a actualizar
    private String nombre;
    private String apellido;
    private Long telefono;
    private String email;
    private String domicilio;
    private Boolean esDonante;
}
