package com.municipalidad.licencias.appLicencias.service;
import com.municipalidad.licencias.appLicencias.dto.ActualizarUsuarioRequestDTO;
import com.municipalidad.licencias.appLicencias.dto.AltaUsuarioDTO;
import com.municipalidad.licencias.appLicencias.dto.UsuarioDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.entities.Usuario;
import java.util.List;



public interface UsuarioService {
    Usuario buscarPorNombreUsuario(String nombreUsuario);
    void nuevoUsuario(AltaUsuarioDTO altaUsuarioDTO) throws ServiceException;
    List<UsuarioDTO> obtenerTodosLosUsuarios();
    public void actualizarUsuario(Long idUsuario, ActualizarUsuarioRequestDTO req);
}