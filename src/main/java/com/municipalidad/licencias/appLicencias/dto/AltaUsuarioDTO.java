package com.municipalidad.licencias.appLicencias.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password") //Evitar que se imprima la contraseña en logs
public class AltaUsuarioDTO {
        private String nombreUsuario;
        private char[] password;
        private boolean isAdmin;
}
