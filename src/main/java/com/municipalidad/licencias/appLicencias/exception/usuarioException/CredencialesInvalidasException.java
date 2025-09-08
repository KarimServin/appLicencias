package com.municipalidad.licencias.appLicencias.exception.usuarioException;


public class CredencialesInvalidasException extends RuntimeException {

    public CredencialesInvalidasException() {
        super("Usuario o contraseña incorrectos");
    }
    
}
