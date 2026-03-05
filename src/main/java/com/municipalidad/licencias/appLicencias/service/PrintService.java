package com.municipalidad.licencias.appLicencias.service;


import com.municipalidad.licencias.appLicencias.dto.ComprobanteDTO;
import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.exception.ImpresionCanceladaException;
import com.municipalidad.licencias.appLicencias.viewforms.LicenciaPDF;
import com.municipalidad.licencias.appLicencias.viewforms.PresupuestoPDF;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import org.springframework.stereotype.Service;

@Service
public class PrintService {

    public PrintService() {
        precargarImagenes();
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
        mostrarAvisoImpresion("Preparando impresión de licencia...");
        imprimir(panel, "Licencia de Conducir");
    }

    public void imprimirComprobante(TitularDTO titular, LicenciaDTO licencia,
                                    ComprobanteDTO comprobante) {
        PresupuestoPDF panel = new PresupuestoPDF(titular, licencia, comprobante);
        prepararPanel(panel, 595, 842);
        mostrarAvisoImpresion("Preparando impresión de comprobante...");
        imprimir(panel, "Comprobante de Pago");
    }

    /**
     * Fuerza el tamaño y dispara el layout ANTES de imprimir.
     * Sin esto, el JPanel mide 0x0 y printAll() no pinta nada.
     */
    private void prepararPanel(javax.swing.JPanel panel, int width, int height) {
        panel.setSize(width, height);
        panel.doLayout();
        panel.validate();
    }

    private void mostrarAvisoImpresion(String mensaje) {
        JDialog dialogo = new JDialog();
        dialogo.setTitle("Impresión");
        dialogo.setModal(false);
        dialogo.setSize(300, 100);
        dialogo.setLocationRelativeTo(null);
        dialogo.setUndecorated(true);

        JLabel label = new JLabel(mensaje, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(51, 153, 255), 2),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        dialogo.add(label, BorderLayout.CENTER);
        dialogo.setVisible(true);

        // Se cierra solo después de 2 segundos
        Timer timer = new Timer(2000, e -> dialogo.dispose());
        timer.setRepeats(false);
        timer.start();
    }

    private void imprimir(java.awt.print.Printable printable, String jobName) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName(jobName);
        job.setPrintable(printable);

        if (!job.printDialog()) {
            throw new ImpresionCanceladaException("Impresión cancelada por el usuario: " + jobName);
        }

        try {
            job.print();
        } catch (PrinterException e) {
            throw new RuntimeException("Error al imprimir: " + jobName, e);
        }
    }
}