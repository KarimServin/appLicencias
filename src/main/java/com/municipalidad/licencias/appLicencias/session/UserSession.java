package com.municipalidad.licencias.appLicencias.session;

import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.model.Usuario;
import org.springframework.stereotype.Component;


@Component
public class UserSession implements SessionInfo {
    
    private Usuario usuarioAdministrativo;

    public Usuario getUsuarioActual() {
        return usuarioAdministrativo;
    }

    public void setUsuario(Usuario usuario) throws ServiceException {
        
        if (usuarioAdministrativo != null) {

            throw new ServiceException("Ya existe una sesión abierta en este equipo");
        }  
        
        usuarioAdministrativo = usuario;  
    }

    public void cerrarSesion() {
        usuarioAdministrativo = null;
    }

    public boolean estaLogueado() {
        return usuarioAdministrativo != null;
    }
    
   
    @Override
    public String getNombreUsuarioActual() {
        return usuarioAdministrativo.getNombreUsuario();
    }
    
    @Override 
    public boolean esSuperusuario() {
        return usuarioAdministrativo.esAdmin();
    }
   
}


