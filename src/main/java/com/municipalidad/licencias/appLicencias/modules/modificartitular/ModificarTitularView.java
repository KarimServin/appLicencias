package com.municipalidad.licencias.appLicencias.modules.modificartitular;

import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.modules.emitirlicencia.ActualizarTitularRequestDTO;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


public class ModificarTitularView extends javax.swing.JFrame {

    public ModificarTitularView() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deshabilitarEdicion();
    }

    // ── Listeners ──

    public void setBuscarAction(ActionListener listener) {
        buscarTitularButton.addActionListener(listener);
    }

    public void setConfirmarAction(ActionListener listener) {
        confirmarModificacionButton.addActionListener(listener);
    }

    public void setLimpiarAction(ActionListener listener) {
        limpiarButton.addActionListener(listener);
    }

    public void setCancelarAction(ActionListener listener) {
        cancelarButton.addActionListener(listener);
    }

    // ── Getters ──

    public String getDni() {
        return numDocTextfield.getText().trim();
    }

    public String getDomicilio() {
        return domicilioTextField.getText().trim();
    }

    public ActualizarTitularRequestDTO getDatosModificados(Long dni) {
        Long telefono = null;
        try {
            String tel = telefonoTextField.getText().trim();
            if (!tel.isEmpty()) {
                telefono = Long.valueOf(tel);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El teléfono ingresado no es válido.");
        }

        return ActualizarTitularRequestDTO.builder()
                .dni(dni)
                .telefono(telefono)
                .email(emailTextField.getText().trim())
                .domicilio(domicilioTextField.getText().trim())
                .esDonante(esDonanteCheckbox.isSelected())
                .build();
    }

    // ── Cargar datos ──

    public void cargarDatosTitular(TitularDTO titular) {
        nombreTextField.setText(titular.getApellido() + ", " + titular.getNombre());
        telefonoTextField.setText(
            titular.getTelefono() != null ? titular.getTelefono().toString() : ""
        );
        emailTextField.setText(
            titular.getEmail() != null ? titular.getEmail() : ""
        );
        domicilioTextField.setText(
            titular.getDomicilio() != null ? titular.getDomicilio() : ""
        );
        esDonanteCheckbox.setSelected(
            titular.getEsDonante() != null && titular.getEsDonante()
        );
    }

    // ── Estados ──

    public void habilitarEdicion() {
        numDocTextfield.setEnabled(false);
        telefonoTextField.setEnabled(true);
        emailTextField.setEnabled(true);
        domicilioTextField.setEnabled(true);
        esDonanteCheckbox.setEnabled(true);
        confirmarModificacionButton.setEnabled(true);
    }

    private void deshabilitarEdicion() {
        telefonoTextField.setEnabled(false);
        emailTextField.setEnabled(false);
        domicilioTextField.setEnabled(false);
        esDonanteCheckbox.setEnabled(false);
        confirmarModificacionButton.setEnabled(false);
    }

    public void limpiar() {
        numDocTextfield.setEnabled(true);
        numDocTextfield.setText("");
        nombreTextField.setText("");
        telefonoTextField.setText("");
        emailTextField.setText("");
        domicilioTextField.setText("");
        esDonanteCheckbox.setSelected(false);
        deshabilitarEdicion();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGeneral = new javax.swing.JPanel();
        panelEncabezado = new javax.swing.JPanel();
        menuPrincipalTitle = new javax.swing.JLabel();
        labelLogoSF = new javax.swing.JLabel();
        panelDatosTitular = new javax.swing.JPanel();
        buscarTitularButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        limpiarButton = new javax.swing.JButton();
        numDocTextfield = new javax.swing.JTextField();
        panelTitularEncontrado = new javax.swing.JPanel();
        titularEncontradoTitle = new javax.swing.JLabel();
        nombreTextField = new javax.swing.JTextField();
        nombreLabel = new javax.swing.JLabel();
        telefonoLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        telefonoTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        domicilioLabel = new javax.swing.JLabel();
        domicilioTextField = new javax.swing.JTextField();
        esDonanteCheckbox = new javax.swing.JCheckBox();
        confirmarModificacionButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        menuPrincipalTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        menuPrincipalTitle.setText("MODIFICAR DATOS DE TITULAR");

        labelLogoSF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPrincipalTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 250, Short.MAX_VALUE)
                .addComponent(labelLogoSF)
                .addContainerGap())
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelEncabezadoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(menuPrincipalTitle))
                    .addComponent(labelLogoSF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        buscarTitularButton.setText("Buscar titular");

        jLabel2.setText("DNI Titular:");

        limpiarButton.setText("Limpiar");

        titularEncontradoTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        titularEncontradoTitle.setText("Titular encontrado");

        nombreTextField.setEditable(false);

        nombreLabel.setText("Nombre y Apellido:");

        telefonoLabel.setText("Teléfono:");

        emailLabel.setText("Email:");

        domicilioLabel.setText("Domicilio:");

        esDonanteCheckbox.setText("Es donante");

        javax.swing.GroupLayout panelTitularEncontradoLayout = new javax.swing.GroupLayout(panelTitularEncontrado);
        panelTitularEncontrado.setLayout(panelTitularEncontradoLayout);
        panelTitularEncontradoLayout.setHorizontalGroup(
            panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitularEncontradoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTitularEncontradoLayout.createSequentialGroup()
                        .addGroup(panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(domicilioLabel)
                            .addComponent(telefonoLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(domicilioTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                            .addComponent(telefonoTextField))
                        .addGap(18, 18, 18)
                        .addGroup(panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTitularEncontradoLayout.createSequentialGroup()
                                .addComponent(emailLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(emailTextField))
                            .addGroup(panelTitularEncontradoLayout.createSequentialGroup()
                                .addComponent(esDonanteCheckbox)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panelTitularEncontradoLayout.createSequentialGroup()
                        .addComponent(nombreLabel)
                        .addGap(33, 33, 33)
                        .addComponent(nombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
            .addGroup(panelTitularEncontradoLayout.createSequentialGroup()
                .addComponent(titularEncontradoTitle)
                .addGap(0, 514, Short.MAX_VALUE))
        );
        panelTitularEncontradoLayout.setVerticalGroup(
            panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitularEncontradoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titularEncontradoTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefonoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel)
                    .addComponent(telefonoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(domicilioLabel)
                    .addComponent(domicilioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(esDonanteCheckbox))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelDatosTitularLayout = new javax.swing.GroupLayout(panelDatosTitular);
        panelDatosTitular.setLayout(panelDatosTitularLayout);
        panelDatosTitularLayout.setHorizontalGroup(
            panelDatosTitularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosTitularLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatosTitularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosTitularLayout.createSequentialGroup()
                        .addGap(0, 405, Short.MAX_VALUE)
                        .addComponent(limpiarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarTitularButton))
                    .addGroup(panelDatosTitularLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(numDocTextfield))
                    .addComponent(panelTitularEncontrado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelDatosTitularLayout.setVerticalGroup(
            panelDatosTitularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosTitularLayout.createSequentialGroup()
                .addGroup(panelDatosTitularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(numDocTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosTitularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarTitularButton)
                    .addComponent(limpiarButton))
                .addGap(18, 18, 18)
                .addComponent(panelTitularEncontrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        confirmarModificacionButton.setText("Confirmar modificación");

        cancelarButton.setText("Cancelar");

        javax.swing.GroupLayout panelGeneralLayout = new javax.swing.GroupLayout(panelGeneral);
        panelGeneral.setLayout(panelGeneralLayout);
        panelGeneralLayout.setHorizontalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGeneralLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelarButton)
                        .addGap(18, 18, 18)
                        .addComponent(confirmarModificacionButton)
                        .addGap(17, 17, 17)))
                .addContainerGap())
            .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelGeneralLayout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(panelDatosTitular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(14, 14, 14)))
        );
        panelGeneralLayout.setVerticalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 306, Short.MAX_VALUE)
                .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarButton)
                    .addComponent(confirmarModificacionButton))
                .addGap(17, 17, 17))
            .addGroup(panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelGeneralLayout.createSequentialGroup()
                    .addGap(132, 132, 132)
                    .addComponent(panelDatosTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(51, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarTitularButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JButton confirmarModificacionButton;
    private javax.swing.JLabel domicilioLabel;
    private javax.swing.JTextField domicilioTextField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JCheckBox esDonanteCheckbox;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JButton limpiarButton;
    private javax.swing.JLabel menuPrincipalTitle;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JTextField nombreTextField;
    private javax.swing.JTextField numDocTextfield;
    private javax.swing.JPanel panelDatosTitular;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JPanel panelGeneral;
    private javax.swing.JPanel panelTitularEncontrado;
    private javax.swing.JLabel telefonoLabel;
    private javax.swing.JTextField telefonoTextField;
    private javax.swing.JLabel titularEncontradoTitle;
    // End of variables declaration//GEN-END:variables
}
