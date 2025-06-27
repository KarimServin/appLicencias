package com.municipalidad.licencias.appLicencias.ui;

import com.municipalidad.licencias.appLicencias.controller.LicenciaController;
import com.municipalidad.licencias.appLicencias.controller.TitularController;
import com.municipalidad.licencias.appLicencias.model.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.model.Licencia;
import com.municipalidad.licencias.appLicencias.singleton.SesionMenuPrincipal;
import com.municipalidad.licencias.appLicencias.singleton.SesionUsuario;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class PantallaRenovarLicencia extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PantallaRenovarLicencia.class.getName());
    LicenciaController licenciaController;
    TitularController titularController;
    
    public PantallaRenovarLicencia() {
    }
    public PantallaRenovarLicencia(LicenciaController licenciaControl, TitularController titularControl) {
        licenciaController = licenciaControl;
        titularController = titularControl;
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png")));
        licenciasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    public PantallaRenovarLicencia(LicenciaController licenciaControl, TitularController titularControl, String dni) {
        licenciaController = licenciaControl;
        titularController = titularControl;
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png")));
        numDocField.setText(dni);
        licenciasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setTable();
    }
    
    private void setTable(){
        String[] columnas = {
            "Clase",
            "Fecha de emisión",
            "Fecha de vencimiento"
        };
        Object[][] datos = new Object[1][3];
        licenciasTable.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
        if(validarDni()){
            List<Licencia> licencias = new ArrayList();
            licencias = licenciaController.obtenerLicenciasPorTitular(Long.valueOf(numDocField.getText()));
            Object[][] data = new Object[licencias.size()][3];
            for(int i=0; i<licencias.size(); i++){
                Licencia l = licencias.get(i);
                data[i][0] = l.getClaseLicencia().toString();
                data[i][1] = l.getFechaEmision().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                data[i][2] = l.getFechaVencimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }
            licenciasTable.setModel(new javax.swing.table.DefaultTableModel(data, columnas));
        }
    }
    
    private boolean validarDni(){
        boolean result=false;
        try{
            if(numDocField.getText().trim()==null || numDocField.getText().trim().isBlank()) 
            JOptionPane.showMessageDialog(
                null,
                "El campo número de documento es obligatorio.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            else Long.valueOf(numDocField.getText());
            result = true;
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(
                null,
                "El campo número de documento no es válido.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            result = false;
        }
        return result;
    }
    
    private void validarTabla(){
        if(licenciasTable.getSelectedRowCount()==-1){
            JOptionPane.showMessageDialog(
                null,
                "Debe seleccionar una fila de la tabla de licencias para renovar.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        renovarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tipoDocDD = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        panelEncabezado = new javax.swing.JPanel();
        labelMenuPrincipal = new javax.swing.JLabel();
        labelLogoSF = new javax.swing.JLabel();
        numDocField = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        licenciasTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        observacionesField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(593, 388));

        renovarButton.setText("Renovar");
        renovarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                renovarButtonActionPerformed(evt);
            }
        });

        cancelarButton.setText("Cancelar");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Tipo de documento");

        jLabel3.setText("Licencias del titular");

        tipoDocDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "CI", "ERRO", "LC", "LE", "LEM", "PAS" }));

        jLabel2.setText("N° de documento");

        labelMenuPrincipal.setText("Renovar Licencia - Sistema de gestión de licencias");

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
                .addComponent(labelMenuPrincipal)
                .addContainerGap())
        );

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

        licenciasTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(licenciasTable);

        jLabel4.setText("Observaciones");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(renovarButton))
                    .addComponent(panelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addComponent(tipoDocDD, 0, 1, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(numDocField))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel1))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(observacionesField, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
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
                .addGap(13, 13, 13)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(observacionesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(renovarButton)
                    .addComponent(cancelarButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void renovarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renovarButtonActionPerformed
        validarDni();
        validarTabla();
        String clase = licenciasTable.getValueAt(licenciasTable.getSelectedRow(), 0).toString();
        LocalDate fechae = LocalDate.parse(licenciasTable.getValueAt(licenciasTable.getSelectedRow(), 1).toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate fechav = LocalDate.parse(licenciasTable.getValueAt(licenciasTable.getSelectedRow(), 2).toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Licencia licencia;
        List<Licencia> licencias = new ArrayList();
        licencias = licenciaController.obtenerLicenciasPorTitular(Long.valueOf(numDocField.getText()));
        try{
            licencia = licencias.stream()
                .filter(l -> l.getClaseLicencia().equals(ClaseLicencia.valueOf(clase))
              && l.getFechaEmision().isEqual(fechae)
              && l.getFechaVencimiento().isEqual(fechav))
            .findFirst().get();
            System.out.println("licencia encontrada: \n clase: " + licencia.getClaseLicencia() + "\nfechae: " + licencia.getFechaEmision()
            + "\nfechav: " + licencia.getFechaVencimiento()
            + "\nid:" + licencia.getId());
            if(licencia.getClass().isInstance(licenciaController.renovarLicencia(licencia, observacionesField.getText(), SesionUsuario.getUsuarioActual()))){
                JOptionPane.showMessageDialog(
                    null,
                    "La licencia se renovo con éxito.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                SesionMenuPrincipal.setVisible(true);
            }
        } catch(NoSuchElementException e){
            JOptionPane.showMessageDialog(
                null,
                "Hubo un error encontrando la licencia a renovar",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_renovarButtonActionPerformed

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        SesionMenuPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelarButtonActionPerformed

    private void numDocFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numDocFieldFocusLost
        if(validarDni()){
            setTable();
        }
    }//GEN-LAST:event_numDocFieldFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new PantallaRenovarLicencia().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JLabel labelMenuPrincipal;
    private javax.swing.JTable licenciasTable;
    private javax.swing.JFormattedTextField numDocField;
    private javax.swing.JTextField observacionesField;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JButton renovarButton;
    private javax.swing.JComboBox<String> tipoDocDD;
    // End of variables declaration//GEN-END:variables
}
