package com.municipalidad.licencias.appLicencias.modules.menu;

import java.awt.Toolkit;
import java.awt.event.ActionListener;


public class MenuView extends javax.swing.JFrame {
    

     
    public MenuView() {
        
        initComponents();

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png")));
        this.setLocationRelativeTo(null);  
    }
    
    public void setLabelBienvenida(String texto) {
    
        labelBienvenidaNombre.setText("Bienvenido. Usted se ha logueado como "+ texto + ".");
    
    }
    
    public void ocultarBotones() {
    
        btnAltaUsuario.setVisible(false);
        btnModificarDatosUsuario.setVisible(false);
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

    public void setListarLicenciasVigentesAction(ActionListener listener) {
        btnListarLicVigentes.addActionListener(listener);
    }

    public void setRenovarLicenciaAction(ActionListener listener) {
        btnRenovarLicencia.addActionListener(listener);
    }

    public void setModificarDatosTitularAction(ActionListener listener) {
        btnModificarDatosTitular.addActionListener(listener);
    }

    public void setLicExpiradasAction(ActionListener listener) {
        btnListarLicExpiradas.addActionListener(listener);
    }

    public void setAltaUsuarioAction(ActionListener listener) {
        btnAltaUsuario.addActionListener(listener);
    }

    public void setModificarDatosUsuarioAction(ActionListener listener) {
        btnModificarDatosUsuario.addActionListener(listener);
    }

    public void setSalirAction(ActionListener listener) {
        btnSALIR.addActionListener(listener);
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEncabezado = new javax.swing.JPanel();
        labelMenuPrincipal = new javax.swing.JLabel();
        labelLogoSF = new javax.swing.JLabel();
        panelBienvenida = new javax.swing.JPanel();
        labelBienvenidaNombre = new javax.swing.JLabel();
        panelOpciones = new javax.swing.JPanel();
        btnEmitirNuevaLicencia = new javax.swing.JButton();
        btnAltaTitular = new javax.swing.JButton();
        btnRenovarLicencia = new javax.swing.JButton();
        btnListarLicExpiradas = new javax.swing.JButton();
        btnEmitirCopiaLicencia = new javax.swing.JButton();
        btnListarLicVigentes = new javax.swing.JButton();
        btnModificarDatosTitular = new javax.swing.JButton();
        btnAltaUsuario = new javax.swing.JButton();
        btnModificarDatosUsuario = new javax.swing.JButton();
        btnSALIR = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        labelMenuPrincipal.setText("Menú Principal - Sistema de gestión de licencias");

        labelLogoSF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLogoSF)
                .addGap(149, 149, 149)
                .addComponent(labelMenuPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelLogoSF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(labelMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        labelBienvenidaNombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelBienvenidaNombre.setText("Bienvenido");

        javax.swing.GroupLayout panelBienvenidaLayout = new javax.swing.GroupLayout(panelBienvenida);
        panelBienvenida.setLayout(panelBienvenidaLayout);
        panelBienvenidaLayout.setHorizontalGroup(
            panelBienvenidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBienvenidaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelBienvenidaNombre)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBienvenidaLayout.setVerticalGroup(
            panelBienvenidaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBienvenidaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelBienvenidaNombre)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        btnEmitirNuevaLicencia.setText("Emitir Nueva Licencia");

        btnAltaTitular.setText("Alta de Titular");

        btnRenovarLicencia.setText("Renovar Licencia");

        btnListarLicExpiradas.setText("Listar licencias expiradas");

        btnEmitirCopiaLicencia.setText("Emitir Copia de Licencia");

        btnListarLicVigentes.setText("Listar Licencias Vigentes por criterio");

        btnModificarDatosTitular.setText("Modificar datos de titular existente");

        btnAltaUsuario.setBackground(new java.awt.Color(0, 204, 153));
        btnAltaUsuario.setText("Alta Usuario");

        btnModificarDatosUsuario.setBackground(new java.awt.Color(0, 204, 153));
        btnModificarDatosUsuario.setText("Modificar datos de Usuario");

        btnSALIR.setBackground(new java.awt.Color(255, 102, 102));
        btnSALIR.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSALIR.setText("Salir");

        javax.swing.GroupLayout panelOpcionesLayout = new javax.swing.GroupLayout(panelOpciones);
        panelOpciones.setLayout(panelOpcionesLayout);
        panelOpcionesLayout.setHorizontalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnModificarDatosUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .addComponent(btnAltaTitular, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .addComponent(btnRenovarLicencia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .addComponent(btnListarLicExpiradas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                    .addComponent(btnEmitirNuevaLicencia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
                .addGap(61, 61, 61)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAltaUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(btnEmitirCopiaLicencia, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(btnModificarDatosTitular, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(btnListarLicVigentes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addComponent(btnSALIR, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelOpcionesLayout.setVerticalGroup(
            panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOpcionesLayout.createSequentialGroup()
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEmitirNuevaLicencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEmitirCopiaLicencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnListarLicVigentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAltaTitular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRenovarLicencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnModificarDatosTitular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnListarLicExpiradas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAltaUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(47, 47, 47)
                .addGroup(panelOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarDatosUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSALIR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBienvenida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBienvenida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAltaTitular;
    private javax.swing.JButton btnAltaUsuario;
    private javax.swing.JButton btnEmitirCopiaLicencia;
    private javax.swing.JButton btnEmitirNuevaLicencia;
    private javax.swing.JButton btnListarLicExpiradas;
    private javax.swing.JButton btnListarLicVigentes;
    private javax.swing.JButton btnModificarDatosTitular;
    private javax.swing.JButton btnModificarDatosUsuario;
    private javax.swing.JButton btnRenovarLicencia;
    private javax.swing.JButton btnSALIR;
    private javax.swing.JLabel labelBienvenidaNombre;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JLabel labelMenuPrincipal;
    private javax.swing.JPanel panelBienvenida;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JPanel panelOpciones;
    // End of variables declaration//GEN-END:variables
}
