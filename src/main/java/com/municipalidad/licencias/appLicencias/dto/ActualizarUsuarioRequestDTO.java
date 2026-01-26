/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.municipalidad.licencias.appLicencias.dto;

import java.io.Serializable;

public class ActualizarUsuarioRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nuevoNombreUsuario;   // null / blank => no cambiar
    private char[] nuevaContrasenia;     // validación en service
    private Boolean superusuario;        // null => no tocar roles

    public String getNuevoNombreUsuario() {
        return nuevoNombreUsuario;
    }

    public void setNuevoNombreUsuario(String nuevoNombreUsuario) {
        this.nuevoNombreUsuario = nuevoNombreUsuario;
    }

    public char[] getNuevaContrasenia() {
        return nuevaContrasenia;
    }

    public void setNuevaContrasenia(char[] nuevaContrasenia) {
        this.nuevaContrasenia = nuevaContrasenia;
    }

    public Boolean getSuperusuario() {
        return superusuario;
    }

    public void setSuperusuario(Boolean superusuario) {
        this.superusuario = superusuario;
    }
}
