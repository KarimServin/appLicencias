package com.municipalidad.licencias.appLicencias.modules.emitircopialicencia;

import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;


public class EmitirCopiaLicenciaView extends javax.swing.JFrame {

    public EmitirCopiaLicenciaView() {
        initComponents();
        this.setLocationRelativeTo(null);  
        // Por defecto deshabilitados
        emitirCopiaButton.setEnabled(false);
        emitirComprobanteCheckbox.setSelected(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void setContinuarAction(ActionListener actionListener) {
        buscarTitularButton.addActionListener(actionListener);
    }

    public void setEmitirCopiaAction(ActionListener actionListener) {
        emitirCopiaButton.addActionListener(actionListener);
    }

    public void setCancelarAction(ActionListener actionListener) {
        cancelarButton.addActionListener(actionListener);
    }

    public void setLimpiarAction(ActionListener listener) {
        limpiarButton.addActionListener(listener);
    }

    public String getDni() {
        return dniTitularField.getText().trim();
    }

    public void setNombre(String nombre) {
        nombreTitularField.setText(nombre);
    }

    public void setUltimaLicencia(LicenciaDTO licencia) {
        if (licencia == null) {
            ultimaLicenciaTextField.setText("Sin licencias previas");
        } else {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            ultimaLicenciaTextField.setText(
                "Emitida: " + licencia.getFechaEmision().format(fmt) +
                " | Vence: " + licencia.getFechaVencimiento().format(fmt) +
                " | Clases: " + licencia.getClases()
            );
        }
    }

    /**
     * Habilita el botón de emitir copia después de encontrar un titular válido.
     */
    public void habilitarEmision() {
        emitirCopiaButton.setEnabled(true);
    }

    /**
     * Devuelve si el usuario quiere imprimir comprobante.
     */
    public boolean isEmitirComprobante() {
        return emitirComprobanteCheckbox.isSelected();
    }

    /**
     * Limpia todos los campos y deshabilita el botón de emitir.
     */
    public void limpiar() {
        dniTitularField.setText("");
        nombreTitularField.setText("");
        ultimaLicenciaTextField.setText("");
        emitirCopiaButton.setEnabled(false);
        emitirComprobanteCheckbox.setSelected(true);
    }
        
        

    

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dniTitularLabel = new javax.swing.JLabel();
        dniTitularField = new javax.swing.JTextField();
        buscarTitularButton = new javax.swing.JButton();
        emitirCopiaButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();
        limpiarButton = new javax.swing.JButton();
        titularEncontradoLabel = new javax.swing.JLabel();
        nombreTitularField = new javax.swing.JTextField();
        ultimaLicenciaLabel = new javax.swing.JLabel();
        ultimaLicenciaTextField = new javax.swing.JTextField();
        emitirComprobanteCheckbox = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dniTitularLabel.setText("DNI del titular:");

        buscarTitularButton.setText("Buscar Titular");

        emitirCopiaButton.setText("Emitir Copia");

        cancelarButton.setText("Cancelar");

        limpiarButton.setText("Limpiar");

        titularEncontradoLabel.setText("Titular encontrado:");

        nombreTitularField.setEditable(false);

        ultimaLicenciaLabel.setText("Última licencia:");

        ultimaLicenciaTextField.setEditable(false);

        emitirComprobanteCheckbox.setText("Emitir comprobante para el titular");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ultimaLicenciaLabel)
                            .addComponent(titularEncontradoLabel)
                            .addComponent(dniTitularLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dniTitularField)
                            .addComponent(nombreTitularField)
                            .addComponent(ultimaLicenciaTextField)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(emitirCopiaButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelarButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(limpiarButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buscarTitularButton))
                            .addComponent(emitirComprobanteCheckbox, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dniTitularLabel)
                    .addComponent(dniTitularField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarTitularButton)
                    .addComponent(limpiarButton))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titularEncontradoLabel)
                    .addComponent(nombreTitularField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ultimaLicenciaLabel)
                    .addComponent(ultimaLicenciaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(emitirComprobanteCheckbox)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarButton)
                    .addComponent(emitirCopiaButton))
                .addGap(15, 15, 15))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("EMITIR COPIA DE LICENCIA");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
                .addComponent(jLabel4))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarTitularButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JTextField dniTitularField;
    private javax.swing.JLabel dniTitularLabel;
    private javax.swing.JCheckBox emitirComprobanteCheckbox;
    private javax.swing.JButton emitirCopiaButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton limpiarButton;
    private javax.swing.JTextField nombreTitularField;
    private javax.swing.JLabel titularEncontradoLabel;
    private javax.swing.JLabel ultimaLicenciaLabel;
    private javax.swing.JTextField ultimaLicenciaTextField;
    // End of variables declaration//GEN-END:variables
}
