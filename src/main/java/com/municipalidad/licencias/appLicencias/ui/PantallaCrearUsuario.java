
package com.municipalidad.licencias.appLicencias.ui;

import com.municipalidad.licencias.appLicencias.controller.UsuarioController;
import com.municipalidad.licencias.appLicencias.singleton.SesionMenuPrincipal;
import javax.swing.JOptionPane;

public class PantallaCrearUsuario extends javax.swing.JFrame {
    private UsuarioController usuarioController;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PantallaCrearUsuario.class.getName());

    public PantallaCrearUsuario() {}
    public PantallaCrearUsuario(UsuarioController usuarioControl) {
        this.usuarioController=usuarioControl;
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEncabezado = new javax.swing.JPanel();
        labelMenuPrincipal = new javax.swing.JLabel();
        labelLogoSF = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        usuarioField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        contraseñaField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        privilegiosCheckBox = new javax.swing.JCheckBox();
        aceptarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(482, 176));

        labelMenuPrincipal.setText("Crear Usuario Administrador - Sistema de gestión de licencias");

        labelLogoSF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLogoSF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMenuPrincipal)
                .addContainerGap())
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelLogoSF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setText("Usuario");

        usuarioField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                usuarioFieldFocusLost(evt);
            }
        });

        jLabel2.setText("Contraseña");

        contraseñaField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                contraseñaFieldFocusLost(evt);
            }
        });

        jLabel3.setText("Privilegios");

        privilegiosCheckBox.setText("Si / No");

        aceptarButton.setText("Aceptar");
        aceptarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarButtonActionPerformed(evt);
            }
        });

        cancelarButton.setText("Cancelar");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(privilegiosCheckBox)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(usuarioField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(29, 29, 29)
                                .addComponent(contraseñaField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cancelarButton)
                                .addGap(18, 18, 18)
                                .addComponent(aceptarButton))
                            .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(usuarioField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(contraseñaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(privilegiosCheckBox))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarButton)
                    .addComponent(cancelarButton))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void contraseñaFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contraseñaFieldFocusLost
        if(contraseñaField.getText().trim() == null || contraseñaField.getText().trim().isBlank()){
            JOptionPane.showMessageDialog(null, 
                    "El campo es obligatorio", 
                    "Error de validación", 
                    JOptionPane.ERROR_MESSAGE);
            contraseñaField.requestFocus();
        } else{
            String cont = contraseñaField.getText();
            if (cont.length() < 8 && cont.length() > 16) {
                JOptionPane.showMessageDialog(
                        null, 
                        "La contraseña debe tener entre 8 y 16 caracteres.", 
                        "Error de validación", 
                        JOptionPane.ERROR_MESSAGE);
                contraseñaField.requestFocus();
            }
        }
    }//GEN-LAST:event_contraseñaFieldFocusLost

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        SesionMenuPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelarButtonActionPerformed

    private void aceptarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarButtonActionPerformed
        //crear el usuario
    }//GEN-LAST:event_aceptarButtonActionPerformed

    private void usuarioFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usuarioFieldFocusLost
        if(usuarioField.getText().trim() == null || usuarioField.getText().trim().isBlank()){
            JOptionPane.showMessageDialog(null, 
                    "El campo es obligatorio", 
                    "Error de validación", 
                    JOptionPane.ERROR_MESSAGE);
            usuarioField.requestFocus();
        } else {
            if(usuarioController.obtenerUsuario(usuarioField.getText().trim()) != null){
                JOptionPane.showMessageDialog(null, 
                        "El nombre de usuario no está disponible", 
                        "Error de validación", 
                        JOptionPane.ERROR_MESSAGE);
                usuarioField.requestFocus();
            }
        }
    }//GEN-LAST:event_usuarioFieldFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new PantallaCrearUsuario().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JTextField contraseñaField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JLabel labelMenuPrincipal;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JCheckBox privilegiosCheckBox;
    private javax.swing.JTextField usuarioField;
    // End of variables declaration//GEN-END:variables
}
