
package com.municipalidad.licencias.appLicencias.ui;

import com.municipalidad.licencias.appLicencias.controller.TitularController;
import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.singleton.SesionMenuPrincipal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;

public class PantallaCargarTitular extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PantallaCargarTitular.class.getName());
    private TitularController titularController;
    private String clase;
    private String dni;
    
    public PantallaCargarTitular() {}
    
    public PantallaCargarTitular(TitularController titularController, String clase, String dni) {
        this.titularController = titularController;
        this.clase = clase;
        this.dni = dni;
        initComponents();
        numDocField.setText(dni);
    }
    
    public PantallaCargarTitular(TitularController titularController, String clase) {
        this.titularController = titularController;
        this.clase = clase;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        domicilioField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        grupoDD = new javax.swing.JComboBox<>();
        factorDD = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        tipoDocDD = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        panelEncabezado = new javax.swing.JPanel();
        labelMenuPrincipal = new javax.swing.JLabel();
        labelLogoSF = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        fechaNacField = new javax.swing.JFormattedTextField();
        donanteCheck = new javax.swing.JCheckBox();
        numDocField = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        apellidoField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nombreField = new javax.swing.JTextField();
        aceptarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        telefonoField = new javax.swing.JFormattedTextField();
        correoField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(596, 350));

        domicilioField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                domicilioFieldFocusLost(evt);
            }
        });

        jLabel10.setText("Nombre");

        jLabel4.setText("Fecha de nacimiento");

        grupoDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "O", "A", "B" }));

        factorDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "+", "-" }));

        jLabel6.setText("Grupo Sanguíneo");

        tipoDocDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "CI", "ERRO", "LC", "LE", "LEM", "PAS" }));

        jLabel7.setText("Factor Sanguíneo");

        labelMenuPrincipal.setText("Cargar titular - Sistema de gestión de licencias");

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

        jLabel8.setText("Donante");

        fechaNacField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        fechaNacField.setText("dd/mm/aaaa");
        fechaNacField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fechaNacFieldFocusLost(evt);
            }
        });

        donanteCheck.setText("Si / No");

        try {
            numDocField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        numDocField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                numDocFieldFocusLost(evt);
            }
        });

        jLabel1.setText("Tipo de documento");

        jLabel9.setText("Apellido");

        jLabel2.setText("N° de documento");

        apellidoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                apellidoFieldFocusLost(evt);
            }
        });

        jLabel3.setText("Domicilio");

        nombreField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nombreFieldFocusLost(evt);
            }
        });

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

        jLabel11.setText("Teléfono");

        jLabel12.setText("Correo");

        try {
            telefonoField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("### ###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        telefonoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                telefonoFieldFocusLost(evt);
            }
        });

        correoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                correoFieldFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelarButton)
                        .addGap(18, 18, 18)
                        .addComponent(aceptarButton))
                    .addComponent(panelEncabezado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(122, 122, 122)
                                .addComponent(tipoDocDD, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(76, 76, 76)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(apellidoField, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                                    .addComponent(telefonoField))))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(numDocField, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel12))
                                .addGap(69, 69, 69)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(correoField, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(domicilioField)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fechaNacField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(grupoDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(105, 105, 105)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(factorDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(77, 77, 77)
                        .addComponent(donanteCheck)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(tipoDocDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numDocField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(correoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefonoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(domicilioField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fechaNacField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(grupoDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(factorDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(donanteCheck))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarButton)
                    .addComponent(cancelarButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void correoFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_correoFieldFocusLost
        String formato = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        String correo = correoField.getText().trim();
        if(correo == null || correo.isBlank() || (!correo.matches(formato))){
             JOptionPane.showMessageDialog(
                null,
                "El correo no es válido, vuelva a intentar.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            //correoField.requestFocus();
        }
    }//GEN-LAST:event_correoFieldFocusLost

    private void apellidoFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_apellidoFieldFocusLost
        if(apellidoField.getText().trim()==null || apellidoField.getText().trim().isBlank()) JOptionPane.showMessageDialog(
                null,
                "El campo es obligatorio.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_apellidoFieldFocusLost

    private void nombreFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nombreFieldFocusLost
        if(nombreField.getText().trim()==null || nombreField.getText().trim().isBlank()) JOptionPane.showMessageDialog(
                null,
                "El campo es obligatorio.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_nombreFieldFocusLost

    private void domicilioFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_domicilioFieldFocusLost
        if(domicilioField.getText().trim()==null || domicilioField.getText().trim().isBlank()) JOptionPane.showMessageDialog(
                null,
                "El campo es obligatorio.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_domicilioFieldFocusLost

    private void aceptarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarButtonActionPerformed
        try{
            if((numDocField.getText().trim()!=null && !numDocField.getText().trim().isBlank()) &&
                    (apellidoField.getText().trim()!=null && !apellidoField.getText().trim().isBlank()) && 
                    (telefonoField.getText().trim()!=null && !telefonoField.getText().trim().isBlank()) && 
                    (fechaNacField.getText().trim()!=null && !fechaNacField.getText().trim().isBlank()) && 
                    (correoField.getText().trim()!=null && !correoField.getText().trim().isBlank() && (correoField.getText().trim().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"))) && 
                    (nombreField.getText().trim()!=null && !nombreField.getText().trim().isBlank()) &&
                    (domicilioField.getText().trim()!=null && !domicilioField.getText().trim().isBlank())){
                Long dni = Long.valueOf(numDocField.getText().replaceAll("[^\\d]", ""));
                String nombreCompleto = apellidoField.getText().trim() + " " + nombreField.getText().trim();
                LocalDate fechaNacimiento = LocalDate.parse(fechaNacField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                char grupoSanguineo = grupoDD.getSelectedItem().toString().charAt(0);
                char factorSanguineo = factorDD.getSelectedItem().toString().charAt(0);
                boolean esDonante = donanteCheck.isSelected();
                String tel = telefonoField.getText().replaceAll("[^\\d]", "");
                long telefono = Long.valueOf(tel);
                String correo = correoField.getText();
                String domicilio = domicilioField.getText().trim();
                try{
                    if(Titular.class.isInstance(titularController.crearTitular(dni, nombreCompleto, fechaNacimiento,
                                        grupoSanguineo, factorSanguineo,
                                        esDonante, false, null, telefono, correo, domicilio))){
                        JOptionPane.showMessageDialog(
                        null,
                        "El titular ha sido registrado con éxito.",
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        SesionMenuPrincipal.setVisible(true);
                    }
                } catch (RuntimeException e){
                    JOptionPane.showMessageDialog(
                        null,
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            } else JOptionPane.showMessageDialog(
                    null,
                    "Hay campos erróneos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (DateTimeParseException e){
            JOptionPane.showMessageDialog(
                    null,
                    "Hubo un error con el campo fecha.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(
                    null,
                    "Hubo un error con un campo numérico.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_aceptarButtonActionPerformed

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        SesionMenuPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelarButtonActionPerformed

    private void telefonoFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_telefonoFieldFocusLost
        try{
            if(telefonoField.getText().trim()==null || telefonoField.getText().trim().isBlank()) 
            JOptionPane.showMessageDialog(
                null,
                "El campo es obligatorio.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            else Long.valueOf(telefonoField.getText().replaceAll("[^\\d]", ""));
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(
                null,
                "El campo completado no es válido",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_telefonoFieldFocusLost

    private void fechaNacFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fechaNacFieldFocusLost
        try{
            if(fechaNacField.getText().trim()==null || fechaNacField.getText().trim().isBlank()) 
            JOptionPane.showMessageDialog(
                null,
                "El campo es obligatorio.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            else LocalDate.parse(fechaNacField.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e){
            JOptionPane.showMessageDialog(
                    null,
                    "Hubo un error con el campo fecha.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_fechaNacFieldFocusLost

    private void numDocFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numDocFieldFocusLost
        try{
            if(numDocField.getText().trim()==null || numDocField.getText().trim().isBlank()) 
            JOptionPane.showMessageDialog(
                null,
                "El campo es obligatorio.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            else Long.valueOf(numDocField.getText());
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(
                null,
                "El campo completado no es válido",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_numDocFieldFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new PantallaCargarTitular().setVisible(true));
   }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JTextField apellidoField;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JTextField correoField;
    private javax.swing.JTextField domicilioField;
    private javax.swing.JCheckBox donanteCheck;
    private javax.swing.JComboBox<String> factorDD;
    private javax.swing.JFormattedTextField fechaNacField;
    private javax.swing.JComboBox<String> grupoDD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JLabel labelMenuPrincipal;
    private javax.swing.JTextField nombreField;
    private javax.swing.JFormattedTextField numDocField;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JFormattedTextField telefonoField;
    private javax.swing.JComboBox<String> tipoDocDD;
    // End of variables declaration//GEN-END:variables
}
