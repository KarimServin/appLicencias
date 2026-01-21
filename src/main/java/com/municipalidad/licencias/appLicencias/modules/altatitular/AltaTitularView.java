
package com.municipalidad.licencias.appLicencias.modules.altatitular;


import com.municipalidad.licencias.appLicencias.dto.TitularDTO;

import java.awt.Toolkit;
import java.awt.event.ActionListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class AltaTitularView extends javax.swing.JFrame {
   
   
    public AltaTitularView() {

        initComponents();
        this.pack();
        this.setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png"))); 
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        domicilioField = new javax.swing.JTextField();
        nombreLabel = new javax.swing.JLabel();
        fechaNacLabel = new javax.swing.JLabel();
        grupoSanguineoCBox = new javax.swing.JComboBox<>();
        factorSanguineoCBox = new javax.swing.JComboBox<>();
        grupoSanLabel = new javax.swing.JLabel();
        factorSanLabel = new javax.swing.JLabel();
        panelEncabezado = new javax.swing.JPanel();
        labelMenuPrincipal = new javax.swing.JLabel();
        labelLogoSF = new javax.swing.JLabel();
        fechaNacField = new javax.swing.JFormattedTextField();
        donanteCheckBox = new javax.swing.JCheckBox();
        apellidoLabel = new javax.swing.JLabel();
        numDniLabel = new javax.swing.JLabel();
        apellidoField = new javax.swing.JTextField();
        domicilioLabel = new javax.swing.JLabel();
        nombreField = new javax.swing.JTextField();
        aceptarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();
        telefonoLabel = new javax.swing.JLabel();
        correoLabel = new javax.swing.JLabel();
        telefonoField = new javax.swing.JFormattedTextField();
        correoField = new javax.swing.JTextField();
        warningLabel = new javax.swing.JLabel();
        numDocField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(596, 350));

        nombreLabel.setText("Nombre");

        fechaNacLabel.setText("Fecha de nacimiento");

        grupoSanguineoCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "O", "A", "B" }));

        factorSanguineoCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "+", "-" }));

        grupoSanLabel.setText("Grupo Sanguíneo");

        factorSanLabel.setText("Factor Sanguíneo");

        labelMenuPrincipal.setText("Alta de titular - Sistema de gestión de licencias");

        labelLogoSF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLogoSF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                .addComponent(labelMenuPrincipal)
                .addGap(23, 23, 23))
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelLogoSF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        fechaNacField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        fechaNacField.setText("dd/mm/aaaa");

        donanteCheckBox.setText("Es donante");

        apellidoLabel.setText("Apellido");

        numDniLabel.setText("N° de DNI");

        domicilioLabel.setText("Domicilio");

        aceptarButton.setText("Aceptar");

        cancelarButton.setText("Cancelar");

        telefonoLabel.setText("Teléfono");

        correoLabel.setText("Correo electrónico");

        warningLabel.setForeground(new java.awt.Color(153, 0, 0));
        warningLabel.setText("Corrobore que los datos ingresados sean correctos.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(cancelarButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aceptarButton)
                .addGap(8, 8, 8))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(warningLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(apellidoLabel)
                                            .addComponent(numDniLabel)
                                            .addComponent(nombreLabel)
                                            .addComponent(grupoSanLabel)
                                            .addComponent(factorSanLabel)
                                            .addComponent(donanteCheckBox))
                                        .addGap(28, 28, 28)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(grupoSanguineoCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(factorSanguineoCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(apellidoField, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                                    .addComponent(nombreField)
                                                    .addComponent(numDocField))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(correoLabel)
                                                    .addComponent(telefonoLabel)
                                                    .addComponent(domicilioLabel)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(fechaNacLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fechaNacField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(telefonoField, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(correoField, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(domicilioField))))
                        .addGap(8, 8, 8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numDniLabel)
                    .addComponent(telefonoLabel)
                    .addComponent(telefonoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numDocField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(apellidoLabel)
                    .addComponent(correoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(correoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreLabel)
                    .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(domicilioField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(domicilioLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fechaNacLabel)
                            .addComponent(fechaNacField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addComponent(grupoSanLabel))
                    .addComponent(grupoSanguineoCBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(factorSanLabel)
                    .addComponent(factorSanguineoCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(donanteCheckBox)
                .addGap(14, 14, 14)
                .addComponent(warningLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarButton)
                    .addComponent(aceptarButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setCancelarAction(ActionListener listener) {
        cancelarButton.addActionListener(listener);
    }
    
    public void setAceptarAction(ActionListener listener) {
        aceptarButton.addActionListener(listener);
    }    
    
 


    public TitularDTO getBuildTitularDTO() {
        String grupo = grupoSanguineoCBox.getSelectedItem() != null
                ? grupoSanguineoCBox.getSelectedItem().toString().trim()
                : null;

        String factor = factorSanguineoCBox.getSelectedItem() != null
                ? factorSanguineoCBox.getSelectedItem().toString().trim()
                : null;
  

        return TitularDTO.builder()
                .dni(extractNumericValue(numDocField.getText()))
                .nombre(nombreField.getText().trim())
                .apellido(apellidoField.getText().trim())
                .fechaNacimiento(parseDate(fechaNacField.getText()))
                .grupoSanguineo(grupo)
                .factorSanguineo(factor)
                .esDonante(donanteCheckBox.isSelected())
                .telefono(extractNumericValue(telefonoField.getText()))
                .email(correoField.getText().trim())
                .domicilio(domicilioField.getText().trim())
                .build();
    }
    
    private Long extractNumericValue(String text) {
        return Long.valueOf(text.replaceAll("[^\\d]", ""));
    }

    private LocalDate parseDate(String dateText) {
        return LocalDate.parse(dateText, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JTextField apellidoField;
    private javax.swing.JLabel apellidoLabel;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JTextField correoField;
    private javax.swing.JLabel correoLabel;
    private javax.swing.JTextField domicilioField;
    private javax.swing.JLabel domicilioLabel;
    private javax.swing.JCheckBox donanteCheckBox;
    private javax.swing.JLabel factorSanLabel;
    private javax.swing.JComboBox<String> factorSanguineoCBox;
    private javax.swing.JFormattedTextField fechaNacField;
    private javax.swing.JLabel fechaNacLabel;
    private javax.swing.JLabel grupoSanLabel;
    private javax.swing.JComboBox<String> grupoSanguineoCBox;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JLabel labelMenuPrincipal;
    private javax.swing.JTextField nombreField;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JLabel numDniLabel;
    private javax.swing.JTextField numDocField;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JFormattedTextField telefonoField;
    private javax.swing.JLabel telefonoLabel;
    private javax.swing.JLabel warningLabel;
    // End of variables declaration//GEN-END:variables
}
