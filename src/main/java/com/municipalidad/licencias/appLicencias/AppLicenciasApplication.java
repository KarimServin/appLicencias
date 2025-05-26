package com.municipalidad.licencias.appLicencias;

import com.municipalidad.licencias.appLicencias.controller.UsuarioController;
import com.municipalidad.licencias.appLicencias.ui.PantallaLogin;
import javax.swing.SwingUtilities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppLicenciasApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AppLicenciasApplication.class, args);
                // Obtené el controlador (Spring sí lo maneja)
                
                
                
             UsuarioController usuarioController = context.getBean(UsuarioController.class);
                
     
            System.setProperty("java.awt.headless", "false"); //corregir
            SwingUtilities.invokeLater(() -> {
			PantallaLogin login = new PantallaLogin(usuarioController);
			login.setVisible(true);
		});
        
                
	}

}
