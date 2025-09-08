
package com.municipalidad.licencias.appLicencias.ui;

import com.municipalidad.licencias.appLicencias.controller.LicenciaController;
import com.municipalidad.licencias.appLicencias.controller.TitularController;
import com.municipalidad.licencias.appLicencias.model.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.model.Licencia;
import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.service.PDFService;
import com.municipalidad.licencias.appLicencias.session.UserSession;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.JOptionPane;
import com.municipalidad.licencias.appLicencias.navigation.BackToMenuListener;
import com.municipalidad.licencias.appLicencias.session.SessionInfo;

public class PantallaEmitirCopiaLicencia extends javax.swing.JFrame {
    
    private final LicenciaController licenciaController;
    private final TitularController titularController;
    private final BackToMenuListener backToMenuListener;
    private final SessionInfo sessionInfo;
    
    private PDFService pdfs;
    
    
    
    
    public PantallaEmitirCopiaLicencia(LicenciaController licenciaController, 
                                       TitularController titularController, 
                                       BackToMenuListener backToMenuListener, 
                                       SessionInfo sessionInfo) {
        
        this.licenciaController = licenciaController;
        this.titularController = titularController;
        this.backToMenuListener = backToMenuListener;
        this.sessionInfo = sessionInfo;
        
        //revisar, no es correcta esta logica
        pdfs = new PDFService(); 
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png")));
        this.setLocationRelativeTo(null);
    }

    private void setTable() {
        String[] columnas = {"Clase", "Fecha de emisión", "Fecha de vencimiento"};

        Long dni;
        try {
            dni = Long.valueOf(numDocField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "DNI inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //No está bien, debería traerse un DTO. No instancias de entidad.
        List<Licencia> licencias = licenciaController.obtenerLicenciasPorTitular(dni);

        if (licencias.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron licencias para este titular.", "Info", JOptionPane.INFORMATION_MESSAGE);
            licenciasTable.setModel(new javax.swing.table.DefaultTableModel(new Object[0][0], columnas));
            return;
        }

        Object[][] data = new Object[licencias.size()][3];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (int i = 0; i < licencias.size(); i++) {
            Licencia l = licencias.get(i);
            data[i][0] = l.getClaseLicencia().toString();
            data[i][1] = l.getFechaEmision().format(formatter);
            data[i][2] = l.getFechaVencimiento().format(formatter);
        }

        licenciasTable.setModel(new javax.swing.table.DefaultTableModel(data, columnas));
}


    private boolean dniEsValido() {
    String dni = numDocField.getText().trim();

        if (dni.isBlank()) {
            JOptionPane.showMessageDialog(null,
                "El campo número de documento es obligatorio.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!dni.matches("\\d+")) { //ya es formatted pero no está de más
            JOptionPane.showMessageDialog(null,
                "El DNI solo puede contener números.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (dni.length() > 8) {
            JOptionPane.showMessageDialog(null,
                "El DNI no puede tener más de 8 dígitos.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    
    }

        
    private boolean validarTabla() {
        
        if (licenciasTable.getSelectedRowCount() == -1) {
            JOptionPane.showMessageDialog(
                    null,
                    "Debe seleccionar una fila de la tabla de licencias.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEncabezado = new javax.swing.JPanel();
        labelMenuPrincipal = new javax.swing.JLabel();
        cancelarButton = new javax.swing.JButton();
        numDocField = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        licenciasTable = new javax.swing.JTable();
        btnEmitirCopia = new javax.swing.JButton();
        labelLogoSF = new javax.swing.JLabel();
        btnBuscarLicencias = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        observacionesTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

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
                .addContainerGap(209, Short.MAX_VALUE)
                .addComponent(labelMenuPrincipal)
                .addContainerGap())
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(labelMenuPrincipal)
                .addContainerGap())
        );

        getContentPane().add(panelEncabezado, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 0, 500, -1));

        cancelarButton.setText("Cancelar");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });
        getContentPane().add(cancelarButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 440, -1, -1));

        try {
            numDocField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        getContentPane().add(numDocField, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 154, 20));

        jLabel2.setText("Ingrese el Nº de DNI:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        licenciasTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Clase", "Fecha de Emisión", "Fecha de vencimiento"
            }
        ));
        jScrollPane2.setViewportView(licenciasTable);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 600, 119));

        btnEmitirCopia.setText("Emitir copia");
        btnEmitirCopia.setEnabled(false);
        btnEmitirCopia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmitirCopiaActionPerformed(evt);
            }
        });
        getContentPane().add(btnEmitirCopia, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 440, -1, 20));

        labelLogoSF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N
        getContentPane().add(labelLogoSF, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 77));

        btnBuscarLicencias.setText("Buscar licencias");
        btnBuscarLicencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarLicenciasActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscarLicencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 100, -1, -1));

        observacionesTextArea.setColumns(20);
        observacionesTextArea.setRows(5);
        jScrollPane1.setViewportView(observacionesTextArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 600, 70));

        jLabel1.setText("Observaciones");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        backToMenuListener.mostrarMenuPrincipal();
        this.dispose();
    }//GEN-LAST:event_cancelarButtonActionPerformed

    private void btnEmitirCopiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmitirCopiaActionPerformed
        dniEsValido();
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
            
            
            String observaciones = observacionesTextArea.getText();
            
            
                licenciaController.emitirLicencia(licencia,observaciones); 
                JOptionPane.showMessageDialog(
                    null,
                    "Se emiti copia de la licencia con éxito.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                backToMenuListener.mostrarMenuPrincipal();
            
        } catch(NoSuchElementException e){
            JOptionPane.showMessageDialog(
                null,
                "Hubo un error encontrando la licencia a copiar",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEmitirCopiaActionPerformed

    private void btnBuscarLicenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarLicenciasActionPerformed
        
        if(dniEsValido()){
            setTable();   
        }
        
    }//GEN-LAST:event_btnBuscarLicenciasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarLicencias;
    private javax.swing.JButton btnEmitirCopia;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JLabel labelMenuPrincipal;
    private javax.swing.JTable licenciasTable;
    private javax.swing.JFormattedTextField numDocField;
    private javax.swing.JTextArea observacionesTextArea;
    private javax.swing.JPanel panelEncabezado;
    // End of variables declaration//GEN-END:variables
}
