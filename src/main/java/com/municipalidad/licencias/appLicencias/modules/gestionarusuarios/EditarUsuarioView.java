package com.municipalidad.licencias.appLicencias.modules.gestionarusuarios;

import com.municipalidad.licencias.appLicencias.viewforms.Dialogs;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

public class EditarUsuarioView extends javax.swing.JFrame {

    private static final int PASS_MIN = 8;
    private static final int PASS_MAX = 16;

    public EditarUsuarioView() {
        initComponents();
        postInitUi();
    }

    private void postInitUi() {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(
                getClass().getResource("/img/SantaFeCapital_Logo.png")
            ));
        } catch (Exception ignored) {}

        configurarVerPassword();
    }

    private void configurarVerPassword() {
        char echoOriginal = contraseniaTextField.getEchoChar();
        verPasswordButton.addActionListener(e -> {
            if (contraseniaTextField.getEchoChar() == 0) {
                contraseniaTextField.setEchoChar(echoOriginal);
                verPasswordButton.setText("👁");
            } else {
                contraseniaTextField.setEchoChar((char) 0);
                verPasswordButton.setText("🔒");
            }
        });
    }

    // ── Listeners ──

    public void setEditarAction(ActionListener l) { aceptarButton.addActionListener(l); }
    public void setCancelarAction(ActionListener l) { cancelarButton.addActionListener(l); }

    // ── Getters ──

    public String getNombreUsuario() { return nombreUsuarioTextField.getText().trim(); }
    public String getNombre() { return nombreTextField.getText().trim(); }
    public String getApellido() { return apellidoTextField.getText().trim(); }
    public String getEmail() { return emailTextField.getText().trim(); }
    public char[] getPassword() { return contraseniaTextField.getPassword(); }

    // ── Setters (precargar datos) ──

    public void setNombreUsuario(String valor) { nombreUsuarioTextField.setText(valor); }
    public void setNombre(String valor) { nombreTextField.setText(valor); }
    public void setApellido(String valor) { apellidoTextField.setText(valor); }
    public void setEmail(String valor) { emailTextField.setText(valor != null ? valor : ""); }

    // ── Habilitar/deshabilitar ──

    public void setEditarEnabled(boolean enabled) { aceptarButton.setEnabled(enabled); }

    // ── Validación ──

    public boolean validarCampos() {
        if (getNombreUsuario().isEmpty()) {
            Dialogs.error(this, "El campo nombre de usuario no puede estar vacío.");
            return false;
        }

        if (getNombre().isEmpty()) {
            Dialogs.error(this, "El campo nombre no puede estar vacío.");
            return false;
        }

        if (getApellido().isEmpty()) {
            Dialogs.error(this, "El campo apellido no puede estar vacío.");
            return false;
        }

        // Contraseña: solo validar si ingresó algo (vacía = no cambiar)
        String password = new String(getPassword()).trim();
        if (!password.isEmpty()) {
            if (password.length() < PASS_MIN || password.length() > PASS_MAX) {
                Dialogs.error(this, "La contraseña debe tener entre " + PASS_MIN + " y " + PASS_MAX + " caracteres.");
                return false;
            }
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelDatos = new javax.swing.JPanel();
        labelUsuario = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        labelContrasenia = new javax.swing.JLabel();
        contraseniaTextField = new javax.swing.JPasswordField();
        verPasswordButton = new javax.swing.JButton();
        nombrePersonaLabel = new javax.swing.JLabel();
        apellidoLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        nombreUsuarioTextField = new javax.swing.JTextField();
        nombreTextField = new javax.swing.JTextField();
        apellidoTextField = new javax.swing.JTextField();
        aceptarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelUsuario.setText("Nombre usuario:");

        labelContrasenia.setText("Contraseña:");

        verPasswordButton.setText("👁");

        nombrePersonaLabel.setText("Nombre:");

        apellidoLabel.setText("Apellido:");

        emailLabel.setText("Email:");

        aceptarButton.setText("Editar");

        cancelarButton.setText("Cancelar");

        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosLayout.createSequentialGroup()
                        .addComponent(labelUsuario)
                        .addGap(7, 7, 7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosLayout.createSequentialGroup()
                        .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(apellidoLabel)
                            .addComponent(nombrePersonaLabel)
                            .addComponent(emailLabel)
                            .addComponent(labelContrasenia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreUsuarioTextField)
                    .addComponent(nombreTextField)
                    .addComponent(apellidoTextField)
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addComponent(contraseniaTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(verPasswordButton))
                    .addComponent(emailTextField))
                .addGap(48, 48, 48))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aceptarButton)
                .addGap(16, 16, 16))
        );
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUsuario)
                    .addComponent(nombreUsuarioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombrePersonaLabel)
                    .addComponent(nombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidoLabel)
                    .addComponent(apellidoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contraseniaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelContrasenia)
                    .addComponent(verPasswordButton))
                .addGap(32, 32, 32)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarButton)
                    .addComponent(cancelarButton))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel1.setText("Panel de edición de datos de usuario");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    // Variables declaration - do not modify
    private javax.swing.JButton aceptarButton;
    private javax.swing.JLabel apellidoLabel;
    private javax.swing.JTextField apellidoTextField;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JPasswordField contraseniaTextField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelContrasenia;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JLabel nombrePersonaLabel;
    private javax.swing.JTextField nombreTextField;
    private javax.swing.JTextField nombreUsuarioTextField;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JButton verPasswordButton;
    // End of variables declaration
}