package com.municipalidad.licencias.appLicencias.session;

import com.municipalidad.licencias.appLicencias.dto.UsuarioDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import org.springframework.stereotype.Component;

@Component
public class UserSession implements SessionInfo {

    private UsuarioDTO usuario;

    public void setUser(UsuarioDTO usuarioDTO) throws ServiceException {

        if (this.usuario != null) {
            throw new ServiceException("Ya existe una sesión abierta en este equipo");
        }
        this.usuario = usuarioDTO;

    }

    public void clearUser() {
        usuario = null;
    }

    public boolean estaLogueado() {
        return usuario != null;
    }

    public UsuarioDTO getUsuarioActual() {
        return usuario;
    }

    @Override
    public String getNombreUsuarioActual() {
        return usuario.getUsuario();
    }

    @Override
    public boolean esSuperusuario() {
        return usuario.esAdmin();
    }

}
