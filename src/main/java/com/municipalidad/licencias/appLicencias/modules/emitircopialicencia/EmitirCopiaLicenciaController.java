package com.municipalidad.licencias.appLicencias.modules.emitircopialicencia;

import com.municipalidad.licencias.appLicencias.dto.ComprobanteDTO;
import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.exception.ImpresionCanceladaException;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
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

        boolean emitirComprobante = view.isEmitirComprobante();

        DialogoProcesando dialogo = new DialogoProcesando(view, "Emitiendo copia, por favor espere...");

        SwingWorker<ResultadoCopia, String> worker = new SwingWorker<>() {

            @Override
            protected ResultadoCopia doInBackground() throws Exception {
                publish("Obteniendo licencia vigente...");
                LicenciaDTO licencia = licenciaService.obtenerCopiaLicenciaVigente(dniValidado);

                publish("Calculando costos...");
                int monto = licencia.getClases().stream()
                        .mapToInt(c -> licenciaService.calcularCosto(c, true, 0))
                        .sum();

                publish("Generando comprobante...");
                ComprobanteDTO comprobante = comprobanteService.generarComprobante(
                        licencia, monto, "DUPLICADO"
                );

                return new ResultadoCopia(licencia, comprobante);
            }

            @Override
            protected void process(java.util.List<String> chunks) {
                dialogo.actualizarMensaje(chunks.get(chunks.size() - 1));
            }

            @Override
            protected void done() {
                dialogo.dispose();
                try {
                    ResultadoCopia resultado = get();

                    try {
                        if (emitirComprobante) {
                            printService.imprimirComprobante(titularActual, resultado.licencia, resultado.comprobante);
                        }
                        printService.imprimirLicencia(titularActual, resultado.licencia);
                    } catch (ImpresionCanceladaException e) {
                        manejarErrorImpresion(resultado.licencia, resultado.comprobante,
                                "No se pudo imprimir.\n¿Desea intentar imprimir nuevamente?");
                    } catch (RuntimeException e) {
                        manejarErrorImpresion(resultado.licencia, resultado.comprobante,
                                "Error al imprimir.\n¿Desea intentar imprimir nuevamente?");
                    }

                    Dialogs.exito(view, "La copia fue emitida correctamente.");
                    view.dispose();

                } catch (Exception e) {
                    Throwable cause = (e.getCause() != null) ? e.getCause() : e;
                    logger.error("Error al emitir copia: {}", cause.getMessage(), cause);
                    Dialogs.error(view, cause.getMessage());
                }
            }
        };

        worker.execute();
        dialogo.setVisible(true);
    }

    private record ResultadoCopia(LicenciaDTO licencia, ComprobanteDTO comprobante) {}
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