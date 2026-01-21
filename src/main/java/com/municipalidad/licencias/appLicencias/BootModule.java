package com.municipalidad.licencias.appLicencias;
import com.municipalidad.licencias.appLicencias.factory.ControllerFactory;
import com.municipalidad.licencias.appLicencias.modules.login.LoginController;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class BootModule {

    private final LoginController loginController;

    
    @Autowired
    public BootModule(LoginController loginController) {
        this.loginController = loginController;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void iniciarAplicacion() {
        loginController.display();
    }
    
}


