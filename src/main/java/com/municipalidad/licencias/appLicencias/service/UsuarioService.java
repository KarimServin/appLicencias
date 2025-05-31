
package com.municipalidad.licencias.appLicencias.service;
import com.municipalidad.licencias.appLicencias.model.Usuario;
/**
 *
 * @author karim
 */


public interface UsuarioService {
    Usuario buscarPorNombreUsuario(String nombreUsuario);
    Usuario validarCredenciales(String nombreUsuario, String contrasenia);
}