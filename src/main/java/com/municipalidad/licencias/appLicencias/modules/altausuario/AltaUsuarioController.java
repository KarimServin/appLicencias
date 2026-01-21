package com.municipalidad.licencias.appLicencias.modules.altausuario;


import com.municipalidad.licencias.appLicencias.dto.AltaUsuarioDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.service.UsuarioService;
import com.municipalidad.licencias.appLicencias.view.Dialogs;
import javax.swing.SwingUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AltaUsuarioController {
  
    private final UsuarioService usuarioService;
    private AltaUsuarioView view;
    
    
    @Autowired
    public AltaUsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
  
    public void display() {
        
        SwingUtilities.invokeLater(() -> {    
            view = new AltaUsuarioView();
        });
                
        configurarListeners();
        view.setVisible(true);
    
    }
  
    private void configurarListeners() {
        view.setAceptarAction(e -> crearUsuario());
        view.setCancelarAction(e -> cancelar());
        
    }
  
 
    private void crearUsuario() {
        
            AltaUsuarioDTO altaUsuarioDTO = new AltaUsuarioDTO(view.getNombreUsuario(),
                                                               view.getPassword(),
                                                               view.isAdmin());
            try {
                usuarioService.nuevoUsuario(altaUsuarioDTO);  

                
                Dialogs.exito(view,"Alta de usuario exitosa.");
                view.dispose();
   
            } catch (ServiceException e) {   
                Dialogs.error(view,e.getMessage());
            }
    }
    
    private void cancelar() {
        view.dispose();
    }
    
    
    
}
