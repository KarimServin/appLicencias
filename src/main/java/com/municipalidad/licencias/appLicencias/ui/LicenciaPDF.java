/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.municipalidad.licencias.appLicencias.ui;

import com.municipalidad.licencias.appLicencias.model.Licencia;
import com.municipalidad.licencias.appLicencias.model.Titular;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;

/**
 *
 * @author pabli
 */
public class LicenciaPDF extends javax.swing.JPanel implements Printable{

    /**
     * Creates new form LicenciaPDF
     */
    public LicenciaPDF() {
        initComponents();
    }

    public LicenciaPDF(Titular titular,Licencia licencia) {
        initComponents();
        txtTipoLicencia.setText(licencia.getClaseLicencia().name());
        txtFechaVencimiento.setText(licencia.getFechaVencimiento().toString());
        txtApellido.setText(titular.getNombre());
        txtFechaNacimiento.setText(titular.getFechaNacimiento().toString());
        txtDireccion.setText(titular.getDireccion());
        txtLicencia.setText(titular.getDni().toString());
        txtGrupoSanguineo.setText(Character.toString(titular.getGrupoSanguineo()));
        
        if(titular.isEsDonante()){
           txtDonante.setText("SI"); 
        }else{
            txtDonante.setText("NO"); 
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
     @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        
        /*if(pageIndex==0){
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            printAll(graphics2D);
            return PAGE_EXISTS;
        }else{
            return NO_SUCH_PAGE;
        }*/
        
        if (pageIndex == 0) {
        Graphics2D g2d = (Graphics2D) graphics;
        
        // Ajustamos al área imprimible
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        // Dibujar título arriba centrado
        String titulo = "LICENCIA DE CONDUCIR";
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        FontMetrics fm = g2d.getFontMetrics();
        int tituloX = (int) ((pageFormat.getImageableWidth() - fm.stringWidth(titulo)) / 2);
        g2d.drawString(titulo, tituloX, 50); // Altura 50 px desde arriba

        // Posicionar el JPanel más abajo y centrado
        int panelX = (int) ((pageFormat.getImageableWidth() - this.getWidth()) / 2);
        int panelY = 100; // Dejar espacio entre título y panel

        g2d.translate(panelX, panelY); // mueve el origen de coordenadas
        this.printAll(g2d);

        return PAGE_EXISTS;
    }
    return NO_SUCH_PAGE;
        
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        txtFechaVencimiento = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtDonante = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txtFechaNacimiento = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txtGrupoSanguineo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtLicencia = new javax.swing.JLabel();
        txtApellido = new javax.swing.JLabel();
        txtTipoLicencia = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Ministerio de Transporte");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, -1, -1));

        txtFechaVencimiento.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtFechaVencimiento.setText("12/03/2024");
        add(txtFechaVencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 200, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 153, 255));
        jLabel6.setText("SANTA FE - CORONDA");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        txtDonante.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtDonante.setText("SI");
        add(txtDonante, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 190, -1));

        jLabel11.setText("Grupo Sanguíneo");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, -1, 20));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 153, 255));
        jLabel2.setText("Licencia clase");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, -1, -1));

        jSeparator2.setForeground(new java.awt.Color(51, 153, 255));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 470, 20));

        txtFechaNacimiento.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtFechaNacimiento.setText("02/09/2000");
        add(txtFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 200, -1));

        jLabel8.setText("Apellido y nombre");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel12.setText("Fecha de Vencimiento");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        txtDireccion.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtDireccion.setText("FALTA DIRECCIONNN");
        add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, -1, 30));

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 10, 40));

        txtGrupoSanguineo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtGrupoSanguineo.setText("O+");
        add(txtGrupoSanguineo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 80, -1));

        jSeparator1.setForeground(new java.awt.Color(51, 153, 255));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 470, 20));

        jLabel17.setText("N° Licencia");
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("VIAL");
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/argentina (1).png"))); // NOI18N
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/escudo-de-armas (4).png"))); // NOI18N
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, -1, -1));

        jLabel14.setText("Dirección");
        add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, -1, -1));

        txtLicencia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtLicencia.setText("42765715");
        add(txtLicencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, -1, -1));

        txtApellido.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtApellido.setText("BRAVO");
        add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        txtTipoLicencia.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtTipoLicencia.setText("A1");
        add(txtTipoLicencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, -1, -1));

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("República Argentina");
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, -1, -1));

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("SEGURIDAD");
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, -1, -1));

        jLabel9.setText("Donante");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("LICENCIA NACIONAL DE CONDUCIR");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        jLabel16.setText("Fecha de Nacimiento");
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/FondoAzul.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 470, 60));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel txtApellido;
    private javax.swing.JLabel txtDireccion;
    private javax.swing.JLabel txtDonante;
    private javax.swing.JLabel txtFechaNacimiento;
    private javax.swing.JLabel txtFechaVencimiento;
    private javax.swing.JLabel txtGrupoSanguineo;
    private javax.swing.JLabel txtLicencia;
    private javax.swing.JLabel txtTipoLicencia;
    // End of variables declaration//GEN-END:variables
}
