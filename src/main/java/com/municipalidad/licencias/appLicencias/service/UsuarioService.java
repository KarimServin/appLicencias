
package com.municipalidad.licencias.appLicencias.service;
import com.municipalidad.licencias.appLicencias.model.Usuario;



public interface UsuarioService {
    Usuario buscarPorNombreUsuario(String nombreUsuario);
    Usuario validarCredenciales(String nombreUsuario, String contrasenia);
    void guardarUsuario(String nombreUsuario, String contrasenia, boolean esSuperusuario);
}