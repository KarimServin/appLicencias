
package com.municipalidad.licencias.appLicencias.ui;

import com.municipalidad.licencias.appLicencias.controller.UsuarioController;
import com.municipalidad.licencias.appLicencias.dto.AltaUsuarioDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.exception.ValidationException;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import com.municipalidad.licencias.appLicencias.navigation.BackToMenuListener;
import java.awt.HeadlessException;

public class CrearUsuarioView extends javax.swing.JFrame {
    
    private final UsuarioController usuarioController;
    private final BackToMenuListener backToMenuListener;
    
    private static final int PASS_MIN = 8;
    private static final int PASS_MAX = 16;
    

    public CrearUsuarioView(UsuarioController usuarioController, BackToMenuListener backToMenuListener) {
        
        this.usuarioController = usuarioController;
        this.backToMenuListener = backToMenuListener;
        
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png")));
        this.setLocationRelativeTo(null);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEncabezado = new javax.swing.JPanel();
        labelMenuPrincipal = new javax.swing.JLabel();
        labelLogoSF = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        usuarioField = new javax.swing.JTextField();
        labelContrasenia = new javax.swing.JLabel();
        privilegiosCheckBox = new javax.swing.JCheckBox();
        aceptarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();
        fieldPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(482, 176));

        labelMenuPrincipal.setText("Alta de usuario - Sistema de gestión de licencias");

        labelLogoSF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLogoSF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
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

        labelUsuario.setText("Usuario");

        labelContrasenia.setText("Contraseña");

        privilegiosCheckBox.setText("Conceder privilegios de Superusuario");

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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(aceptarButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(privilegiosCheckBox)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelUsuario)
                                .addGap(32, 32, 32)
                                .addComponent(usuarioField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelContrasenia)
                                .addGap(18, 18, 18)
                                .addComponent(fieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelUsuario)
                    .addComponent(usuarioField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelContrasenia)
                    .addComponent(fieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(privilegiosCheckBox)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarButton)
                    .addComponent(cancelarButton))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        backToMenuListener.mostrarMenuPrincipal();
        this.dispose();
    }//GEN-LAST:event_cancelarButtonActionPerformed

    private void aceptarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarButtonActionPerformed
        
        
    
        if (!validarCamposUsuario()) 
            return;
        
        AltaUsuarioDTO altaUsuarioDTO = new AltaUsuarioDTO(usuarioField.getText(),fieldPassword.getPassword(), privilegiosCheckBox.isSelected());
                
        try {
            
        usuarioController.altaUsuario(altaUsuarioDTO);
        JOptionPane.showMessageDialog(null, "Se ha creado el nuevo usuario con éxito");
        backToMenuListener.mostrarMenuPrincipal();
        this.dispose();
        
        } catch (ServiceException | ValidationException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
        
        }
        
    }//GEN-LAST:event_aceptarButtonActionPerformed

    private boolean validarCamposUsuario() {
        String nombreUsuario = usuarioField.getText().trim();
        char[] passwordChars = fieldPassword.getPassword();
        String password = new String(passwordChars).trim();

        if (nombreUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(
                null,
                "El campo usuario no puede estar vacío.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        if (password.isEmpty()) {
            JOptionPane.showMessageDialog(
                null,
                "El campo contraseña no puede estar vacío.",
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        return true;
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JPasswordField fieldPassword;
    private javax.swing.JLabel labelContrasenia;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JLabel labelMenuPrincipal;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JCheckBox privilegiosCheckBox;
    private javax.swing.JTextField usuarioField;
    // End of variables declaration//GEN-END:variables
}
