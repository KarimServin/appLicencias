
package com.municipalidad.licencias.appLicencias.ui;

import com.municipalidad.licencias.appLicencias.controller.TitularController;
import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.exception.ValidationException;
import java.awt.Toolkit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import com.municipalidad.licencias.appLicencias.navigation.BackToMenuListener;


public class AltaTitularView extends javax.swing.JFrame {
    
    private final TitularController titularController;
    private final BackToMenuListener backToMenuListener;
    
    
    public AltaTitularView(TitularController titularController, 
                                 BackToMenuListener backToMenuListener) {
        
        this.titularController = titularController;
        this.backToMenuListener = backToMenuListener;
        
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

    private void aceptarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarButtonActionPerformed
        
        if(!camposIngresadosValidos()) 
            return; 
        
        
        try {
                //Generar titularDTO
                TitularDTO titularDTO = buildTitularDTO();
                titularController.crearTitular(titularDTO);

                JOptionPane.showMessageDialog(
                null,
                "El titular ha sido registrado con éxito.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);

                this.dispose();
                backToMenuListener.mostrarMenuPrincipal();
                    
                }   catch (ValidationException e) { 
                    JOptionPane.showMessageDialog(
                        null,
                        "Error de validación: " + e.getMessage(),
                        "Datos inválidos",
                        JOptionPane.ERROR_MESSAGE);
                
                }   catch (DateTimeParseException e){
                   
                        JOptionPane.showMessageDialog(
                                null,
                                "Hubo un error con el campo fecha.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                }   catch (NumberFormatException e){
                        JOptionPane.showMessageDialog(
                                null,
                                "Hubo un error con un campo numérico.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                }   catch (ServiceException ex) { 
                        JOptionPane.showMessageDialog(
                        null,
                        "Error de negocio: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }   catch (RuntimeException ex) {
                        JOptionPane.showMessageDialog(
                                null,
                                ex.getMessage(),
                                "Error desconocido. Contacte a soporte.",
                                JOptionPane.ERROR_MESSAGE);
                }
    }//GEN-LAST:event_aceptarButtonActionPerformed

    
    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        backToMenuListener.mostrarMenuPrincipal();
        this.dispose();
    }//GEN-LAST:event_cancelarButtonActionPerformed
    
    private TitularDTO buildTitularDTO() {
        return TitularDTO.builder()
            .dni(extractNumericValue(numDocField.getText()))
            .nombre(nombreField.getText().trim())
            .apellido(apellidoField.getText().trim())
            .fechaNacimiento(parseDate(fechaNacField.getText()))
            .grupoSanguineo(grupoSanguineoCBox.getSelectedItem().toString().charAt(0))
            .factorSanguineo(factorSanguineoCBox.getSelectedItem().toString().charAt(0))
            .esDonante(donanteCheckBox.isSelected())
            .telefono(extractNumericValue(telefonoField.getText()))
            .email(correoField.getText().trim())
            .domicilio(domicilioField.getText().trim())
            .build();
    }
    
    private boolean camposIngresadosValidos() {
    
        


        if (numDocField.getText().trim().isBlank()) {
            mostrarError("Debe ingresar el número de documento.");
            return false;
        }
        if (!hasMinimumLength(numDocField.getText(), 7)) {
            mostrarError("El DNI debe tener al menos 7 dígitos");
            numDocField.requestFocus(); 
            return false;
        }
        if (apellidoField.getText().trim().isBlank()) {
            mostrarError("Debe ingresar el apellido.");
            return false;
        }
        if (!apellidoField.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            mostrarError("El apellido solo puede contener letras");
            return false;
        }
        if (apellidoField.getText().length() > 50) {
            mostrarError("El apellido no puede exceder 50 caracteres");
            return false;
        }
        if (nombreField.getText().trim().isBlank()) {
            mostrarError("Debe ingresar el nombre.");
            return false;
        }
        if (!nombreField.getText().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            mostrarError("El nombre solo puede contener letras");
            return false;
        }
        if (nombreField.getText().length() > 50) {
            mostrarError("El nombre no puede exceder 50 caracteres");
            return false;
        }
        if (telefonoField.getText().trim().isBlank()) {
            mostrarError("Debe ingresar el teléfono.");
            return false;
        }
        

        //expresion regular para el formato de correo
        //cadena+@+dominio+punto+extension
        String correoFormato = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        String correo = correoField.getText().trim();
        if (correo.isBlank() || !correo.matches(correoFormato)) {
            mostrarError("Debe ingresar una dirección de correo válida. Ejemplo: juanperez@example.com. ");
            return false;
        }
        if (domicilioField.getText().trim().isBlank()) {
            mostrarError("Debe ingresar el domicilio.");
            return false;
        }
        if (fechaNacField.getText().trim().isBlank()) {
            mostrarError("Debe ingresar la fecha de nacimiento.");
            return false;
        }
        if (!isValidFormatoFecha(fechaNacField.getText())) {
            mostrarError("Debe usar el formato DD/MM/YYYY para la fecha de nacimiento");
            return false;
        }

        return true;
    }
    
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, "Error al completar los campos: " + mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private Long extractNumericValue(String text) {
        return Long.valueOf(text.replaceAll("[^\\d]", ""));
    }

    private LocalDate parseDate(String dateText) {
        return LocalDate.parse(dateText, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    
    private boolean isValidFormatoFecha(String date) {  
        return date.matches("\\d{2}/\\d{2}/\\d{4}");
    }
    
    private boolean hasMinimumLength(String text, int minLength) {
       return text.replaceAll("[^\\d]", "").length() >= minLength;
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
