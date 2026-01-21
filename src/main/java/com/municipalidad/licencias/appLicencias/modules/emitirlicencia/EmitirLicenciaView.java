package com.municipalidad.licencias.appLicencias.modules.emitirlicencia;

import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.view.Dialogs;
import java.awt.Toolkit; 
import java.awt.event.ActionListener;


public class EmitirLicenciaView extends javax.swing.JFrame {
    

      
    public EmitirLicenciaView() {
     
        initComponents();    
        this.setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png")));
        
        infoTitularLabel.setVisible(false);
        
    }
    
   
    public void setAceptarAction(ActionListener listener) {
        aceptarButton.addActionListener(listener);
    }
    
    public void setCancelarAction(ActionListener listener) {
        cancelarButton.addActionListener(listener);
    }
    
    public void setBuscarTitularAction(ActionListener listener) {
        buscarTitularButton.addActionListener(listener);
    }
    
    public String getDni() {
        return numDocField.getText().trim();
    }

    public ClaseLicencia getClase() {
        // Obtener el texto seleccionado
        String seleccion = (String) claseLicenciaComboBox.getSelectedItem();
        if (seleccion == null || seleccion.isBlank()) {
            return null; // Validar selección vacía (null safe)
        }
        try {
            // Extraer la letra (siempre asumimos que está en la posición 6 del texto)
            String letra = seleccion.substring(6, 7);
            return ClaseLicencia.valueOf(letra); // Retornar el enum correspondiente
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            // Manejar errores si el texto no coincide con la estructura esperada
            Dialogs.error(this, "La clase seleccionada no es válida.");
            return null;
        }
    }
    
    public String getObservaciones() {
        return observacionesField.getText().trim();
    }
    
    public void setInfoTitular(String dni, 
                               String nombre, 
                               String apellido) {
    
    
        infoTitularLabel.setText("Ud. ha seleccionado emitir licencia para el titular DNI: " 
                                + dni + ", " 
                                + apellido + ", " 
                                + nombre + ".");
        
        infoTitularLabel.setVisible(true); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEncabezado = new javax.swing.JPanel();
        labelMenuPrincipal = new javax.swing.JLabel();
        labelLogoSF = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        claseLicenciaComboBox = new javax.swing.JComboBox<>();
        aceptarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();
        numDocField = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        observacionesField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        buscarTitularButton = new javax.swing.JButton();
        infoTitularLabel = new javax.swing.JLabel();

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

        jLabel5.setText("Clase de Licencia");

        claseLicenciaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Clase A: Ciclomotores motocicletas y triciclos motorizados", "Clase B: Automóviles y camionetas con acoplado", "Clase C: Camiones sin acoplado y los comprendidos en la clase B", "Clase D: Servicio de transporte de pasajeros, emergencia, seguridad y los comprendidos en la clase B o C, según el caso", "Clase E: Camiones articulados o con acoplado, maquinaria especial no agrícola y los comprendidos en la clase B y C", "Clase F: Automotores especialmente adaptados para discapacitados", "Clase G: Tractores agrícolas y maquinaria especial agrícola", " " }));

        aceptarButton.setText("Aceptar");

        cancelarButton.setText("Cancelar");

        try {
            numDocField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel2.setText("N° de DNI");

        jLabel6.setText("Observaciones");

        buscarTitularButton.setText("Buscar titular");

        infoTitularLabel.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(observacionesField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(claseLicenciaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cancelarButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(aceptarButton))
                            .addComponent(buscarTitularButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(numDocField, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(infoTitularLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numDocField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(buscarTitularButton)
                .addGap(33, 33, 33)
                .addComponent(infoTitularLabel)
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(claseLicenciaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(observacionesField, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarButton)
                    .addComponent(cancelarButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JButton buscarTitularButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JComboBox<String> claseLicenciaComboBox;
    private javax.swing.JLabel infoTitularLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JLabel labelMenuPrincipal;
    private javax.swing.JFormattedTextField numDocField;
    private javax.swing.JTextField observacionesField;
    private javax.swing.JPanel panelEncabezado;
    // End of variables declaration//GEN-END:variables
}
