package com.municipalidad.licencias.appLicencias.ui;







import com.municipalidad.licencias.appLicencias.controller.LicenciaController;
import com.municipalidad.licencias.appLicencias.model.Licencia;
import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.singleton.SesionMenuPrincipal;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pabli
 */
public class PantallaListarLicencias extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PantallaListarLicencias.class.getName());

    /**
     * Creates new form PantallaListarLicencias
     */
    LicenciaController licenciaController;
    
    public PantallaListarLicencias() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png")));
    }
    
    public PantallaListarLicencias(LicenciaController licenciaControl) {
        licenciaController = licenciaControl;
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png")));
        inicializarPantalla();
    }
    public void inicializarPantalla() {
    List<Licencia> licencias = licenciaController.obtenerLicenciasVigentesFiltradas(null, null, null);
    cargarTabla(licencias);
}
    

     public void cargarTabla(List<Licencia> licencias) {
    if (licencias.isEmpty()) {
        inicializarPantalla();
        JOptionPane.showMessageDialog(null, "No hay licencias vigentes para mostrar.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
    } else {
        String[] columnas = {"Nombre", "Apellido", "Clase Licencia", "Fecha de Vencimiento", "Grupo Sanguíneo", "Donante"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Licencia licencia : licencias) {
            Titular titular = licencia.getTitular();
            String nombre = titular != null ? titular.getNombre() : "Sin titular";
            String clase = licencia.getClaseLicencia().toString();
            String fechaVenc = licencia.getFechaVencimiento() != null ? licencia.getFechaVencimiento().toString() : "-";
            String grupo = titular != null ? 
    String.valueOf(titular.getGrupoSanguineo()) + String.valueOf(titular.getFactorSanguineo()) 
    : "-";
            String donante = titular != null && titular.isEsDonante() ? "Sí" : "No";

            modelo.addRow(new Object[]{nombre, clase, fechaVenc, grupo, donante});
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
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        grupoDD = new javax.swing.JComboBox<>();
        factorDD = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        grupoDD1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        scroll3 = new javax.swing.JScrollPane();
        tablaLicencias = new javax.swing.JTable();
        cancelarButton = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelLogoSF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N

        labelMenuPrincipal.setText("Listar Licencias - Sistema de gestión de licencias");

        jLabel9.setText("Filtrar por:");

        jLabel11.setText("Apellido");

        jLabel10.setText("Nombre");

        jLabel8.setText("Donante");

        grupoDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS", "SI", "NO" }));

        factorDD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS", "+", "-" }));

        jLabel6.setText("Grupo Sanguíneo");

        grupoDD1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TODOS", "O", "A", "B" }));

        jLabel7.setText("Factor Sanguíneo");

        jButton1.setText("Filtrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(202, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labelLogoSF)
                            .addGap(202, 202, 202)
                            .addComponent(labelMenuPrincipal))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(jLabel9))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(jLabel11)
                            .addGap(136, 136, 136)
                            .addComponent(jLabel10)
                            .addGap(136, 136, 136)
                            .addComponent(jLabel8)
                            .addGap(35, 35, 35)
                            .addComponent(grupoDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(80, 80, 80)
                            .addComponent(jLabel7)
                            .addGap(18, 18, 18)
                            .addComponent(grupoDD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(38, 38, 38)
                            .addComponent(jLabel6)
                            .addGap(28, 28, 28)
                            .addComponent(factorDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(270, 270, 270)
                            .addComponent(jButton1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(scroll3, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(260, 260, 260)
                            .addComponent(cancelarButton)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(405, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelLogoSF)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(labelMenuPrincipal)))
                    .addGap(29, 29, 29)
                    .addComponent(jLabel9)
                    .addGap(14, 14, 14)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel11)
                        .addComponent(jLabel10)
                        .addComponent(jLabel8)
                        .addComponent(grupoDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7)
                        .addComponent(grupoDD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(factorDD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jButton1)
                    .addGap(7, 7, 7)
                    .addComponent(scroll3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(13, 13, 13)
                    .addComponent(cancelarButton)
                    .addGap(0, 12, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    String nombre = jTextField2.getText().trim();
    String apellido = jTextField1.getText().trim();
    String nombreApellido = (nombre + " " + apellido).trim();
    if (nombreApellido.isBlank()) nombreApellido = null;

    // Grupo + Factor
    String grupo = grupoDD1.getSelectedItem().toString();
    String factor = factorDD.getSelectedItem().toString();
    String grupoCompleto = null;

    if (!grupo.equalsIgnoreCase("TODOS") && !factor.equalsIgnoreCase("TODOS")) {
        grupoCompleto = grupo + factor; // Ej: "A+"
    }

    // Donante
    String donanteStr = grupoDD.getSelectedItem().toString();
    Boolean esDonante = null;
    if (donanteStr.equalsIgnoreCase("SI")) esDonante = true;
    else if (donanteStr.equalsIgnoreCase("NO")) esDonante = false;

    List<Licencia> licencias = licenciaController.obtenerLicenciasVigentesFiltradas(
        nombreApellido,
        grupoCompleto,
        esDonante
    );

    cargarTabla(licencias);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cancelarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarButtonActionPerformed
        SesionMenuPrincipal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelarButtonActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarButton;
    private javax.swing.JComboBox<String> factorDD;
    private javax.swing.JComboBox<String> grupoDD;
    private javax.swing.JComboBox<String> grupoDD1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JLabel labelMenuPrincipal;
    private javax.swing.JScrollPane scroll3;
    private javax.swing.JTable tablaLicencias;
    // End of variables declaration//GEN-END:variables
}
