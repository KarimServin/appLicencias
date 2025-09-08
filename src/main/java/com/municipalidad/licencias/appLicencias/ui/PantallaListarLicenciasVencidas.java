package com.municipalidad.licencias.appLicencias.ui;

import com.municipalidad.licencias.appLicencias.controller.LicenciaController;
import com.municipalidad.licencias.appLicencias.model.Licencia;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.municipalidad.licencias.appLicencias.navigation.BackToMenuListener;


public class PantallaListarLicenciasVencidas extends javax.swing.JFrame {
    
    BackToMenuListener backToMenuListener;
    LicenciaController licenciaController;
    

    
    public PantallaListarLicenciasVencidas(LicenciaController licenciaController, BackToMenuListener backToMenuListener) {
        this.licenciaController = licenciaController;
        this.backToMenuListener = backToMenuListener;
        
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png")));
        cargarTabla();
    }


    private void cargarTabla(){
        List<Licencia> l = licenciaController.obtenerLicenciasExpiradas();

        if (l.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay licencias expiradas para mostrar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String[] columnas = {"Nombre", "Clase Licencia", "Fecha de Vencimiento"};
            DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            for (Licencia licencia : l) {
                String nombre = licencia.getTitular() != null ? licencia.getTitular().getNombre() : "Sin titular";
                String clase = licencia.getClaseLicencia().toString();
                String fechaVenc = licencia.getFechaVencimiento() != null ? licencia.getFechaVencimiento().toString() : "-";

                modelo.addRow(new Object[]{nombre, clase, fechaVenc});
            }

            tablaLicencias.setModel(modelo);
            scroll3.setViewportView(tablaLicencias);
        }
       
    }
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelLogoSF = new javax.swing.JLabel();
        labelMenuPrincipal = new javax.swing.JLabel();
        scroll3 = new javax.swing.JScrollPane();
        tablaLicencias = new javax.swing.JTable();
        cancelarButton = new javax.swing.JButton();
        aceptarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelLogoSF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N

        labelMenuPrincipal.setText("Licencias vencidas - Sistema de gestión de licencias");

        tablaLicencias.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tablaLicencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido", "Clase Licencia", "Fecha de vencimiento "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scroll3.setViewportView(tablaLicencias);

        cancelarButton.setText("Volver");
        cancelarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarButtonActionPerformed(evt);
            }
        });

        aceptarButton.setText("Actualizar");
        aceptarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelLogoSF)
                .addGap(192, 192, 192)
                .addComponent(labelMenuPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(49, 49, 49))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelarButton)
                .addGap(68, 68, 68)
                .addComponent(aceptarButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(scroll3, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelLogoSF)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(labelMenuPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5)))
                .addGap(9, 9, 9)
                .addComponent(scroll3, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelarButton)
                    .addComponent(aceptarButton))
                .addGap(74, 74, 74))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        backToMenuListener.mostrarMenuPrincipal();
        this.dispose();
    }//GEN-LAST:event_cancelarButtonActionPerformed

    private void aceptarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarButtonActionPerformed
        cargarTabla();
    }//GEN-LAST:event_aceptarButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JLabel labelMenuPrincipal;
    private javax.swing.JScrollPane scroll3;
    private javax.swing.JTable tablaLicencias;
    // End of variables declaration//GEN-END:variables
}
