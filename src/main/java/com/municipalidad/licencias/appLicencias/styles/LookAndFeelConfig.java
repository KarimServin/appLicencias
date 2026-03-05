package com.municipalidad.licencias.appLicencias;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ContainerEvent;
import java.beans.PropertyChangeListener;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;

public final class LookAndFeelConfig {

    private LookAndFeelConfig() {}

    private static final Color DISABLED_BG = new Color(230, 230, 230);
    private static final Color DISABLED_FG = new Color(120, 120, 120);

    public static void aplicar() throws Exception {

        UIManager.setLookAndFeel(new FlatLightLaf());

        configurarForma();
        configurarFuente();
        configurarColoresDisabled();
        configurarColoresInactive();
        registrarListenerEditableGlobal();
    }

    private static void configurarForma() {
        UIManager.put("Component.arc", 14);
        UIManager.put("Button.arc", 14);
        UIManager.put("TextComponent.arc", 10);
        UIManager.put("Component.focusWidth", 1);
        UIManager.put("Component.innerFocusWidth", 0);
        UIManager.put("Button.minimumHeight", 36);
        UIManager.put("TextComponent.minimumHeight", 34);
        UIManager.put("Component.arrowType", "triangle");
        UIManager.put("ScrollBar.showButtons", false);
    }

    private static void configurarFuente() {
        UIManager.put("defaultFont", new Font("Segoe UI", Font.PLAIN, 14));
    }

    private static void configurarColoresDisabled() {
        // setEnabled(false)
        UIManager.put("TextComponent.disabledBackground", DISABLED_BG);
        UIManager.put("TextComponent.disabledForeground", DISABLED_FG);
        UIManager.put("TextField.disabledBackground", DISABLED_BG);
        UIManager.put("TextField.disabledForeground", DISABLED_FG);
        UIManager.put("FormattedTextField.disabledBackground", DISABLED_BG);
        UIManager.put("PasswordField.disabledBackground", DISABLED_BG);
        UIManager.put("ComboBox.disabledBackground", DISABLED_BG);
        UIManager.put("ComboBox.disabledForeground", DISABLED_FG);
        UIManager.put("Button.disabledBackground", new Color(210, 210, 210));
        UIManager.put("Button.disabledText", new Color(130, 130, 130));
    }

    private static void configurarColoresInactive() {
        // setEditable(false) → fondo
        UIManager.put("TextField.inactiveBackground", DISABLED_BG);
        UIManager.put("FormattedTextField.inactiveBackground", DISABLED_BG);
        UIManager.put("PasswordField.inactiveBackground", DISABLED_BG);
        UIManager.put("TextArea.inactiveBackground", DISABLED_BG);
    }

    /**
     * Listener global que aplica foreground gris a cualquier JTextComponent
     * cuando se le hace setEditable(false), y restaura el color original
     * cuando vuelve a setEditable(true).
     *
     * Esto resuelve que FlatLaf NO cambia el color del texto con editable=false.
     */
    private static void registrarListenerEditableGlobal() {

        PropertyChangeListener editableListener = evt -> {
            if ("editable".equals(evt.getPropertyName())) {
                Object source = evt.getSource();
                if (source instanceof JTextComponent tc) {
                    if (Boolean.FALSE.equals(evt.getNewValue())) {
                        tc.setForeground(DISABLED_FG);
                    } else {
                        tc.setForeground(UIManager.getColor("TextField.foreground"));
                    }
                }
            }
        };

        Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
            if (event instanceof ContainerEvent ce
                    && ce.getID() == ContainerEvent.COMPONENT_ADDED) {
                Component child = ce.getChild();
                if (child instanceof JTextComponent tc) {
                    tc.addPropertyChangeListener("editable", editableListener);
                    // Aplicar estado actual si ya viene con editable=false
                    if (!tc.isEditable()) {
                        tc.setForeground(DISABLED_FG);
                    }
                }
            }
        }, AWTEvent.CONTAINER_EVENT_MASK);
    }
}