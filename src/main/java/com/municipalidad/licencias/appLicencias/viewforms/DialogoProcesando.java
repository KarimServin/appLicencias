package com.municipalidad.licencias.appLicencias.viewforms;

import javax.swing.*;
import java.awt.*;

/**
 * Diálogo modal que muestra "Procesando..." y bloquea la interacción
 * sin congelar la interfaz (se muestra desde el EDT, el trabajo pesado
 * corre en un SwingWorker).
 */
public class DialogoProcesando extends JDialog {

    private final JLabel labelMensaje;

    public DialogoProcesando(Component parent, String mensaje) {
        super(SwingUtilities.getWindowAncestor(parent), "Procesando", ModalityType.APPLICATION_MODAL);
        setUndecorated(true);
        setSize(320, 80);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        labelMensaje = new JLabel(mensaje, SwingConstants.CENTER);
        labelMensaje.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        labelMensaje.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(51, 153, 255), 2),
            BorderFactory.createEmptyBorder(15, 25, 15, 25)
        ));
        add(labelMensaje, BorderLayout.CENTER);
    }

    public void actualizarMensaje(String mensaje) {
        SwingUtilities.invokeLater(() -> labelMensaje.setText(mensaje));
    }
}