package com.municipalidad.licencias.appLicencias.modules.emitirlicencia;

import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import java.awt.Toolkit; 
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.time.format.DateTimeFormatter;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;


public class EmitirLicenciaView extends javax.swing.JFrame {
  
    public EmitirLicenciaView() {
        initComponents();
        this.setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/SantaFeCapital_Logo.png")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configurarChecklistClases();
        aplicarEstadoInicial();
    }
    /*
     * Estado inicial: solo numDocField, buscarTitularButton, limpiarButton y cancelarButton
     * están habilitados. Todo lo demás bloqueado.
     */
    private void aplicarEstadoInicial() {
        
        ultimaLicenciaTextField.setBackground(java.awt.Color.WHITE);
        ultimaLicenciaTextField.setForeground(java.awt.Color.BLACK);
        ultimaLicenciaTextField.setDisabledTextColor(java.awt.Color.BLACK);
        // ✅ DNI editable solo en estado inicial
        numDocTextfield.setEditable(true);
        numDocTextfield.setEnabled(true);
        
        // Campos de titular → no editables
        nombreTextField.setEditable(false);
        telefonoTextField.setEditable(false);
        emailTextField.setEditable(false);
        domicilioTextField.setEditable(false);
        ultimaLicenciaTextField.setEditable(false);

        // Checkboxes → desactivados
        esDonanteCheckbox.setEnabled(false);
        mantenerDatosCheckbox.setEnabled(false);
        mantenerDatosCheckbox.setSelected(false);

        // Botón emitir → desactivado hasta que haya clase seleccionada
        emitirButton.setEnabled(false);

        // Checkboxes de clase → desactivados hasta encontrar titular
        claseChecks.values().forEach(cb -> cb.setEnabled(false));

        // Limpiar, Cancelar y BuscarTitular → siempre habilitados
        limpiarButton.setEnabled(true);
        cancelarButton.setEnabled(true);
        buscarTitularButton.setEnabled(true);
    }

