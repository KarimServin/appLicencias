
package com.municipalidad.licencias.appLicencias.ui;

import java.util.List;
import javax.swing.DefaultListModel;
import com.municipalidad.licencias.appLicencias.controller.UsuarioController;
import com.municipalidad.licencias.appLicencias.singleton.SesionMenuPrincipal;
import java.util.Arrays;
import javax.swing.JOptionPane;



public class PantallaModificarUsuario extends javax.swing.JFrame {

    private UsuarioController usuarioController;
    String usuarioSeleccionadoNombre;
    
    public PantallaModificarUsuario(UsuarioController usuarioController) {
        usuarioSeleccionadoNombre = null;
        this.usuarioController = usuarioController;
        initComponents();
        cargarUsuariosVentana();
        this.setLocationRelativeTo(null);
    }
    
    
    private void cargarUsuariosVentana() {
    
        List<String> nombres = usuarioController.obtenerTodosLosNombresDeUsuario();
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (String nombre : nombres) {
        modelo.addElement(nombre);
        }
        listaUsuarios.setModel(modelo);
        }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        labelUsuarioSeleccionado = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnConfirmarSeleccion = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaUsuarios = new javax.swing.JList<>();
        jPanel4 = new javax.swing.JPanel();
        labelNuevoNombre = new javax.swing.JLabel();
        labelNuevoPassword = new javax.swing.JLabel();
        fieldNuevoNombre = new javax.swing.JTextField();
        fieldNuevoPassword = new javax.swing.JPasswordField();
        checkboxConservarNombre = new javax.swing.JCheckBox();
        btnConfirmarModificacion = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        checkboxOtorgarPrivilegios = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Modificar datos de Usuario - Sistema de Gestión de Licencias");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Usuario seleccionado");

        labelUsuarioSeleccionado.setText("No ha seleccionado ningún usuario.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(labelUsuarioSeleccionado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelUsuarioSeleccionado)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Seleccione el usuario cuyos datos desea modificar:");

        btnConfirmarSeleccion.setText("Confirmar selección");
        btnConfirmarSeleccion.setEnabled(false);
        btnConfirmarSeleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarSeleccionActionPerformed(evt);
            }
        });

        listaUsuarios.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaUsuarios.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaUsuariosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listaUsuarios);

        labelNuevoNombre.setText("Ingrese el nuevo nombre de usuario:");
        labelNuevoNombre.setEnabled(false);

        labelNuevoPassword.setText("Ingrese la nueva contraseña:");
        labelNuevoPassword.setEnabled(false);

        fieldNuevoNombre.setEnabled(false);
        fieldNuevoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNuevoNombreActionPerformed(evt);
            }
        });

        fieldNuevoPassword.setEnabled(false);

        checkboxConservarNombre.setText("Conservar nombre de usuario actual");
        checkboxConservarNombre.setEnabled(false);
        checkboxConservarNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxConservarNombreActionPerformed(evt);
            }
        });

        btnConfirmarModificacion.setText("Confirmar Modificación");
        btnConfirmarModificacion.setEnabled(false);
        btnConfirmarModificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarModificacionActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        checkboxOtorgarPrivilegios.setText("Otorgar privilegios de superusuario");
        checkboxOtorgarPrivilegios.setEnabled(false);
        checkboxOtorgarPrivilegios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkboxOtorgarPrivilegiosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(labelNuevoPassword)
                                        .addGap(71, 71, 71))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(labelNuevoNombre)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fieldNuevoNombre)
                                    .addComponent(fieldNuevoPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))
                            .addComponent(btnCancelar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnConfirmarModificacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(checkboxConservarNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(checkboxOtorgarPrivilegios))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNuevoNombre)
                    .addComponent(fieldNuevoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkboxConservarNombre))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNuevoPassword)
                    .addComponent(fieldNuevoPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(checkboxOtorgarPrivilegios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmarModificacion)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnConfirmarSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConfirmarSeleccion)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmarSeleccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarSeleccionActionPerformed
        
        usuarioSeleccionadoNombre = listaUsuarios.getSelectedValue();
        labelUsuarioSeleccionado.setText("Usted ha seleccionado el usuario " + usuarioSeleccionadoNombre);
        fieldNuevoNombre.setEnabled(true);
        fieldNuevoPassword.setEnabled(true);
        checkboxConservarNombre.setEnabled(true);
        labelNuevoNombre.setEnabled(true);
        labelNuevoPassword.setEnabled(true);
        checkboxOtorgarPrivilegios.setEnabled(true);
        fieldNuevoNombre.setText(usuarioSeleccionadoNombre);
        btnConfirmarModificacion.setEnabled(true);
        
        
    }//GEN-LAST:event_btnConfirmarSeleccionActionPerformed

    private void fieldNuevoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNuevoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNuevoNombreActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        SesionMenuPrincipal.setVisible(true);
        this.dispose();        
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void checkboxConservarNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxConservarNombreActionPerformed
       
        fieldNuevoNombre.setEnabled(!checkboxConservarNombre.isSelected());
        
    }//GEN-LAST:event_checkboxConservarNombreActionPerformed

    private void checkboxOtorgarPrivilegiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkboxOtorgarPrivilegiosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkboxOtorgarPrivilegiosActionPerformed

    private void btnConfirmarModificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarModificacionActionPerformed
        String nombreUsuarioFinal;
    // Determinar si el nombre de usuario va a cambiar o se conserva
    if (checkboxConservarNombre.isSelected() || 
        usuarioSeleccionadoNombre.equals(fieldNuevoNombre.getText())) {
        nombreUsuarioFinal = usuarioSeleccionadoNombre;
    } else {
        nombreUsuarioFinal = fieldNuevoNombre.getText();
        // Validar si el nuevo nombre de usuario es válido
        if (!nuevoNombreDeUsuarioEsValido(nombreUsuarioFinal)) {
            return; // Si no es válido, se interrumpe el flujo
        }
    }
    // Obtener contraseña (validación más adelante)
    String nuevoPassword = new String(fieldNuevoPassword.getPassword());
    
    if (!contraseniaEsValida(nuevoPassword)) return;
    // Realizar la actualización
    try {
        usuarioController.actualizarUsuario(
            usuarioSeleccionadoNombre,
            nombreUsuarioFinal,
            nuevoPassword,
            checkboxOtorgarPrivilegios.isSelected()
        );
        
        JOptionPane.showMessageDialog(
            null, 
            "Se han modificado los datos del usuario seleccionado con éxito"
        );
        
        SesionMenuPrincipal.setVisible(true);
        this.dispose(); // Cierra la ventana actual

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
            null, 
            "Ocurrió un error al modificar el usuario:\n" + e.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE
        );
    }  
        
    }//GEN-LAST:event_btnConfirmarModificacionActionPerformed

    private void listaUsuariosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaUsuariosValueChanged
        // TODO add your handling code here:
         if (!evt.getValueIsAdjusting()) {
        if (!listaUsuarios.isSelectionEmpty()) {
            btnConfirmarSeleccion.setEnabled(true); // Habilita el botón
        } else {
            btnConfirmarSeleccion.setEnabled(false); // Deshabilita si no hay selección
        }
         }
    }//GEN-LAST:event_listaUsuariosValueChanged

    private boolean nuevoNombreDeUsuarioEsValido(String nuevoNombre) {
    if (nuevoNombre == null || nuevoNombre.isBlank()) {
        JOptionPane.showMessageDialog(null,
                "El nombre de usuario no puede estar vacío",
                "Error de validación",
                JOptionPane.ERROR_MESSAGE);
        return false;
    }

    if (usuarioController.obtenerUsuario(nuevoNombre) != null) {
        JOptionPane.showMessageDialog(null,
                "El nombre de usuario no está disponible",
                "Error de validación",
                JOptionPane.ERROR_MESSAGE);
        return false;
    }

    return true;
    }
    
    private boolean contraseniaEsValida(String contrasenia) {
    if (contrasenia == null || contrasenia.isBlank()) {
        JOptionPane.showMessageDialog(
            null,
            "La contraseña no puede estar vacía.",
            "Error de validación",
            JOptionPane.ERROR_MESSAGE
        );
        return false;
    }

    if (contrasenia.length() < 8 || contrasenia.length() > 16) {
        JOptionPane.showMessageDialog(
            null,
            "La contraseña debe tener entre 8 y 16 caracteres.",
            "Error de validación",
            JOptionPane.ERROR_MESSAGE
        );
        return false;
    }

    return true;
}
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmarModificacion;
    private javax.swing.JButton btnConfirmarSeleccion;
    private javax.swing.JCheckBox checkboxConservarNombre;
    private javax.swing.JCheckBox checkboxOtorgarPrivilegios;
    private javax.swing.JTextField fieldNuevoNombre;
    private javax.swing.JPasswordField fieldNuevoPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelNuevoNombre;
    private javax.swing.JLabel labelNuevoPassword;
    private javax.swing.JLabel labelUsuarioSeleccionado;
    private javax.swing.JList<String> listaUsuarios;
    // End of variables declaration//GEN-END:variables
}
