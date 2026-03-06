package com.municipalidad.licencias.appLicencias.modules.modificartitular;

import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.events.OperacionEvent;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.modules.emitirlicencia.ActualizarTitularRequestDTO;
import com.municipalidad.licencias.appLicencias.service.LicenciaService;
import com.municipalidad.licencias.appLicencias.service.TitularService;
import com.municipalidad.licencias.appLicencias.session.SessionInfo;
import com.municipalidad.licencias.appLicencias.viewforms.Dialogs;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ModificarTitularController {

    private static final Logger logger = LoggerFactory.getLogger(ModificarTitularController.class);

    private final TitularService titularService;
    private final LicenciaService licenciaService;
    private final ApplicationEventPublisher eventPublisher;
    private final SessionInfo sessionInfo;
    private ModificarTitularView view;
    private Long dniValidado;
    private String domicilioOriginal;

    @Autowired
    public ModificarTitularController(TitularService titularService,
                                      LicenciaService licenciaService,
                                      ApplicationEventPublisher eventPublisher,
                                      SessionInfo sessionInfo) {
        this.titularService = titularService;
        this.licenciaService = licenciaService;
        this.eventPublisher = eventPublisher;
        this.sessionInfo = sessionInfo;
        logger.debug("ModificarTitularController instanciado.");
    }

    public void display() {
        SwingUtilities.invokeLater(() -> {
            view = new ModificarTitularView();
            configurarListeners();
            view.setVisible(true);
        });
    }

    private void configurarListeners() {
        view.setBuscarAction(e -> buscarTitular());
        view.setConfirmarAction(e -> confirmarModificacion());
        view.setLimpiarAction(e -> limpiar());
        view.setCancelarAction(e -> cancelar());
    }

    private void buscarTitular() {
        String dniTexto = view.getDni();
        logger.info("Buscando titular con DNI: {}", dniTexto);

        if (dniTexto.isEmpty()) {
            Dialogs.error(view, "Debe ingresar un número de DNI.");
            return;
        }

        try {
            Long dni = Long.valueOf(dniTexto);
            Optional<TitularDTO> resultado = titularService.buscarPorDni(dni);

            if (resultado.isPresent()) {
                logger.info("Titular encontrado para DNI: {}", dni);
                this.dniValidado = dni;
                this.domicilioOriginal = resultado.get().getDomicilio();
                view.cargarDatosTitular(resultado.get());
                view.habilitarEdicion();
            } else {
                logger.warn("No se encontró titular con DNI: {}", dni);
                Dialogs.error(view, "No se encontró un titular con ese DNI.");
            }

        } catch (NumberFormatException e) {
            logger.warn("DNI con formato inválido: {}", dniTexto);
            Dialogs.error(view, "El DNI ingresado no es válido.");
        }
    }

    private void confirmarModificacion() {
        logger.info("Confirmando modificación para DNI: {}", dniValidado);

        try {
            ActualizarTitularRequestDTO datos = view.getDatosModificados(dniValidado);

            if (cambioDomicilio(datos)) {
                boolean tieneLicenciaVigente = licenciaService
                        .obtenerUltimaLicencia(dniValidado)
                        .map(lic -> !lic.getFechaVencimiento().isBefore(LocalDate.now()))
                        .orElse(false);

                if (tieneLicenciaVigente) {
                    int opcion = JOptionPane.showConfirmDialog(
                        view,
                        "El titular tiene una licencia vigente.\n\n" +
                        "Al modificar el domicilio, deberá emitir una \n" +
                        "licencia con los datos actualizados.\n\n" +
                        "¿Desea continuar?",
                        "Advertencia — Cambio de domicilio",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                    );

                    if (opcion != JOptionPane.YES_OPTION) {
                        logger.info("Modificación cancelada por el usuario.");
                        return;
                    }
                }
            }

            titularService.actualizarDatosTitular(datos);

            eventPublisher.publishEvent(new OperacionEvent(this,
                sessionInfo.getNombreUsuarioActual(),
                "MODIFICACION_TITULAR",
                "Titular modificado - DNI: " + dniValidado));

            logger.info("Titular modificado correctamente. DNI: {}", dniValidado);
            Dialogs.exito(view, "Los datos del titular fueron modificados correctamente.");
            view.dispose();

        } catch (IllegalArgumentException e) {
            logger.warn("Datos inválidos: {}", e.getMessage());
            Dialogs.error(view, e.getMessage());
        } catch (ServiceException e) {
            logger.warn("Error al modificar titular: {}", e.getMessage());
            Dialogs.error(view, e.getMessage());
        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage(), e);
            Dialogs.error(view, "Ocurrió un error inesperado: " + e.getMessage());
        }
    }
    

    private boolean cambioDomicilio(ActualizarTitularRequestDTO datos) {
        return !Objects.equals(domicilioOriginal, datos.getDomicilio());
    }

    private void limpiar() {
        logger.info("Limpiando formulario.");
        dniValidado = null;
        domicilioOriginal = null;
        view.limpiar();
    }

    private void cancelar() {
        logger.info("Operación cancelada. Cerrando vista.");
        view.dispose();
    }
}