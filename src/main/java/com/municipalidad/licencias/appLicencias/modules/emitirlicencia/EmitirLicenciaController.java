package com.municipalidad.licencias.appLicencias.modules.emitirlicencia;

import com.municipalidad.licencias.appLicencias.dto.ComprobanteDTO;
import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.exception.ImpresionCanceladaException;
import com.municipalidad.licencias.appLicencias.service.ComprobanteService;
import com.municipalidad.licencias.appLicencias.service.LicenciaService;
import com.municipalidad.licencias.appLicencias.service.PrintService;
import com.municipalidad.licencias.appLicencias.service.TitularService;
import com.municipalidad.licencias.appLicencias.viewforms.DialogoProcesando;
import com.municipalidad.licencias.appLicencias.viewforms.Dialogs;
import java.util.Optional;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class EmitirLicenciaController {

    private static final Logger logger = LoggerFactory.getLogger(EmitirLicenciaController.class);
    private Long dniValidado;
    private TitularDTO titularActual;

    private final TitularService titularService;
    private final LicenciaService licenciaService;
    private final ComprobanteService comprobanteService;
    private final PrintService printService;
    private EmitirLicenciaView view;
    
    @Autowired
    public EmitirLicenciaController(TitularService titularService,
                                    LicenciaService licenciaService,
                                    ComprobanteService comprobanteService,
                                    PrintService printService) {
        this.titularService = titularService;
        this.licenciaService = licenciaService;
        this.comprobanteService = comprobanteService;
        this.printService = printService;
        logger.debug("EmitirLicenciaController instanciado.");
    }

    public void display() {
        logger.info("Iniciando visualización de EmitirLicenciaView.");
        SwingUtilities.invokeLater(() -> {
            view = new EmitirLicenciaView();
            setListeners();
            view.setVisible(true);
            logger.info("EmitirLicenciaView visible.");
        });
    }

    private void setListeners() {
        logger.debug("Registrando listeners en la vista.");
        view.setEmitirAction(e -> aceptar());
        view.setBuscarTitularAction(e -> buscarTitular());
        view.setLimpiarAction(e -> limpiar());
        view.setCancelarAction(e -> cancelar());
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
                view.setInfoTitular(titularActual);

                licenciaService.obtenerUltimaLicencia(dni).ifPresentOrElse(
                    licencia -> view.setUltimaLicencia(licencia),
                    () -> view.setUltimaLicencia(null)
                );

                view.activarCamposTitular();
            } else {
                logger.warn("No se encontró titular con DNI: {}", dni);
                Dialogs.error(view, "No se encontró un titular con ese DNI.");
            }

        } catch (NumberFormatException e) {
            logger.warn("DNI con formato inválido: {}", dniTexto);
            Dialogs.error(view, "El DNI ingresado no es válido.");
        }
    }

    private void aceptar() {
        logger.info("Iniciando emisión de licencia para DNI: {}", dniValidado);

        // ── Validaciones rápidas (en el EDT, está bien) ──
        var clases = view.getClasesSeleccionadas();
        if (clases.isEmpty()) {
            Dialogs.error(view, "Debe seleccionar al menos una clase de licencia.");
            return;
        }

        // Capturar datos de la vista ANTES de salir del EDT
        boolean mantenerDatos = view.isMantenerDatos();
        ActualizarTitularRequestDTO datosActualizados = null;
        if (!mantenerDatos) {
            datosActualizados = view.getDatosTitular();
            datosActualizados.setDni(dniValidado);
        }

        // ── Mostrar "Procesando..." y ejecutar en background ──
        DialogoProcesando dialogo = new DialogoProcesando(view, "Emitiendo licencia, por favor espere...");

        final ActualizarTitularRequestDTO datosFinal = datosActualizados;

        SwingWorker<ResultadoEmision, String> worker = new SwingWorker<>() {

            @Override
            protected ResultadoEmision doInBackground() throws Exception {
                // Todo esto corre FUERA del EDT

                if (datosFinal != null) {
                    publish("Actualizando datos del titular...");
                    titularService.actualizarDatosTitular(datosFinal);
                }

                publish("Calculando costos...");
                int vigencia = licenciaService.calcularVigencia(dniValidado);
                int monto = clases.stream()
                        .mapToInt(c -> licenciaService.calcularCosto(c, false, vigencia))
                        .sum();

                publish("Emitiendo licencia...");
                EmisionLicenciaDTO emisionDTO = new EmisionLicenciaDTO(dniValidado, clases);
                LicenciaDTO licenciaEmitida = licenciaService.emitirLicencia(emisionDTO);

                publish("Generando comprobante...");
                ComprobanteDTO comprobante = comprobanteService.generarComprobante(
                        licenciaEmitida, monto, "EMISIÓN"
                );

                return new ResultadoEmision(licenciaEmitida, comprobante);
            }

            @Override
            protected void process(java.util.List<String> chunks) {
                // Actualizar el mensaje del diálogo (corre en EDT)
                dialogo.actualizarMensaje(chunks.get(chunks.size() - 1));
            }

            @Override
            protected void done() {
                dialogo.dispose(); // Cerrar el "Procesando..."
                try {
                    ResultadoEmision resultado = get();

                    // Imprimir (printDialog necesita el EDT, esto está bien)
                    try {
                        printService.imprimirComprobante(titularActual, resultado.licencia, resultado.comprobante);
                        printService.imprimirLicencia(titularActual, resultado.licencia);
                    } catch (ImpresionCanceladaException e) {
                        manejarErrorImpresion(view, resultado.licencia, resultado.comprobante,
                                "No se pudo imprimir.\n¿Desea intentar imprimir nuevamente?");
                    } catch (RuntimeException e) {
                        manejarErrorImpresion(view, resultado.licencia, resultado.comprobante,
                                "Error al imprimir.\n¿Desea intentar imprimir nuevamente?");
                    }

                    Dialogs.exito(view, "La licencia fue emitida correctamente.");
                    view.dispose();

                } catch (Exception e) {
                    Throwable cause = (e.getCause() != null) ? e.getCause() : e;
                    logger.error("Error al emitir licencia: {}", cause.getMessage(), cause);
                    Dialogs.error(view, cause.getMessage());
                }
            }
        };

        worker.execute();
        dialogo.setVisible(true); // Modal: bloquea hasta que el worker haga dispose()
    }

    // DTO interno simple para pasar el resultado
    private record ResultadoEmision(LicenciaDTO licencia, ComprobanteDTO comprobante) {}

    private void manejarErrorImpresion(EmitirLicenciaView view,
                                       LicenciaDTO licenciaEmitida,
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
                printService.imprimirComprobante(titularActual, licenciaEmitida, comprobante);
                printService.imprimirLicencia(titularActual, licenciaEmitida);
            } catch (Exception ex) {
                logger.error("Segundo intento de impresión fallido para DNI: {}", dniValidado, ex);
                Dialogs.advertencia(view,
                    "No se pudo imprimir en el segundo intento.\n" +
                    "La licencia quedó registrada con ID: " + licenciaEmitida.getId() + "\n" +
                    "N° de comprobante: " + comprobante.getId()
                );
            }
        } else {
            Dialogs.advertencia(view,
                "La licencia quedó registrada en el sistema.\n" +
                "ID Licencia: " + licenciaEmitida.getId() + "\n" +
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