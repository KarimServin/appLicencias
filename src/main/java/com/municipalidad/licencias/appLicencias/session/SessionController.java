package com.municipalidad.licencias.appLicencias.session;

import com.municipalidad.licencias.appLicencias.dto.UsuarioDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.modules.menu.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionController {

    
    private final UserSession userSession;


    
    public String getNombreUsuarioActual() {
        return userSession.getNombreUsuarioActual();
    }
    
    public boolean esSuperusuario() {
        return userSession.esSuperusuario(); 
    }
    
    
    @Autowired
    public SessionController(UserSession userSession) {
        this.userSession = userSession;
        //this.controllerFactory = controllerFactory;
    }
    
    public void login(UsuarioDTO usuarioDTO) throws ServiceException {
        userSession.setUser(usuarioDTO);
        
        //MenuController menu = controllerFactory.createMenuController();
        //menu.display();
        
        
    }
    
    public void logout() {
    
        userSession.clearUser();
    }
     
}
