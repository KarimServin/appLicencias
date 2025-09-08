package com.municipalidad.licencias.appLicencias.service;
import com.municipalidad.licencias.appLicencias.dto.AltaUsuarioDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.model.Usuario;
import java.util.List;



public interface UsuarioService {
    Usuario buscarPorNombreUsuario(String nombreUsuario);
    void nuevoUsuario(AltaUsuarioDTO altaUsuarioDTO) throws ServiceException;
    List<String> obtenerTodosLosNombresDeUsuario();
    void actualizarUsuario(String nombreUsuarioActual, String nuevoNombre, char[] nuevaContrasenia, boolean esSuperusuario);
}