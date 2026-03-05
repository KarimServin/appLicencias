package com.municipalidad.licencias.appLicencias.modules.altausuario;

import com.municipalidad.licencias.appLicencias.viewforms.Dialogs;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

public class AltaUsuarioView extends javax.swing.JFrame {

    private static final int PASS_MIN = 8;
    private static final int PASS_MAX = 16;

    public AltaUsuarioView() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png")));
        this.setLocationRelativeTo(null);
        configurarVerPassword();
    }

    // ── Listeners ──

    public void setCancelarAction(ActionListener listener) {
        cancelarButton.addActionListener(listener);
    }

    public void setAceptarAction(ActionListener listener) {
        aceptarButton.addActionListener(listener);
    }

    // ── Getters ──

    public String getNombreUsuario() {
        return nombreUsuarioTextField.getText().trim();
    }

    public String getNombre() {
        return nombreTextField.getText().trim();
    }

    public String getApellido() {
        return apellidoTextField.getText().trim();
    }

    public String getEmail() {
        return emailTextField.getText().trim();
    }

    public char[] getPassword() {
        return contraseniaTextField.getPassword();
    }

    public boolean isAdmin() {
        return privilegiosCheckBox.isSelected();
    }

    // ── Botón ver/ocultar contraseña ──

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

        String password = new String(getPassword()).trim();

        if (password.isEmpty()) {
            Dialogs.error(this, "El campo contraseña no puede estar vacío.");
            return false;
        }

        if (password.length() < PASS_MIN || password.length() > PASS_MAX) {
            Dialogs.error(this, "La contraseña debe tener entre " + PASS_MIN + " y " + PASS_MAX + " caracteres.");
            return false;
        }

        return true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        panelEncabezado = new javax.swing.JPanel();
        labelLogoSF = new javax.swing.JLabel();
        labelMenuPrincipal = new javax.swing.JLabel();
        instruccionesLabel = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        labelUsuario = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        labelContrasenia = new javax.swing.JLabel();
        contraseniaTextField = new javax.swing.JPasswordField();
        verPasswordButton = new javax.swing.JButton();
        privilegiosCheckBox = new javax.swing.JCheckBox();
        nombrePersonaLabel = new javax.swing.JLabel();
        apellidoLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        nombreUsuarioTextField = new javax.swing.JTextField();
        nombreTextField = new javax.swing.JTextField();
        apellidoTextField = new javax.swing.JTextField();
        aceptarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(482, 176));

        labelLogoSF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N

        labelMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        labelMenuPrincipal.setText("ALTA DE USUARIO");

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMenuPrincipal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                .addComponent(labelLogoSF)
                .addContainerGap())
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLogoSF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        instruccionesLabel.setText("Ingrese las credenciales de acceso para el nuevo usuario.");

        labelUsuario.setText("Nombre usuario:");

        labelContrasenia.setText("Contraseña:");

        verPasswordButton.setText("👁");

        privilegiosCheckBox.setText("Conceder privilegios de Superusuario");

        nombrePersonaLabel.setText("Nombre:");

        apellidoLabel.setText("Apellido:");

        emailLabel.setText("Email:");

        aceptarButton.setText("Aceptar");

        cancelarButton.setText("Cancelar");

        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(contraseniaTextField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(verPasswordButton))
                            .addComponent(emailTextField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(privilegiosCheckBox)))
                .addGap(52, 52, 52))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aceptarButton)
                .addContainerGap())
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contraseniaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelContrasenia)
                    .addComponent(verPasswordButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(privilegiosCheckBox)
                .addGap(34, 34, 34)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarButton)
                    .addComponent(cancelarButton))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(instruccionesLabel)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(instruccionesLabel)
                .addGap(27, 27, 27)
                .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JLabel apellidoLabel;
    private javax.swing.JTextField apellidoTextField;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JPasswordField contraseniaTextField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel instruccionesLabel;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel labelContrasenia;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JLabel labelMenuPrincipal;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JLabel nombrePersonaLabel;
    private javax.swing.JTextField nombreTextField;
    private javax.swing.JTextField nombreUsuarioTextField;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JCheckBox privilegiosCheckBox;
    private javax.swing.JButton verPasswordButton;
    // End of variables declaration//GEN-END:variables
}
