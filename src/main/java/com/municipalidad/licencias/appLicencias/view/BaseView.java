package com.municipalidad.licencias.appLicencias.view;

import javax.swing.JOptionPane;


public abstract class BaseView extends javax.swing.JFrame {
    
    
    public static void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(
            null,
            mensaje,
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
    
    
    public static void mostrarMensajeExito(String mensaje) {
        JOptionPane.showMessageDialog(
            null,
            mensaje,
            "Éxito",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
}
