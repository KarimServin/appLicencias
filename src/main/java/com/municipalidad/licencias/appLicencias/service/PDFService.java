/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.municipalidad.licencias.appLicencias.service;

import com.municipalidad.licencias.appLicencias.model.Licencia;
import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.ui.LicenciaPDF;
import com.municipalidad.licencias.appLicencias.ui.PresupuestoPDF;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class PDFService {
     public Printable crearLicenciaPrintable(Titular titular, Licencia licencia) {
        return new LicenciaPDF(titular, licencia);
    }

    public Printable crearComprobantePrintable(Titular titular, Licencia licencia, int monto) {
        return new PresupuestoPDF(titular, licencia, monto);
    }
 
     public void imprimirLicencia(Titular titular,Licencia licencia) {
        LicenciaPDF licenciaPanel = new LicenciaPDF(titular, licencia);
        JFrame frame = new JFrame();
        frame.add(licenciaPanel);
        frame.pack();
        frame.setVisible(true);  // Necesario para que se renderice
        frame.setLocationRelativeTo(null);  // Opcional: centra la ventana
        

// Ahora sí, se puede imprimir
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Imprimir Licencia");
        job.setPrintable(licenciaPanel);

        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al imprimir: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Impresión cancelada.");
        }
        frame.setVisible(false);
    }

    public void imprimirComprobante(Titular titular,Licencia licencia,int Monto) {
        PresupuestoPDF presupuesto = new PresupuestoPDF(titular,licencia,Monto);
        JFrame frame = new JFrame();
        frame.add(presupuesto);
        frame.pack();
        frame.setVisible(true);  // Necesario para que se renderice
        frame.setLocationRelativeTo(null);  // Opcional: centra la ventana

// Ahora sí, se puede imprimir
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Imprimir Comprobante de pago");
        job.setPrintable(presupuesto);

        if (job.printDialog()) {
            try {
                job.print();
                
                 
            } catch (PrinterException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al imprimir: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Impresión cancelada.");
        }
        frame.setVisible(false);
    }

}
