package com.municipalidad.licencias.appLicencias;
import com.municipalidad.licencias.appLicencias.factory.ControllerFactory;
import com.municipalidad.licencias.appLicencias.modules.login.LoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class BootModule {

    private final LoginController loginController;
    private final ControllerFactory controllerFactory;
    
    @Autowired
    public BootModule(LoginController loginController, ControllerFactory controllerFactory) {
        this.loginController = loginController;
        this.controllerFactory = controllerFactory;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void iniciarAplicacion() {
        loginController.display();
    }
}

