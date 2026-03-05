package com.municipalidad.licencias.appLicencias.modules.login;

import com.municipalidad.licencias.appLicencias.session.SessionController;
import com.municipalidad.licencias.appLicencias.dto.UsuarioDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import javax.swing.SwingUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.municipalidad.licencias.appLicencias.auth.AuthService;
import com.municipalidad.licencias.appLicencias.dto.CredencialesDTO;
import com.municipalidad.licencias.appLicencias.modules.menu.MenuController;
import com.municipalidad.licencias.appLicencias.viewforms.Dialogs;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
import org.springframework.beans.factory.ObjectProvider;

@Component
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final AuthService authService;
    private final SessionController sessionController;
    private final ObjectProvider<MenuController> menuProvider;
    
    private PantallaLogin pantallaLogin;
    
    @Autowired
    public LoginController(AuthService authenticationService,
                           SessionController sessionController,
                           ObjectProvider<MenuController> menuProvider) {
        this.authService = authenticationService;
        this.sessionController = sessionController;
        this.menuProvider = menuProvider;
    }

    public void display() {

        System.out.println("LoginController.display() instance=" + System.identityHashCode(this));
        logger.info("Iniciando aplicación de Licencias Municipales");
        
        SwingUtilities.invokeLater(() -> {
            pantallaLogin = new PantallaLogin();
            pantallaLogin.setListeners(e -> autenticar());
            pantallaLogin.setVisible(true);
            
        });
        
    }


    public void autenticar() {
        CredencialesDTO credenciales = pantallaLogin.getCredencialesIngresadas();

        try {
            logger.debug("Intentando autenticar usuario: {}", credenciales.getNombreUsuario());

            // Opcional: bloquear botón mientras autentica
            // pantallaLogin.setLoginEnabled(false);

            SwingWorker<UsuarioDTO, Void> worker = new SwingWorker<>() {

                @Override
                protected UsuarioDTO doInBackground() throws Exception {
                    // ⚠️ Esto corre FUERA del EDT
                    return authService.login(credenciales);
                }

                @Override
                protected void done() {
                    // ✅ Esto corre EN el EDT
                    try {
                        UsuarioDTO usuarioDTO = get(); 
                        onLoginExitoso(usuarioDTO);

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        logger.error("Login interrumpido", e);
                        Dialogs.error(pantallaLogin, "Login interrumpido. Intente nuevamente.");

                    } catch (ExecutionException e) {
                        Throwable cause = e.getCause();

                        if (cause instanceof ServiceException se) {
                            logger.warn("Error de autenticación: {}", se.getMessage());
                            Dialogs.error(pantallaLogin, se.getMessage());
                        } else {
                            logger.error("Error inesperado durante autenticación", cause);
                            Dialogs.error(pantallaLogin, "Ocurrió un error inesperado. Intente nuevamente.");
                        }

                    } catch (ServiceException se) {
                        // por si onLoginExitoso() falla
                        logger.warn("Error post-login: {}", se.getMessage());
                        Dialogs.error(pantallaLogin, se.getMessage());

                    } finally {
                        // pantallaLogin.setLoginEnabled(true);
                    }
                }
            };

            worker.execute();

        } catch (Exception ex) {
            // errores raros antes de arrancar el worker
            logger.error("Error inesperado al iniciar autenticación", ex);
            Dialogs.error(pantallaLogin, "Ocurrió un error inesperado. Intente nuevamente.");
            // pantallaLogin.setLoginEnabled(true);
        }
    }

    

    private void onLoginExitoso(UsuarioDTO usuarioDTO) throws ServiceException {
        logger.info("Login exitoso para usuario: {}. Iniciando sesión...", usuarioDTO.getNombreUsuario());

        sessionController.login(usuarioDTO);

        // Pre-resolver el MenuController en background MIENTRAS el usuario ve el diálogo
        CompletableFuture<MenuController> menuFuture = CompletableFuture.supplyAsync(
            () -> menuProvider.getObject()
        );

        // El diálogo es modal: bloquea este hilo hasta que el usuario presione OK
        Dialogs.exito(pantallaLogin, "Login exitoso");

        // Cuando llega acá, el bean ya debería estar resuelto (o casi)
        pantallaLogin.dispose();

        try {
            MenuController menu = menuFuture.get(); // Si ya terminó, retorna inmediato
            menu.display();
        } catch (Exception e) {
            logger.error("Error al preparar el menú", e);
            Dialogs.error(null, "No se pudo abrir el menú.");
        }
    }  

}