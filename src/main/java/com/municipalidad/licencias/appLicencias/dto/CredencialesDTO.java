package com.municipalidad.licencias.appLicencias.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class CredencialesDTO {
    private final String nombreUsuario;
    private final char[] password;
}


