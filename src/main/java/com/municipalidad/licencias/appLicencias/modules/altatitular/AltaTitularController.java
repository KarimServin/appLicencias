package com.municipalidad.licencias.appLicencias.modules.altatitular;


import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.exception.ValidationException;
import com.municipalidad.licencias.appLicencias.service.TitularService;
import com.municipalidad.licencias.appLicencias.validation.TitularValidator;
import com.municipalidad.licencias.appLicencias.validation.ValidationResult;
import com.municipalidad.licencias.appLicencias.view.Dialogs;
import java.time.format.DateTimeParseException;
import javax.swing.SwingUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


    @Component
    public class AltaTitularController {

        private static final Logger logger = LoggerFactory.getLogger(AltaTitularController.class);

        private final TitularService titularService;
        private final TitularValidator titularValidator;

        private AltaTitularView view;

        @Autowired
        public AltaTitularController(TitularService titularService,
                                     TitularValidator titularValidator) {
            this.titularService = titularService;
            this.titularValidator = titularValidator;    
        }


  
        public void display() {
            SwingUtilities.invokeLater(() -> {
                view = new AltaTitularView();
                view.setVisible(true);
            });          
            setListeners();
        }  
        
        private void setListeners() {
            view.setAceptarAction(e -> procesarAltaTitular());
            view.setCancelarAction(e -> cancelar()); 
        }
        
        private void cancelar() {
            view.dispose();
        };

        private void procesarAltaTitular() {
           
            try {
 
                
                TitularDTO titularDTO = view.getBuildTitularDTO();
                ValidationResult validation = titularValidator.validarTitular(titularDTO);

                if (!validation.isValid()) {
                    Dialogs.error(view, validation.getFirstError());
                    return;
                } 

                titularService.guardarTitular(titularDTO);

                Dialogs.exito(view,"El titular ha sido registrado con éxito.");
                view.dispose();


            } catch (ValidationException e) {
                logger.warn("Error de validación: {}", e.getMessage());
                Dialogs.error(view,"Error de validación: " + e.getMessage());

            } catch (DateTimeParseException e) {
                logger.warn("Error en formato de fecha: {}", e.getMessage());
                Dialogs.error(view,e.getMessage());

            } catch (NumberFormatException e) {
                logger.warn("Error en formato numérico: {}", e.getMessage());
                Dialogs.error(view,e.getMessage());

            } catch (ServiceException e) {
                logger.error("Error de servicio: {}", e.getMessage());
                Dialogs.error(view,e.getMessage());

            } catch (Exception e) {
                logger.error("Error inesperado: {}", e.getMessage(), e);
                Dialogs.error(view,e.getMessage());
            }
        }

    }