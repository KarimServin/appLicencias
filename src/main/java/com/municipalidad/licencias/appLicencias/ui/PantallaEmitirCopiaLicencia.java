
package com.municipalidad.licencias.appLicencias.ui;

import com.municipalidad.licencias.appLicencias.controller.LicenciaController;
import com.municipalidad.licencias.appLicencias.controller.TitularController;
import com.municipalidad.licencias.appLicencias.model.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.model.Licencia;
import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.service.PDFService;
import com.municipalidad.licencias.appLicencias.singleton.SesionMenuPrincipal;
import com.municipalidad.licencias.appLicencias.singleton.SesionUsuario;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.JOptionPane;

public class PantallaEmitirCopiaLicencia extends javax.swing.JFrame {
    LicenciaController licenciaController;
    TitularController titularController;
    PDFService pdfs;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PantallaEmitirCopiaLicencia.class.getName());
    
    public PantallaEmitirCopiaLicencia() {}
    public PantallaEmitirCopiaLicencia(LicenciaController licenciaControl, TitularController titularControl) {
        licenciaController = licenciaControl;
        titularController = titularControl;
        pdfs = new PDFService();
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png")));
    }

    private void setTable() {
        String[] columnas = {
            "Clase",
            "Fecha de emisión",
            "Fecha de vencimiento"
        };
        Object[][] datos = new Object[1][3];
        licenciasTable.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));
        if (validarDni()) {
            List<Licencia> licencias = new ArrayList();
            licencias = licenciaController.obtenerLicenciasPorTitular(Long.valueOf(numDocField.getText()));
            Object[][] data = new Object[licencias.size()][3];
            for (int i = 0; i < licencias.size(); i++) {
                Licencia l = licencias.get(i);
                data[i][0] = l.getClaseLicencia().toString();
                data[i][1] = l.getFechaEmision().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                data[i][2] = l.getFechaVencimiento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }
            licenciasTable.setModel(new javax.swing.table.DefaultTableModel(data, columnas));
        }
    }

    private boolean validarDni() {
        boolean result = false;
        try {
            if (numDocField.getText().trim() == null || numDocField.getText().trim().isBlank()) {
                JOptionPane.showMessageDialog(
                        null,
                        "El campo número de documento es obligatorio.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                Long.valueOf(numDocField.getText());
            }
            result = true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "El campo número de documento no es válido.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            result = false;
        }
        return result;
    }

    private void validarTabla() {
        if (licenciasTable.getSelectedRowCount() == -1) {
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

        panelEncabezado = new javax.swing.JPanel();
        labelMenuPrincipal = new javax.swing.JLabel();
        cancelarButton = new javax.swing.JButton();
        numDocField = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        tipoDocDD = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        licenciasTable = new javax.swing.JTable();
        emitirButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        observacionesField = new javax.swing.JTextField();
        labelLogoSF = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setSize(new java.awt.Dimension(590, 254));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelMenuPrincipal.setText("Emitir Copia Licencia - Sistema de gestión de licencias");

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap(173, Short.MAX_VALUE)
                .addComponent(labelMenuPrincipal)
                .addGap(129, 129, 129))
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(labelMenuPrincipal)
                .addContainerGap())
        );

        getContentPane().add(panelEncabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 0, -1, -1));

        cancelarButton.setText("Cancelar");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });
        getContentPane().add(cancelarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, -1, -1));

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
        numDocField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numDocFieldActionPerformed(evt);
            }
        });
        getContentPane().add(numDocField, new org.netbeans.lib.awtextra.AbsoluteConstraints(404, 83, 154, -1));

        jLabel1.setText("Tipo de documento");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 86, -1, -1));

        tipoDocDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DNI", "CI", "ERRO", "LC", "LE", "LEM", "PAS" }));
        getContentPane().add(tipoDocDD, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 83, 83, -1));

        jLabel2.setText("N° de documento");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(291, 86, -1, -1));

        licenciasTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Clase", "Fecha de emison", "Fecha de vencimiento"
            }
        ));
        jScrollPane2.setViewportView(licenciasTable);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 117, 600, 119));

        emitirButton.setText("Emitir copia");
        emitirButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emitirButtonActionPerformed(evt);
            }
        });
        getContentPane().add(emitirButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 290, -1, -1));

        jLabel4.setText("Observaciones");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 257, -1, -1));

        observacionesField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                observacionesFieldActionPerformed(evt);
            }
        });
        getContentPane().add(observacionesField, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 254, 460, -1));

        labelLogoSF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N
        getContentPane().add(labelLogoSF, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 77));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        SesionMenuPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelarButtonActionPerformed

    private void numDocFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_numDocFieldFocusLost
        if(validarDni()){
            setTable();
        }
    }//GEN-LAST:event_numDocFieldFocusLost

    private void emitirButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emitirButtonActionPerformed
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
            if(licencia.getClass().isInstance(licenciaController.emitirLicencia(licencia, observacionesField.getText(), SesionUsuario.getUsuarioActual()))){
                JOptionPane.showMessageDialog(
                    null,
                    "Se emiti copia de la licencia con éxito.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                SesionMenuPrincipal.setVisible(true);
            }
        } catch(NoSuchElementException e){
            JOptionPane.showMessageDialog(
                null,
                "Hubo un error encontrando la licencia a copiar",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_emitirButtonActionPerformed

    private void numDocFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numDocFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numDocFieldActionPerformed

    private void observacionesFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_observacionesFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_observacionesFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(() -> new PantallaEmitirCopiaLicencia().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarButton;
    private javax.swing.JButton emitirButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JLabel labelMenuPrincipal;
    private javax.swing.JTable licenciasTable;
    private javax.swing.JFormattedTextField numDocField;
    private javax.swing.JTextField observacionesField;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JComboBox<String> tipoDocDD;
    // End of variables declaration//GEN-END:variables
}
