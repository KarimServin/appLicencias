package com.municipalidad.licencias.appLicencias.ui;

import com.municipalidad.licencias.appLicencias.controller.LicenciaController;
import com.municipalidad.licencias.appLicencias.model.Licencia;
import com.municipalidad.licencias.appLicencias.model.Titular;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.municipalidad.licencias.appLicencias.navigation.BackToMenuListener;


public class PantallaListarLicencias extends javax.swing.JFrame {
    
    private final LicenciaController licenciaController;
    private final BackToMenuListener backToMenuListener;

    
    public PantallaListarLicencias(LicenciaController licenciaController, BackToMenuListener backToMenuListener) {
        
        this.licenciaController = licenciaController;
        this.backToMenuListener = backToMenuListener;

        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png")));
       
       
    }

    

     public void cargarTabla(List<Licencia> licencias) {
         
            if (licencias == null || licencias.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No hay licencias vigentes para mostrar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    String[] columnas = {"Nombre", "Apellido", "Clase Licencia", "Fecha de Vencimiento", "Grupo Sanguíneo", "Donante"};

    DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            for (Licencia licencia : licencias) {
                Titular titular = licencia.getTitular();

                String nombre = "-";
                String apellido = "-";
                String clase = "-";
                String fechaVenc = "-";
                String grupoSanguineo = "-";
                String donante = "No";

                if (titular != null) {
                    nombre = titular.getNombre() != null ? titular.getNombre() : "-";
                    apellido = titular.getApellido() != null ? titular.getApellido() : "-";
                    grupoSanguineo = "1";
                    donante = titular.isEsDonante() ? "Sí" : "No";
                }

                clase = licencia.getClaseLicencia() != null ? licencia.getClaseLicencia().toString() : "-";
                fechaVenc = licencia.getFechaVencimiento() != null ? licencia.getFechaVencimiento().toString() : "-";

                modelo.addRow(new Object[]{nombre, apellido, clase, fechaVenc, grupoSanguineo, donante});
            }

            tablaLicencias.setModel(modelo);
            scroll3.setViewportView(tablaLicencias);
        
     }



             
               
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelLogoSF = new javax.swing.JLabel();
        labelMenuPrincipal = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        donanteCBox = new javax.swing.JComboBox<>();
        grupoSanguineoCBox = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        factorSanguineoCBox = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        filtrarButton = new javax.swing.JButton();
        scroll3 = new javax.swing.JScrollPane();
        tablaLicencias = new javax.swing.JTable();
        cancelarButton = new javax.swing.JButton();
        apellidoField = new javax.swing.JTextField();
        nombreField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelLogoSF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N

        labelMenuPrincipal.setText("Listar Licencias - Sistema de gestión de licencias");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Aplicar criterios de filtrado");

        jLabel11.setText("Apellido");

        jLabel10.setText("Nombre");

        jLabel8.setText("Donante");

        donanteCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS", "SI", "NO" }));

        grupoSanguineoCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS", "+", "-" }));

        jLabel6.setText("Grupo Sanguíneo");

        factorSanguineoCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS", "O", "A", "B" }));

        jLabel7.setText("Factor Sanguíneo");

        filtrarButton.setText("Filtrar");
        filtrarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filtrarButtonActionPerformed(evt);
            }
        });

        tablaLicencias.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tablaLicencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Documento", "Nombre", "Direccion", "Fecha Nacimiento", "Fecha vencimiento", "Grupo Sanguineo", "Factor Sanguineo", "Donante"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(filtrarButton)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelMenuPrincipal)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(apellidoField, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(grupoSanguineoCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(donanteCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelLogoSF)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(jLabel9))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(jLabel11)
                            .addGap(136, 136, 136)
                            .addComponent(jLabel10)
                            .addGap(136, 136, 136)
                            .addComponent(jLabel8))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(80, 80, 80)
                            .addComponent(jLabel7)
                            .addGap(18, 18, 18)
                            .addComponent(factorSanguineoCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(scroll3, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(260, 260, 260)
                            .addComponent(cancelarButton)))
                    .addGap(0, 18, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(labelMenuPrincipal)
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(donanteCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grupoSanguineoCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(14, 14, 14)
                .addComponent(filtrarButton)
                .addContainerGap(328, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addComponent(labelLogoSF)
                    .addGap(29, 29, 29)
                    .addComponent(jLabel9)
                    .addGap(14, 14, 14)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel11)
                        .addComponent(jLabel10)
                        .addComponent(jLabel8))
                    .addGap(24, 24, 24)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7)
                        .addComponent(factorSanguineoCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(48, 48, 48)
                    .addComponent(scroll3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(13, 13, 13)
                    .addComponent(cancelarButton)
                    .addGap(0, 12, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filtrarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filtrarButtonActionPerformed
    
                //Nombre y Apellido
                String nombre = nombreField.getText().trim();
                if (nombre.isBlank()) nombre = null;

                String apellido = apellidoField.getText().trim();
                if (apellido.isBlank()) apellido = null;

                //Grupo Sanguineo
                String grupo = grupoSanguineoCBox.getSelectedItem().toString();
                if (grupo.equalsIgnoreCase("TODOS")) grupo = null;

                //Factor Sanguíneo
                String factor = factorSanguineoCBox.getSelectedItem().toString();
                if (factor.equalsIgnoreCase("TODOS")) factor = null;

                //Donante
                String donanteStr = donanteCBox.getSelectedItem().toString();
                Boolean esDonante = null;
                if (donanteStr.equalsIgnoreCase("SI")) esDonante = true;
                else if (donanteStr.equalsIgnoreCase("NO")) esDonante = false;

                // 5. Llamada al controlador
                List<Licencia> licencias = licenciaController.obtenerLicenciasVigentesFiltradas(
                    nombre,
                    apellido,
                    grupo,
                    factor,
                    esDonante
                );

                // 6. Cargar tabla
                cargarTabla(licencias);
    }//GEN-LAST:event_filtrarButtonActionPerformed

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        backToMenuListener.mostrarMenuPrincipal();
        this.dispose();
    }//GEN-LAST:event_cancelarButtonActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidoField;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JComboBox<String> donanteCBox;
    private javax.swing.JComboBox<String> factorSanguineoCBox;
    private javax.swing.JButton filtrarButton;
    private javax.swing.JComboBox<String> grupoSanguineoCBox;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JLabel labelMenuPrincipal;
    private javax.swing.JTextField nombreField;
    private javax.swing.JScrollPane scroll3;
    private javax.swing.JTable tablaLicencias;
    // End of variables declaration//GEN-END:variables
}
