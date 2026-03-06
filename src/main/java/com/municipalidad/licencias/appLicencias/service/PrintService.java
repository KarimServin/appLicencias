package com.municipalidad.licencias.appLicencias.service;

import com.municipalidad.licencias.appLicencias.dto.ComprobanteDTO;
import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.exception.ImpresionCanceladaException;
import com.municipalidad.licencias.appLicencias.viewforms.LicenciaPDF;
import com.municipalidad.licencias.appLicencias.viewforms.PresupuestoPDF;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.print.PrintServiceLookup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PrintService {

    private static final Logger logger = LoggerFactory.getLogger(PrintService.class);

    // Hilo dedicado para operaciones de impresión pesadas
    private final ExecutorService printExecutor = Executors.newSingleThreadExecutor(r -> {
        Thread t = new Thread(r, "print-worker");
        t.setDaemon(true);
        return t;
    });

    // Latch para esperar a que el warmup termine antes de la primera impresión real
    private final CountDownLatch warmupLatch = new CountDownLatch(1);

    public PrintService() {
        precargarImagenes();
        precalentarImpresion();
    }

    /**
     * Pre-calienta el subsistema de impresión en el hilo de impresión.
     * Usa el mismo ExecutorService para que el warmup bloquee cualquier
     * impresión real hasta que termine (por la cola del SingleThreadExecutor).
     */
    private void precalentarImpresion() {
        printExecutor.submit(() -> {
            try {
                logger.info("Pre-calentando subsistema de impresión...");
                long inicio = System.currentTimeMillis();

                // Forzar enumeración de impresoras (la parte más lenta)
                var servicios = PrintServiceLookup.lookupPrintServices(null, null);
                logger.debug("Impresoras encontradas: {}", servicios.length);

                // Forzar inicialización de PrinterJob
                PrinterJob.getPrinterJob();

                long fin = System.currentTimeMillis();
                logger.info("Subsistema de impresión listo en {} ms", (fin - inicio));
            } catch (Exception e) {
                logger.warn("No se pudo pre-calentar impresión: {}", e.getMessage());
            } finally {
                warmupLatch.countDown();
            }
        });
    }

    private void precargarImagenes() {
        String[] imagenes = {
            "/img/FondoAzul.png",
            "/img/argentina (1).png",
            "/img/argentina (2).png",
            "/img/escudo-de-armas (3).png",
            "/img/escudo-de-armas (4).png"
        };
        for (String path : imagenes) {
            var url = getClass().getResource(path);
            if (url != null) {
                new javax.swing.ImageIcon(url).getImage()
                    .getScaledInstance(1, 1, java.awt.Image.SCALE_DEFAULT);
            }
        }
    }

    public void imprimirLicencia(TitularDTO titular, LicenciaDTO licencia) {
        LicenciaPDF panel = new LicenciaPDF(titular, licencia);
        prepararPanel(panel, 470, 380);
        imprimir(panel, "Licencia de Conducir");
    }

    public void imprimirComprobante(TitularDTO titular, LicenciaDTO licencia,
                                    ComprobanteDTO comprobante) {
        PresupuestoPDF panel = new PresupuestoPDF(titular, licencia, comprobante);
        prepararPanel(panel, 595, 842);
        imprimir(panel, "Comprobante de Pago");
    }

    private void prepararPanel(javax.swing.JPanel panel, int width, int height) {
        panel.setSize(width, height);
        panel.doLayout();
        panel.validate();
    }

    
    private void imprimir(java.awt.print.Printable printable, String jobName) {
        logger.info("Iniciando impresión: {}", jobName);
        long inicio = System.currentTimeMillis();

        PrinterJob job = PrinterJob.getPrinterJob();
        logger.debug("PrinterJob obtenido en {} ms", System.currentTimeMillis() - inicio);

        job.setJobName(jobName);
        job.setPrintable(printable);

        if (!job.printDialog()) {
            throw new ImpresionCanceladaException("Impresión cancelada por el usuario: " + jobName);
        }

        try {
            long inicioPrint = System.currentTimeMillis();
            job.print();
            logger.info("Impresión '{}' completada en {} ms", jobName, System.currentTimeMillis() - inicioPrint);
        } catch (PrinterException e) {
            throw new RuntimeException("Error al imprimir: " + jobName, e);
        }
    }
}