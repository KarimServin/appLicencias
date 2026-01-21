package com.municipalidad.licencias.appLicencias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class AppLicencias {

    private static final Logger logger = LoggerFactory.getLogger(AppLicencias.class);
 
	public static void main(String[] args) {
            try {
                logger.info("Iniciando contexto de Spring Boot");
                System.setProperty("java.awt.headless", "false");

                SpringApplication.run(AppLicencias.class, args);

                logger.info("Contexto de Spring inicializado con éxito");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
} 
