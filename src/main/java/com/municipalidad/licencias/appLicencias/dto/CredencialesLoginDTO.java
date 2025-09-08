package com.municipalidad.licencias.appLicencias.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
public class CredencialesLoginDTO {
    private String nombreUsuario;
    private char[] password;
}