    /*
     * Invocado por el controller al encontrar al titular.
     * Habilita edición de campos y checkboxes.
     * Registra el ItemListener de mantenerDatos UNA sola vez.
     */
    public void activarCamposTitular() {
        
        //bloquar dni una vez encontrado el titular
        numDocTextfield.setEditable(false);
        numDocTextfield.setEnabled(false);
        
        mantenerDatosCheckbox.setEnabled(true);
        mantenerDatosCheckbox.setSelected(false);

        // Remover listeners anteriores para evitar acumulación ✅
        for (var listener : mantenerDatosCheckbox.getItemListeners()) {
            mantenerDatosCheckbox.removeItemListener(listener);
        }

        mantenerDatosCheckbox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                deshabilitarEdicionTitular();
            } else {
                habilitarEdicionTitular();
            }
        });

        habilitarEdicionTitular();

        // Habilitar checkboxes de clase y agregar listener para el botón Emitir ✅
        claseChecks.values().forEach(cb -> {
            cb.setEnabled(true);
            // Remover listeners anteriores
            for (var listener : cb.getItemListeners()) {
                cb.removeItemListener(listener);
            }
            cb.addItemListener(e -> actualizarEstadoBotonEmitir());
        });
    }

    /*
     * Habilita edición de campos modificables del titular.
     * nombre y ultimaLicencia nunca son editables.
     */
    private void habilitarEdicionTitular() {
        telefonoTextField.setEditable(true);
        emailTextField.setEditable(true);
        domicilioTextField.setEditable(true);
        esDonanteCheckbox.setEnabled(true);
    }

    /*
     * Deshabilita edición cuando se marca "Mantener datos".
     * El botón Emitir NO se deshabilita, solo los campos. ✅
     */
    private void deshabilitarEdicionTitular() {
        telefonoTextField.setEditable(false);
        emailTextField.setEditable(false);
        domicilioTextField.setEditable(false);
        esDonanteCheckbox.setEnabled(false);
    }

    /*
     * Habilita el botón Emitir solo si hay al menos una clase seleccionada.
     */
    private void actualizarEstadoBotonEmitir() {
        boolean hayClaseSeleccionada = claseChecks.values().stream().anyMatch(JCheckBox::isSelected);
        emitirButton.setEnabled(hayClaseSeleccionada);
    }

    /*
     * Rellena los campos con los datos del titular encontrado.
     */
    public void setInfoTitular(TitularDTO dto) {
        nombreTextField.setText(dto.getNombre() + " " + dto.getApellido()); // ✅ espacio entre nombre y apellido
        telefonoTextField.setText(dto.getTelefono() != null ? dto.getTelefono().toString() : "");
        emailTextField.setText(dto.getEmail() != null ? dto.getEmail() : "");
        domicilioTextField.setText(dto.getDomicilio() != null ? dto.getDomicilio() : "");
        ultimaLicenciaTextField.setText(""); // TODO: completar con última licencia real
        if (dto.getEsDonante())
            esDonanteCheckbox.setSelected(true);
    }

    /*
     * Vuelve al estado inicial completo. ✅
     * Limpia todos los campos y deshabilita lo que corresponde.
     */
    public void limpiar() {
        numDocTextfield.setText("");
        nombreTextField.setText("");
        telefonoTextField.setText("");
        emailTextField.setText("");
        domicilioTextField.setText("");
        ultimaLicenciaTextField.setText("");
        esDonanteCheckbox.setSelected(false);   // ✅ solo deselecciona, no borra el texto
        claseChecks.values().forEach(cb -> cb.setSelected(false));
        aplicarEstadoInicial(); // ✅ reutiliza el estado inicial
    }

    public ActualizarTitularRequestDTO getDatosTitular() {
        String telTxt = telefonoTextField.getText().trim();
        Long telefono = telTxt.isEmpty() ? null : Long.valueOf(telTxt);

        return ActualizarTitularRequestDTO.builder()
                .telefono(telefono)
                .email(emailTextField.getText().trim())
                .domicilio(domicilioTextField.getText().trim())
                .esDonante(esDonanteCheckbox.isSelected())
                .build();
    }

    public Set<ClaseLicencia> getClasesSeleccionadas() {
        Set<ClaseLicencia> out = new HashSet<>();
        claseChecks.forEach((k, v) -> {
            if (v.isSelected()) out.add(k);
        });
        return out;
    }

    public String getDni() {
        return numDocTextfield.getText().trim();
    }

    public boolean isMantenerDatos() {
        return mantenerDatosCheckbox.isSelected();
    }

    public void setEmitirAction(ActionListener listener) {
        emitirButton.addActionListener(listener);
    }

    public void setCancelarAction(ActionListener listener) {
        cancelarButton.addActionListener(listener);
    }

    public void setBuscarTitularAction(ActionListener listener) {
        buscarTitularButton.addActionListener(listener);
    }

    public void setLimpiarAction(ActionListener listener) {
        limpiarButton.addActionListener(listener);
    }
    
 
    
    public void setUltimaLicencia(LicenciaDTO licencia) {//TODO: Crear un DTO específico en lugar de usar LicenciaDTO genérico
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        panelEncabezado = new javax.swing.JPanel();
        menuPrincipalTitle = new javax.swing.JLabel();
        labelLogoSF = new javax.swing.JLabel();
        panelLicenciaEmitir = new javax.swing.JPanel();
        emitirButton = new javax.swing.JButton();
        cancelarButton = new javax.swing.JButton();
        licenciaAEmitirTitle = new javax.swing.JLabel();
        clasesPanel = new javax.swing.JPanel();
        panelDatosTitular = new javax.swing.JPanel();
        buscarTitularButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        panelTitularEncontrado = new javax.swing.JPanel();
        titularEncontradoTitle = new javax.swing.JLabel();
        nombreTextField = new javax.swing.JTextField();
        nombreLabel = new javax.swing.JLabel();
        telefonoLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        telefonoTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        domicilioLabel = new javax.swing.JLabel();
        domicilioTextField = new javax.swing.JTextField();
        esDonanteCheckbox = new javax.swing.JCheckBox();
        ultimaLicenciaLabel = new javax.swing.JLabel();
        ultimaLicenciaTextField = new javax.swing.JTextField();
        mantenerDatosCheckbox = new javax.swing.JCheckBox();
        limpiarButton = new javax.swing.JButton();
        datosActualizadosRedLabel = new javax.swing.JLabel();
        numDocTextfield = new javax.swing.JTextField();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setSize(new java.awt.Dimension(590, 254));

        menuPrincipalTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        menuPrincipalTitle.setText("EMITIR, RENOVAR O AMPLIAR LICENCIA");

        labelLogoSF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPrincipalTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelLogoSF)
                .addContainerGap())
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelEncabezadoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(menuPrincipalTitle))
                    .addComponent(labelLogoSF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        emitirButton.setText("Emitir");

        cancelarButton.setText("Cancelar");

        licenciaAEmitirTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        licenciaAEmitirTitle.setText("Licencia a emitir");

        clasesPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelLicenciaEmitirLayout = new javax.swing.GroupLayout(panelLicenciaEmitir);
        panelLicenciaEmitir.setLayout(panelLicenciaEmitirLayout);
        panelLicenciaEmitirLayout.setHorizontalGroup(
            panelLicenciaEmitirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLicenciaEmitirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLicenciaEmitirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLicenciaEmitirLayout.createSequentialGroup()
                        .addComponent(licenciaAEmitirTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(emitirButton))
                    .addComponent(clasesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelLicenciaEmitirLayout.setVerticalGroup(
            panelLicenciaEmitirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLicenciaEmitirLayout.createSequentialGroup()
                .addComponent(licenciaAEmitirTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clasesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(panelLicenciaEmitirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emitirButton)
                    .addComponent(cancelarButton))
                .addContainerGap())
        );

        buscarTitularButton.setText("Buscar titular");

        jLabel2.setText("DNI Titular:");

        titularEncontradoTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        titularEncontradoTitle.setText("Titular encontrado");

        nombreTextField.setEditable(false);

        nombreLabel.setText("Nombre:");

        telefonoLabel.setText("Teléfono:");

        emailLabel.setText("Email:");

        domicilioLabel.setText("Domicilio:");

        esDonanteCheckbox.setText("Es donante");

        ultimaLicenciaLabel.setText("Ultima licencia:");

        ultimaLicenciaTextField.setEditable(false);
        ultimaLicenciaTextField.setEnabled(false);

        mantenerDatosCheckbox.setText("Mantener los datos del titular actuales (sin cambios).");

        javax.swing.GroupLayout panelTitularEncontradoLayout = new javax.swing.GroupLayout(panelTitularEncontrado);
        panelTitularEncontrado.setLayout(panelTitularEncontradoLayout);
        panelTitularEncontradoLayout.setHorizontalGroup(
            panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitularEncontradoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTitularEncontradoLayout.createSequentialGroup()
                        .addGroup(panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTitularEncontradoLayout.createSequentialGroup()
                                .addGroup(panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(domicilioLabel)
                                    .addComponent(telefonoLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(domicilioTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                                    .addComponent(telefonoTextField))
                                .addGap(18, 18, 18)
                                .addGroup(panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelTitularEncontradoLayout.createSequentialGroup()
                                        .addComponent(emailLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(emailTextField))
                                    .addGroup(panelTitularEncontradoLayout.createSequentialGroup()
                                        .addComponent(esDonanteCheckbox)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(panelTitularEncontradoLayout.createSequentialGroup()
                                .addComponent(nombreLabel)
                                .addGap(12, 12, 12)
                                .addComponent(nombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(6, 6, 6))
                    .addGroup(panelTitularEncontradoLayout.createSequentialGroup()
                        .addComponent(ultimaLicenciaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ultimaLicenciaTextField)
                        .addContainerGap())))
            .addGroup(panelTitularEncontradoLayout.createSequentialGroup()
                .addGroup(panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titularEncontradoTitle)
                    .addGroup(panelTitularEncontradoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mantenerDatosCheckbox)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelTitularEncontradoLayout.setVerticalGroup(
            panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitularEncontradoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titularEncontradoTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefonoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel)
                    .addComponent(telefonoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(domicilioLabel)
                    .addComponent(domicilioTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(esDonanteCheckbox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelTitularEncontradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ultimaLicenciaLabel)
                    .addComponent(ultimaLicenciaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(mantenerDatosCheckbox)
                .addContainerGap())
        );

        limpiarButton.setText("Limpiar");

        datosActualizadosRedLabel.setForeground(new java.awt.Color(204, 0, 0));
        datosActualizadosRedLabel.setText("Corrobore que los datos estén actualizados.");

        javax.swing.GroupLayout panelDatosTitularLayout = new javax.swing.GroupLayout(panelDatosTitular);
        panelDatosTitular.setLayout(panelDatosTitularLayout);
        panelDatosTitularLayout.setHorizontalGroup(
            panelDatosTitularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosTitularLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDatosTitularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTitularEncontrado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosTitularLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(limpiarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarTitularButton))
                    .addGroup(panelDatosTitularLayout.createSequentialGroup()
                        .addComponent(datosActualizadosRedLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelDatosTitularLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(numDocTextfield)))
                .addContainerGap())
        );
        panelDatosTitularLayout.setVerticalGroup(
            panelDatosTitularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosTitularLayout.createSequentialGroup()
                .addGroup(panelDatosTitularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(numDocTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosTitularLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarTitularButton)
                    .addComponent(limpiarButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTitularEncontrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datosActualizadosRedLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDatosTitular, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelLicenciaEmitir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelDatosTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelLicenciaEmitir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void configurarChecklistClases() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (ClaseLicencia c : ClaseLicencia.values()) {
            JCheckBox cb = new JCheckBox(formatearTextoClase(c));
            cb.setEnabled(false);
            claseChecks.put(c, cb);
            panel.add(cb);
        }

        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBorder(null);

        clasesPanel.setLayout(new java.awt.BorderLayout());
        clasesPanel.add(scroll, java.awt.BorderLayout.CENTER);
        clasesPanel.revalidate();
        clasesPanel.repaint();
    }

    private String formatearTextoClase(ClaseLicencia c) {
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
    private javax.swing.JButton buscarTitularButton;
    private javax.swing.JButton cancelarButton;
    private javax.swing.JPanel clasesPanel;
    private javax.swing.JLabel datosActualizadosRedLabel;
    private javax.swing.JLabel domicilioLabel;
    private javax.swing.JTextField domicilioTextField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JButton emitirButton;
    private javax.swing.JCheckBox esDonanteCheckbox;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel labelLogoSF;
    private javax.swing.JLabel licenciaAEmitirTitle;
    private javax.swing.JButton limpiarButton;
    private javax.swing.JCheckBox mantenerDatosCheckbox;
    private javax.swing.JLabel menuPrincipalTitle;
    private javax.swing.JLabel nombreLabel;
    private javax.swing.JTextField nombreTextField;
    private javax.swing.JTextField numDocTextfield;
    private javax.swing.JPanel panelDatosTitular;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JPanel panelLicenciaEmitir;
    private javax.swing.JPanel panelTitularEncontrado;
    private javax.swing.JLabel telefonoLabel;
    private javax.swing.JTextField telefonoTextField;
    private javax.swing.JLabel titularEncontradoTitle;
    private javax.swing.JLabel ultimaLicenciaLabel;
    private javax.swing.JTextField ultimaLicenciaTextField;
    // End of variables declaration//GEN-END:variables
}
