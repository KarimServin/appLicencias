package com.municipalidad.licencias.appLicencias.modules.emitircopialicencia;

import com.municipalidad.licencias.appLicencias.dto.ComprobanteDTO;
import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.exception.ImpresionCanceladaException;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.modules.emitirlicencia.EmitirLicenciaController;
import com.municipalidad.licencias.appLicencias.service.ComprobanteService;
import com.municipalidad.licencias.appLicencias.service.LicenciaService;
import com.municipalidad.licencias.appLicencias.service.PrintService;
import com.municipalidad.licencias.appLicencias.service.TitularService;
import com.municipalidad.licencias.appLicencias.viewforms.Dialogs;
import java.util.Optional;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmitirCopiaLicenciaController {

    private static final Logger logger = LoggerFactory.getLogger(EmitirCopiaLicenciaController.class);

    private final LicenciaService licenciaService;
    private final TitularService titularService;
    private final ComprobanteService comprobanteService;
    private final PrintService printService;
    private EmitirCopiaLicenciaView view;
    private Long dniValidado;
    private TitularDTO titularActual;

    @Autowired
    public EmitirCopiaLicenciaController(LicenciaService licenciaService,
                                         TitularService titularService,
                                         ComprobanteService comprobanteService,
                                         PrintService printService) {
        this.licenciaService = licenciaService;
        this.titularService = titularService;
        this.comprobanteService = comprobanteService;
        this.printService = printService;
        logger.debug("EmitirCopiaLicenciaController instanciado.");
    }

    public void display() {
        SwingUtilities.invokeLater(() -> {
            view = new EmitirCopiaLicenciaView();
            configurarListeners();
            view.setVisible(true);
        });
    }

    private void configurarListeners() {
        view.setContinuarAction(e -> buscarTitular());
        view.setEmitirCopiaAction(e -> emitirCopia());
        view.setCancelarAction(e -> cancelar());
        view.setLimpiarAction(e -> limpiar());
    }

    private void buscarTitular() {
        String dniTexto = view.getDni();
        logger.info("Buscando titular con DNI: {}", dniTexto);

        if (dniTexto.isEmpty()) {
            logger.warn("DNI vacío al intentar buscar titular.");
            Dialogs.error(view, "Debe ingresar un número de DNI.");
            return;
        }

        try {
            Long dni = Long.valueOf(dniTexto);
            Optional<TitularDTO> titularDTO = titularService.buscarPorDni(dni);

            if (titularDTO.isPresent()) {
                logger.info("Titular encontrado para DNI: {}", dni);
                this.dniValidado = dni;
                this.titularActual = titularDTO.get();
                view.setNombre(titularActual.getApellido() + ", " + titularActual.getNombre());

                licenciaService.obtenerUltimaLicencia(dni).ifPresentOrElse(
                    licencia -> view.setUltimaLicencia(licencia),
                    () -> view.setUltimaLicencia(null)
                );

                view.habilitarEmision();

            } else {
                logger.warn("No se encontró titular con DNI: {}", dni);
                Dialogs.error(view, "No se encontró un titular con ese DNI.");
            }

        } catch (NumberFormatException e) {
            logger.warn("DNI con formato inválido: {}", dniTexto);
            Dialogs.error(view, "El DNI ingresado no es válido.");
        }
    }

    private void emitirCopia() {
        logger.info("Iniciando emisión de copia para DNI: {}", dniValidado);
        try {
            // 1. Obtener última licencia vigente (lanza excepción si está vencida)
            LicenciaDTO licencia = licenciaService.obtenerCopiaLicenciaVigente(dniValidado);

            // 2. Calcular costo de copia → $50 por clase
            int monto = licencia.getClases().stream()
                    .mapToInt(c -> licenciaService.calcularCosto(c, true, 0))
                    .sum();

            // 3. Generar comprobante
            ComprobanteDTO comprobante = comprobanteService.generarComprobante(
                    licencia, monto, "DUPLICADO"
            );

            // 4. Imprimir
            try {
                if (view.isEmitirComprobante()) {
                    printService.imprimirComprobante(titularActual, licencia, comprobante);
                }
                printService.imprimirLicencia(titularActual, licencia);
            } catch (ImpresionCanceladaException e) {
                logger.warn("Impresión cancelada por el usuario para DNI: {}", dniValidado);
                manejarErrorImpresion(licencia, comprobante,
                        "No se pudo imprimir.\n¿Desea intentar imprimir nuevamente?");
            } catch (RuntimeException e) {
                logger.error("Error de impresora para DNI: {}", dniValidado, e);
                manejarErrorImpresion(licencia, comprobante,
                        "Error al imprimir.\n¿Desea intentar imprimir nuevamente?");
            }

            logger.info("Copia de licencia emitida correctamente para DNI: {}", dniValidado);
            Dialogs.exito(view, "La copia fue emitida correctamente.");
            view.dispose();

        } catch (ServiceException e) {
            logger.warn("Error al emitir copia: {}", e.getMessage());
            Dialogs.error(view, e.getMessage());
        } catch (Exception e) {
            logger.error("Error inesperado al emitir copia: {}", e.getMessage(), e);
            Dialogs.error(view, "Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    private void manejarErrorImpresion(LicenciaDTO licencia,
                                       ComprobanteDTO comprobante,
                                       String mensaje) {
        int opcion = JOptionPane.showConfirmDialog(
            view,
            mensaje,
            "Error de impresión",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (opcion == JOptionPane.YES_OPTION) {
            try {
                if (view.isEmitirComprobante()) {
                    printService.imprimirComprobante(titularActual, licencia, comprobante);
                }
                printService.imprimirLicencia(titularActual, licencia);
            } catch (Exception ex) {
                logger.error("Segundo intento de impresión fallido para DNI: {}", dniValidado, ex);
                Dialogs.advertencia(view,
                    "No se pudo imprimir en el segundo intento.\n" +
                    "La copia quedó registrada con N° Comprobante: " + comprobante.getId()
                );
            }
        } else {
            Dialogs.advertencia(view,
                "La copia quedó registrada en el sistema.\n" +
                "N° Comprobante: " + comprobante.getId()
            );
        }
    }

    private void limpiar() {
        logger.info("Limpiando formulario.");
        dniValidado = null;
        titularActual = null;
        view.limpiar();
    }

    private void cancelar() {
        logger.info("Operación cancelada. Cerrando vista.");
        view.dispose();
    }
}