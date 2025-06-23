
package com.municipalidad.licencias.appLicencias.service;
import com.municipalidad.licencias.appLicencias.model.Usuario;
import java.util.List;



public interface UsuarioService {
    Usuario buscarPorNombreUsuario(String nombreUsuario);
    Usuario validarCredenciales(String nombreUsuario, String contrasenia);
    void guardarUsuario(String nombreUsuario, String contrasenia, boolean esSuperusuario);
    List<String> obtenerTodosLosNombresDeUsuario();
    void actualizarUsuario(String nombreUsuarioActual, String nuevoNombre, String nuevaContrasenia, boolean esSuperusuario);
}