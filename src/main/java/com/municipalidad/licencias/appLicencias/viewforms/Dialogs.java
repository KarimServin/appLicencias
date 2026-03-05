package com.municipalidad.licencias.appLicencias.viewforms;

public final class Dialogs {
    
    private Dialogs() {}

    public static void error(java.awt.Component parent, String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(
            parent,
            mensaje,
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE
        );
    }

    public static void exito(java.awt.Component parent, String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(
            parent,
            mensaje,
            "Éxito",
            javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
    }
    public static void info(java.awt.Component view, String message) {
        javax.swing.JOptionPane.showMessageDialog(
                view,
                message,
                "Información",
                javax.swing.JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    public static void advertencia(java.awt.Component parent, String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(
            parent,
            mensaje,
            "Advertencia",
            javax.swing.JOptionPane.WARNING_MESSAGE
        );
    }
}