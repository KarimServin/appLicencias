package com.municipalidad.licencias.appLicencias.modules.gestionarusuarios;

import java.io.Serializable;

public class ActualizarUsuarioRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nuevoNombreUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private char[] nuevaContrasenia;
    private Boolean superusuario;

    public String getNuevoNombreUsuario() { return nuevoNombreUsuario; }
    public void setNuevoNombreUsuario(String nuevoNombreUsuario) { this.nuevoNombreUsuario = nuevoNombreUsuario; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public char[] getNuevaContrasenia() { return nuevaContrasenia; }
    public void setNuevaContrasenia(char[] nuevaContrasenia) { this.nuevaContrasenia = nuevaContrasenia; }

    public Boolean getSuperusuario() { return superusuario; }
    public void setSuperusuario(Boolean superusuario) { this.superusuario = superusuario; }
}