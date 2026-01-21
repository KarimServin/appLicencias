/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.municipalidad.licencias.appLicencias.exception.usuarioException;

/**
 *
 * @author karim
 */
public class UsuarioYaExisteException extends RuntimeException {

  
    public UsuarioYaExisteException() {
        
        super("El nombre de usuario ya está en uso");
        
    }

   
}
