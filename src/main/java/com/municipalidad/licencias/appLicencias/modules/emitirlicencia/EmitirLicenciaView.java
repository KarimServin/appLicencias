package com.municipalidad.licencias.appLicencias.modules.emitirlicencia;

import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.view.Dialogs;
import java.awt.Rectangle;
import java.awt.Toolkit; 
import java.awt.event.ActionListener;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class EmitirLicenciaView extends javax.swing.JFrame {
    

      
    public EmitirLicenciaView() {
     
        initComponents();    
        this.setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png")));
        
        infoTitularLabel.setVisible(false);
        configurarChecklistClases();
        
    }
    
   
    public void setAceptarAction(ActionListener listener) {
        aceptarButton.addActionListener(listener);
    }
    
    public void setCancelarAction(ActionListener listener) {
        cancelarButton.addActionListener(listener);
    }
    
    public void setBuscarTitularAction(ActionListener listener) {
        buscarTitularButton.addActionListener(listener);
    }
    
    public String getDni() {
        return numDocField.getText().trim();
    }

    public Set<ClaseLicencia> getClasesSeleccionadas() {
        Set<ClaseLicencia> out = new HashSet<>();
        claseChecks.forEach((k, v) -> {
            if (v.isSelected()) out.add(k);
        });
        return out;
    }
    
    
    public void setInfoTitular(String dni, 
                               String nombre, 
                               String apellido) {
    
    
        infoTitularLabel.setText("Ud. ha seleccionado emitir licencia para el titular DNI: " 
                                + dni + ", " 
                                + apellido + ", " 
                                + nombre + ".");
        
        infoTitularLabel.setVisible(true); 
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelEncabezado = new javax.swing.JPanel();
        labelMenuPrincipal = new javax.swing.JLabel();
        labelLogoSF = new javax.swing.JLabel();
        numDocField = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        buscarTitularButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        aceptarButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();
        claseLicenciaComboBox = new javax.swing.JComboBox<>();
        infoTitularLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setSize(new java.awt.Dimension(590, 254));

        labelMenuPrincipal.setText("Emitir Nueva Licencia - Sistema de gestión de licencias");

        labelLogoSF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelLogoSF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
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

        jLabel2.setText("N° de DNI");

        buscarTitularButton.setText("Buscar titular");

        aceptarButton.setText("Aceptar");

        cancelarButton.setText("Cancelar");

        claseLicenciaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        infoTitularLabel.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelarButton)
                        .addGap(18, 18, 18)
                        .addComponent(aceptarButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(infoTitularLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(claseLicenciaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(claseLicenciaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(infoTitularLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aceptarButton)
                    .addComponent(cancelarButton)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(numDocField, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buscarTitularButton))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numDocField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(buscarTitularButton)
                .addGap(67, 67, 67)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


        private void configurarChecklistClases() {

      JPanel panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

      for (ClaseLicencia c : ClaseLicencia.values()) {
          JCheckBox cb = new JCheckBox(formatearTextoClase(c));
          claseChecks.put(c, cb);
          panel.add(cb);
      }

      JScrollPane scroll = new JScrollPane(panel);
      scroll.setBorder(null);

      Rectangle r = claseLicenciaComboBox.getBounds();

      jPanel1.setLayout(null);
      claseLicenciaComboBox.setVisible(false);

      scroll.setBounds(r.x, r.y, r.width, r.height + 90);
      jPanel1.add(scroll);

      jPanel1.revalidate();
      jPanel1.repaint();
  }

    // --- NUEVO ---
    private String formatearTextoClase(ClaseLicencia c) {
        // Texto legible (podés moverlo a un enum enriquecido si querés)
        return switch (c) {
            case A -> "A - Ciclomotores, motocicletas y triciclos motorizados";
            case B -> "B - Automóviles y camionetas con acoplado";
            case C -> "C - Camiones sin acoplado y los comprendidos en la clase B";
            case D -> "D - Transporte de pasajeros / emergencia / seguridad (según corresponda)";
            case E -> "E - Camiones articulados o con acoplado / maquinaria especial no agrícola";
            case F -> "F - Automotores adaptados para discapacitados";
            case G -> "G - Tractores agrícolas y maquinaria especial agrícola";
        };
    }


    
    //Checkboxes por enum
    private final Map<ClaseLicencia, JCheckBox> claseChecks = new EnumMap<>(ClaseLicencia.class);
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JButton buscarTitularButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JComboBox<String> claseLicenciaComboBox;
    private javax.swing.JLabel infoTitularLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JLabel labelMenuPrincipal;
    private javax.swing.JFormattedTextField numDocField;
    private javax.swing.JPanel panelEncabezado;
    // End of variables declaration//GEN-END:variables
}
