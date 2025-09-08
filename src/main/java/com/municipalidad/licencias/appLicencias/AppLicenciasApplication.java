package com.municipalidad.licencias.appLicencias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AppLicenciasApplication {

 
	public static void main(String[] args) {
		
            try {
                
                    ConfigurableApplicationContext context = SpringApplication.run(AppLicenciasApplication.class, args);
                    System.setProperty("java.awt.headless", "false"); 
                    LoginController loginController = context.getBean(LoginController.class);
                    loginController.iniciarAplicacion();
            
            } catch (Exception e) {
              
                e.printStackTrace();
              
            }
                
	}
    
        
} 
