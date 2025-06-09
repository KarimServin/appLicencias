
package com.municipalidad.licencias.appLicencias.ui;

import com.municipalidad.licencias.appLicencias.controller.LicenciaController;
import com.municipalidad.licencias.appLicencias.controller.TitularController;
import com.municipalidad.licencias.appLicencias.model.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.singleton.SesionMenuPrincipal;
import com.municipalidad.licencias.appLicencias.singleton.SesionUsuario;
import javax.swing.JOptionPane;

public class PantallaEmitirLicencia extends javax.swing.JFrame {
    LicenciaController licenciaController;
    TitularController titularController;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PantallaEmitirLicencia.class.getName());
    
    public PantallaEmitirLicencia() {}
    public PantallaEmitirLicencia(LicenciaController licenciaControl, TitularController titularControl) {
        licenciaController = licenciaControl;
        titularController = titularControl;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEncabezado = new javax.swing.JPanel();
        labelMenuPrincipal = new javax.swing.JLabel();
        labelLogoSF = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        clasesDD = new javax.swing.JComboBox<>();
        aceptarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();
        numDocField = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        tipoDocDD = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        observacionesField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setSize(new java.awt.Dimension(590, 254));

        labelMenuPrincipal.setText("Emitir Nueva Licencia - Sistema de gestión de licencias");

        labelLogoSF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLogoSF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
                .addComponent(labelMenuPrincipal)
                .addContainerGap())
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelLogoSF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelMenuPrincipal)
                .addContainerGap())
        );

        jLabel5.setText("Clase");

        clasesDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clase A: Ciclomotores motocicletas y triciclos motorizados", "Clase B: Automóviles y camionetas con acoplado", "Clase C: Camiones sin acoplado y los comprendidos en la clase B", "Clase D: Servicio de transporte de pasajeros, emergencia, seguridad y los comprendidos en la clase B o C, según el caso", "Clase E: Camiones articulados o con acoplado, maquinaria especial no agrícola y los comprendidos en la clase B y C", "Clase F: Automotores especialmente adaptados para discapacitados", "Clase G: Tractores agrícolas y maquinaria especial agrícola", " " }));

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

        tipoDocDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "CI", "ERRO", "LC", "LE", "LEM", "PAS" }));

        jLabel2.setText("N° de documento");

        jLabel6.setText("Observaciones");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(cancelarButton)
                            .addGap(18, 18, 18)
                            .addComponent(aceptarButton))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6))
                            .addGap(50, 50, 50)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(clasesDD, 0, 1, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(tipoDocDD, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(numDocField))
                                .addComponent(observacionesField)))))
                .addGap(9, 9, 9))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(tipoDocDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numDocField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(clasesDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(observacionesField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarButton)
                    .addComponent(cancelarButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aceptarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarButtonActionPerformed
        if(numDocField.getText().trim()!=null || !numDocField.getText().trim().isBlank()){
            ClaseLicencia claseSelec;
            String selec = clasesDD.getSelectedItem().toString().toUpperCase();
            claseSelec = ClaseLicencia.valueOf(String.valueOf(selec.charAt(6)));
            long dniTitular = Long.parseLong(numDocField.getText().replaceAll("[^\\d]", ""));
            try {
                if(Titular.class.isInstance(titularController.buscarTitular(dniTitular))){
                    if(licenciaController.puedeEmitir(dniTitular, claseSelec)){
                        licenciaController.emitirLicencia(dniTitular, claseSelec, observacionesField.getText().trim(), SesionUsuario.getUsuarioActual());
                        JOptionPane.showMessageDialog(
                                null,
                                "La licencia ha sido creada con éxito.",
                                "Exito",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "El titular no puede emitir licencia de la clase seleccionada.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (RuntimeException e){
                int opcion =  JOptionPane.showOptionDialog(
                            null,
                            "El titular no está registrado. ¿Desea registrarlo?",
                            "Titular no encontrado",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new Object[]{"Aceptar", "Cancelar"},
                            "Aceptar");
                if(opcion == JOptionPane.YES_OPTION){
                    new PantallaCargarTitular(titularController, clasesDD.getSelectedItem().toString().toUpperCase()).setVisible(true);
                    this.dispose();
                } else {
                    numDocField.setText("");
                    numDocField.requestFocus();
                }
            }
        } else JOptionPane.showMessageDialog(
                null,
                "Hay campos vacíos",
                "Error",
                JOptionPane.ERROR_MESSAGE);
         
    }//GEN-LAST:event_aceptarButtonActionPerformed

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        SesionMenuPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelarButtonActionPerformed

    private void numDocFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numDocFieldFocusLost
        if(numDocField.getText().trim() == null || numDocField.getText().trim().isBlank()){
            JOptionPane.showMessageDialog(numDocField, 
                    "El campo es obligatorio", 
                    "Error de validación", 
                    JOptionPane.ERROR_MESSAGE);
            numDocField.requestFocus();
        } else{
            Long dni = Long.valueOf(numDocField.getText());
            try{
                titularController.buscarTitular(dni);
            } catch (RuntimeException e){
                int opcion =  JOptionPane.showOptionDialog(
                            null,
                            "El titular no está registrado. ¿Desea registrarlo?",
                            "Titular no encontrado",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            new Object[]{"Aceptar", "Cancelar"},
                            "Aceptar");
                if(opcion == JOptionPane.YES_OPTION){
                    new PantallaCargarTitular(titularController, clasesDD.getSelectedItem().toString().toUpperCase()).setVisible(true);
                } else {
                    numDocField.setText("");
                    numDocField.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_numDocFieldFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(() -> new PantallaEmitirLicencia().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JComboBox<String> clasesDD;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JLabel labelMenuPrincipal;
    private javax.swing.JFormattedTextField numDocField;
    private javax.swing.JTextField observacionesField;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JComboBox<String> tipoDocDD;
    // End of variables declaration//GEN-END:variables
}
