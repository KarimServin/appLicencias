package com.municipalidad.licencias.appLicencias.modules.menu;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


public class MenuView extends javax.swing.JFrame {
    

     
    public MenuView() {
        
        initComponents();
        this.pack();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png")));
        this.setLocationRelativeTo(null);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void setLabelBienvenida(String texto) {
    
        labelBienvenidaNombre.setText("Bienvenido. Usted se ha logueado como "+ texto + ".");
    
    }
    
    public void desactivarBotones() {
    
        btnAltaUsuario.setEnabled(false);
        btnModificarDatosUsuario.setEnabled(false);
        btnGestionarCostos.setEnabled(false);
        btnConsultarOperaciones.setEnabled(false);
    }
    
    
    public void setEmitirNuevaLicenciaAction(ActionListener listener) {
        btnEmitirNuevaLicencia.addActionListener(listener);
    }

    public void setEmitirCopiaLicenciaAction(ActionListener listener) {
        btnEmitirCopiaLicencia.addActionListener(listener);
    }

    public void setAltaTitularAction(ActionListener listener) {
        btnAltaTitular.addActionListener(listener);
    }

    public void setConsultarLicenciasAction(ActionListener listener) {
        btnConsultarLicencias.addActionListener(listener);
    }


    public void setAltaUsuarioAction(ActionListener listener) {
        btnAltaUsuario.addActionListener(listener);
    }
    
    public void setModificarDatosTitularAction(ActionListener listener) {
        btnModificarDatosTitular.addActionListener(listener);
    }

    public void setModificarDatosUsuarioAction(ActionListener listener) {
        btnModificarDatosUsuario.addActionListener(listener);
    }
    
    public void setGestionarCostosAction(ActionListener listener) {
        btnGestionarCostos.addActionListener(listener);
    }

    public void setSalirAction(ActionListener listener) {
        btnSALIR.addActionListener(listener);
    }

    public void setConsultarOperacionesAction(ActionListener listener) {
        btnConsultarOperaciones.addActionListener(listener);
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelOpciones = new javax.swing.JPanel();
        btnAltaTitular = new javax.swing.JButton();
        btnConsultarLicencias = new javax.swing.JButton();
        btnEmitirCopiaLicencia = new javax.swing.JButton();
        btnAltaUsuario = new javax.swing.JButton();
        btnModificarDatosUsuario = new javax.swing.JButton();
        btnEmitirNuevaLicencia = new javax.swing.JButton();
        btnModificarDatosTitular = new javax.swing.JButton();
        btnSALIR = new javax.swing.JButton();
        btnGestionarCostos = new javax.swing.JButton();
        btnConsultarOperaciones = new javax.swing.JButton();
        panelBienvenida = new javax.swing.JPanel();
        panelEncabezado = new javax.swing.JPanel();
        labelLogoSF = new javax.swing.JLabel();
        labelMenuPrincipal = new javax.swing.JLabel();
        labelBienvenidaNombre = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        btnAltaTitular.setBackground(new java.awt.Color(153, 255, 204));
        btnAltaTitular.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        btnAltaTitular.setText("Alta de Titular");
        btnAltaTitular.setMaximumSize(new java.awt.Dimension(153, 31));

        btnConsultarLicencias.setBackground(new java.awt.Color(153, 255, 204));
        btnConsultarLicencias.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        btnConsultarLicencias.setText("Consultar licencias");

        btnEmitirCopiaLicencia.setBackground(new java.awt.Color(153, 255, 204));
        btnEmitirCopiaLicencia.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        btnEmitirCopiaLicencia.setText("Imprimir copia de licencia");

        btnAltaUsuario.setBackground(new java.awt.Color(153, 255, 204));
        btnAltaUsuario.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        btnAltaUsuario.setText("Alta de Usuario");
        btnAltaUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltaUsuarioActionPerformed(evt);
            }
        });

        btnModificarDatosUsuario.setBackground(new java.awt.Color(153, 255, 204));
        btnModificarDatosUsuario.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        btnModificarDatosUsuario.setText("Gestionar usuarios existentes");

        btnEmitirNuevaLicencia.setBackground(new java.awt.Color(153, 255, 204));
        btnEmitirNuevaLicencia.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        btnEmitirNuevaLicencia.setText("Emitir licencia");
        btnEmitirNuevaLicencia.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnModificarDatosTitular.setBackground(new java.awt.Color(153, 255, 204));
        btnModificarDatosTitular.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        btnModificarDatosTitular.setText("Modificar datos de titular");

        btnSALIR.setBackground(new java.awt.Color(255, 204, 204));
        btnSALIR.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        btnSALIR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salida-de-emergencia.png"))); // NOI18N
        btnSALIR.setText("Salir");

        btnGestionarCostos.setBackground(new java.awt.Color(153, 255, 204));
        btnGestionarCostos.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        btnGestionarCostos.setText("Gestionar costos de licencia");

        btnConsultarOperaciones.setBackground(new java.awt.Color(153, 255, 204));
        btnConsultarOperaciones.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        btnConsultarOperaciones.setText("Consultar registro de operaciones");

        javax.swing.GroupLayout panelOpcionesLayout = new javax.swing.GroupLayout(panelOpciones);
        panelOpciones.setLayout(panelOpcionesLayout);
        panelOpcionesLayout.setHorizontalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcionesLayout.createSequentialGroup()
                        .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnEmitirCopiaLicencia, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                                .addComponent(btnEmitirNuevaLicencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnConsultarLicencias, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAltaTitular, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnModificarDatosTitular, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAltaUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelOpcionesLayout.createSequentialGroup()
                        .addComponent(btnModificarDatosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGestionarCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcionesLayout.createSequentialGroup()
                        .addComponent(btnConsultarOperaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSALIR, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelOpcionesLayout.setVerticalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEmitirNuevaLicencia, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAltaTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEmitirCopiaLicencia, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarDatosTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnConsultarLicencias, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(btnAltaUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarDatosUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGestionarCostos, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSALIR, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultarOperaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        labelLogoSF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N

        labelMenuPrincipal.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 24)); // NOI18N
        labelMenuPrincipal.setText("Menú Principal - Sistema de Gestión de Licencias");

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelMenuPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(labelLogoSF))
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelLogoSF)
                    .addComponent(labelMenuPrincipal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelBienvenidaNombre.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 14)); // NOI18N
        labelBienvenidaNombre.setText("Ud. se autenticó como ");

        javax.swing.GroupLayout panelBienvenidaLayout = new javax.swing.GroupLayout(panelBienvenida);
        panelBienvenida.setLayout(panelBienvenidaLayout);
        panelBienvenidaLayout.setHorizontalGroup(
            panelBienvenidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEncabezado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBienvenidaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelBienvenidaNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBienvenidaLayout.setVerticalGroup(
            panelBienvenidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBienvenidaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelBienvenidaNombre)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        getContentPane().add(panelBienvenida, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAltaUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAltaUsuarioActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAltaTitular;
    private javax.swing.JButton btnAltaUsuario;
    private javax.swing.JButton btnConsultarLicencias;
    private javax.swing.JButton btnConsultarOperaciones;
    private javax.swing.JButton btnEmitirCopiaLicencia;
    private javax.swing.JButton btnEmitirNuevaLicencia;
    private javax.swing.JButton btnGestionarCostos;
    private javax.swing.JButton btnModificarDatosTitular;
    private javax.swing.JButton btnModificarDatosUsuario;
    private javax.swing.JButton btnSALIR;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelBienvenidaNombre;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JLabel labelMenuPrincipal;
    private javax.swing.JPanel panelBienvenida;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JPanel panelOpciones;
    // End of variables declaration//GEN-END:variables
}
