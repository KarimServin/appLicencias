package com.municipalidad.licencias.appLicencias.ui;

import com.municipalidad.licencias.appLicencias.controller.LicenciaController;
import com.municipalidad.licencias.appLicencias.controller.TitularController;
import com.municipalidad.licencias.appLicencias.model.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.model.Licencia;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import com.municipalidad.licencias.appLicencias.navigation.BackToMenuListener;
import com.municipalidad.licencias.appLicencias.session.SessionInfo;
import javax.swing.table.DefaultTableModel;

public class PantallaRenovarLicencia extends javax.swing.JFrame {
    
    
    LicenciaController licenciaController;
    TitularController titularController;
    SessionInfo SessionInfo;
    BackToMenuListener backToMenuListener;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  
    public PantallaRenovarLicencia(LicenciaController licenciaController, 
                                   TitularController titularController, 
                                   SessionInfo userSession, 
                                   BackToMenuListener backToMenuListener) {
        this.licenciaController = licenciaController;
        this.titularController = titularController;
        this.SessionInfo = userSession;
        this.backToMenuListener = backToMenuListener;
        
        initComponents();
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png")));
        licenciasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    
    public PantallaRenovarLicencia(LicenciaController licenciaController, 
                                   TitularController titularController, 
                                   String dni) {
        this.licenciaController = licenciaController;
        this.titularController = titularController;
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png")));
        numDniField.setText(dni);
        licenciasTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setTable();
    }
    
    private void setTable() {
        
        if (!dniEsValido()) return;
        
        numDniField.setEnabled(false);
        buscarLicenciasButton.setEnabled(false);
        
        try {

            //Nombres de columnas que tendrá la tabla
            final String[] columnas = {
                "Clase",
                "Fecha de emisión",
                "Fecha de vencimiento"
            };


            /* Crea un modelo de tabla con 0 filas y luego sobreescribe 
            isCellEditable usando clase anónima para que no se pueda editar 
            ninguna celda*/
            DefaultTableModel model = new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
            };

            licenciasTable.setModel(model);


            Long dni = Long.valueOf(numDniField.getText().trim());
            
            //Esto esta mal. Se estan trayendo entidades JPA a la vista.
            List<Licencia> licencias = licenciaController.obtenerLicenciasPorTitular(dni);
            

            /* Añadir las licencias a la tabla si hay*/
            if (licencias != null) {
                for (Licencia l : licencias) {
                    model.addRow(new Object[] {
                    String.valueOf(l.getClaseLicencia()),
                    l.getFechaEmision() != null ? l.getFechaEmision().format(DTF) : "",
                    l.getFechaVencimiento() != null ? l.getFechaVencimiento().format(DTF) : ""
                    });
                }
            }
          
        } catch (Exception ex) {
            
            JOptionPane.showMessageDialog(this,
                "Error al cargar licencias: " + ex.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean dniEsValido() {
            String dni = numDniField.getText().trim();

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
    
    
    private void validarSeleccionTabla(){
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
        tipoDocDD = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        panelEncabezado = new javax.swing.JPanel();
        labelMenuPrincipal = new javax.swing.JLabel();
        labelLogoSF = new javax.swing.JLabel();
        numDniField = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        licenciasTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        observacionesField = new javax.swing.JTextField();
        buscarLicenciasButton = new javax.swing.JButton();

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
            numDniField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

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

        buscarLicenciasButton.setText("Buscar licencias");
        buscarLicenciasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarLicenciasButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(numDniField))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(observacionesField, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cancelarButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(renovarButton))
                            .addComponent(buscarLicenciasButton, javax.swing.GroupLayout.Alignment.TRAILING))))
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
                    .addComponent(numDniField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buscarLicenciasButton)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(observacionesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(renovarButton)
                    .addComponent(cancelarButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void renovarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_renovarButtonActionPerformed
        
        validarSeleccionTabla();
        String clase = licenciasTable.getValueAt(licenciasTable.getSelectedRow(), 0).toString();
        LocalDate fechae = LocalDate.parse(licenciasTable.getValueAt(licenciasTable.getSelectedRow(), 1).toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate fechav = LocalDate.parse(licenciasTable.getValueAt(licenciasTable.getSelectedRow(), 2).toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Licencia licencia;
        List<Licencia> licencias = new ArrayList();
        licencias = licenciaController.obtenerLicenciasPorTitular(Long.valueOf(numDniField.getText()));
        try{
            licencia = licencias.stream()
                .filter(l -> l.getClaseLicencia().equals(ClaseLicencia.valueOf(clase))
              && l.getFechaEmision().isEqual(fechae)
              && l.getFechaVencimiento().isEqual(fechav))
            .findFirst().get();
            System.out.println("licencia encontrada: \n clase: " + licencia.getClaseLicencia() + "\nfechae: " + licencia.getFechaEmision()
            + "\nfechav: " + licencia.getFechaVencimiento()
            + "\nid:" + licencia.getId());
            licenciaController.renovarLicencia(licencia, observacionesField.getText());
                JOptionPane.showMessageDialog(
                    null,
                    "La licencia se renovo con éxito.",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                backToMenuListener.mostrarMenuPrincipal();
            
        } catch(NoSuchElementException e){
            JOptionPane.showMessageDialog(
                null,
                "Hubo un error encontrando la licencia a renovar",
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_renovarButtonActionPerformed

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        backToMenuListener.mostrarMenuPrincipal();
        this.dispose();
    }//GEN-LAST:event_cancelarButtonActionPerformed

    private void buscarLicenciasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarLicenciasButtonActionPerformed
      
        if (dniEsValido()) {
            
            setTable();
        }      
    }//GEN-LAST:event_buscarLicenciasButtonActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarLicenciasButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JLabel labelMenuPrincipal;
    private javax.swing.JTable licenciasTable;
    private javax.swing.JFormattedTextField numDniField;
    private javax.swing.JTextField observacionesField;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JButton renovarButton;
    private javax.swing.JComboBox<String> tipoDocDD;
    // End of variables declaration//GEN-END:variables
}
