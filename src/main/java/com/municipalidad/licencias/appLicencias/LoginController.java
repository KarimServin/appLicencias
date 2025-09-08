package com.municipalidad.licencias.appLicencias;



import com.municipalidad.licencias.appLicencias.dto.CredencialesLoginDTO;
import com.municipalidad.licencias.appLicencias.dto.UsuarioDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.authenticationService.AuthenticationService;
import com.municipalidad.licencias.appLicencias.session.UserSession;
import com.municipalidad.licencias.appLicencias.ui.PantallaLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.SwingUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class LoginController {
    
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final AuthenticationService authenticationService;
    private final UserSession userSession;
    
    @Autowired
    public LoginController(
                         AuthenticationService authenticationService,
                         UserSession userSession) {
       
        this.authenticationService = authenticationService;
        this.userSession = userSession;
        
    }
   
   
    
    public  void iniciarAplicacion() {
        logger.info("Iniciando aplicación de Licencias Municipales");
        SwingUtilities.invokeLater(() -> {
            PantallaLogin login;
            login = new PantallaLogin(this::autenticar);
            login.setVisible(true);
        });
           
    }
    
    
    public void autenticar(CredencialesLoginDTO credenciales) {
    
        try {
            logger.debug("Intentando autenticar usuario: {}", credenciales.getNombreUsuario());
            
            UsuarioDTO usuarioDTO = authenticationService.login(credenciales);
            onLoginExitoso(usuarioDTO);
            
           } catch (ServiceException ex) {
            // Manejo específico de error de servicio
            logger.warn("Error de autenticación: {}", ex.getMessage());
           
        } catch (Exception ex) {
            // Manejo de errores inesperados
            logger.error("Error inesperado durante autenticación", ex); 
        }

    }
 
    private void onLoginExitoso(UsuarioDTO usuarioDTO) {
        logger.info("Navegando al menú principal para usuario: {}", usuarioDTO.getUsuario());
        
        SwingUtilities.invokeLater(navigationController::mostrarMenuPrincipal);
        
    }
    
}


