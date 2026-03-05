package com.municipalidad.licencias.appLicencias.modules.altatitular;


import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.exception.ValidationException;
import com.municipalidad.licencias.appLicencias.service.TitularService;
import com.municipalidad.licencias.appLicencias.validation.TitularValidator;
import com.municipalidad.licencias.appLicencias.validation.ValidationResult;
import com.municipalidad.licencias.appLicencias.viewforms.Dialogs;
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
        logger.debug("AltaTitularController instanciado correctamente.");
    }

    public void display() {
        logger.info("Iniciando visualización de AltaTitularView.");
        SwingUtilities.invokeLater(() -> {
            logger.debug("Creando instancia de AltaTitularView en el EDT.");
            view = new AltaTitularView();
            setListeners();
            view.setVisible(true);
            logger.info("AltaTitularView visible.");
        });
    }

    private void setListeners() {
        logger.debug("Registrando listeners en la vista.");
        view.setAceptarAction(e -> procesarAltaTitular());
        view.setCancelarAction(e -> cancelar());
    }

    private void cancelar() {
        logger.info("Operación cancelada por el usuario. Cerrando vista.");
        view.dispose();
    }

    private void procesarAltaTitular() {
        logger.info("Iniciando procesamiento de alta de titular.");
        try {
            logger.debug("Obteniendo datos del formulario.");
            TitularDTO titularDTO = view.getBuildTitularDTO();

            logger.debug("Validando datos del titular: {}", titularDTO);
            ValidationResult validation = titularValidator.validarTitular(titularDTO);

            if (!validation.isValid()) {
                logger.warn("Validación fallida: {}", validation.getFirstError());
                Dialogs.error(view, validation.getFirstError());
                return;
            }

            logger.debug("Datos válidos. Guardando titular en el servicio.");
            titularService.guardarTitular(titularDTO);

            logger.info("Titular registrado con éxito.");
            Dialogs.exito(view, "El titular ha sido registrado con éxito.");
            view.dispose();

        } catch (ValidationException e) {
            logger.warn("Error de validación: {}", e.getMessage());
            Dialogs.error(view, "Error de validación: " + e.getMessage());

        } catch (DateTimeParseException e) {
            logger.warn("Error en formato de fecha: {}", e.getMessage());
            Dialogs.error(view, e.getMessage());

        } catch (NumberFormatException e) {
            logger.warn("Error en formato numérico: {}", e.getMessage());
            Dialogs.error(view, e.getMessage());

        } catch (ServiceException e) {
            logger.error("Error de servicio: {}", e.getMessage());
            Dialogs.error(view, e.getMessage());

        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage(), e);
            Dialogs.error(view, e.getMessage());
        }
    }
}