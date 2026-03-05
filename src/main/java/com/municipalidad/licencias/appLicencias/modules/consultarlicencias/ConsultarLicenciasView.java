package com.municipalidad.licencias.appLicencias.modules.consultarlicencias;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class ConsultarLicenciasView extends javax.swing.JFrame {

    

    public ConsultarLicenciasView() {
        initComponents();
        postInitUi();
    }

    // =========================
    // Inicialización NO generada
    // =========================
    private void postInitUi() {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(
                getClass().getResource("/img/SantaFeCapital_Logo.png")
            ));
        } catch (Exception ignored) {
            // si no está el recurso, no rompemos la pantalla
        }

       

        

        // UX: si Estado != "Vencen pronto", deshabilitar "Vencen en"
        // (controller puede también manejarlo, pero esto ayuda)
        vencenEnComboBox.setEnabled(false);
        estadoComboBox.addActionListener(e -> actualizarEnabledVencenEn());

        // Tabla: modelo vacío al iniciar (sin filas dummy)
        limpiarTabla();

        // Defaults razonables (opcional)
        // setEstados(new String[]{"Vigentes", "Expiradas", "Vencen pronto", "Todas"});
        // setVencenEnOpciones(new String[]{"7 días", "15 días", "30 días", "60 días"});
    }

    private void actualizarEnabledVencenEn() {
        String estado = getEstadoSeleccionado();
        boolean esVencenPronto = estado != null && estado.toLowerCase().contains("vencen");
        vencenEnComboBox.setEnabled(esVencenPronto);
        if (!esVencenPronto) {
            vencenEnComboBox.setSelectedIndex(-1);
        }
    }

    // =========================
    // API para el Controller
    // =========================

    // Listeners (controller-friendly)
    public void onAplicarFiltros(ActionListener l) { aplicarFiltrosButton.addActionListener(l); }
    public void onLimpiar(ActionListener l) { limpiarButton.addActionListener(l); }
    public void onCerrar(ActionListener l) { cerrarButton.addActionListener(l); }
    public void onEmitirReporte(ActionListener l) { emitirReporteButton.addActionListener(l); }

    // Cargar combos
    public void setEstados(String[] estados) {
        estadoComboBox.setModel(new DefaultComboBoxModel<>(estados));
        estadoComboBox.setSelectedIndex(0);
        actualizarEnabledVencenEn();
    }

    public void setVencenEnOpciones(String[] opciones) {
        vencenEnComboBox.setModel(new DefaultComboBoxModel<>(opciones));
        vencenEnComboBox.setSelectedIndex(-1);
    }

    public void setClases(String[] clases) {
        clasesComboBox.setModel(new DefaultComboBoxModel<>(clases));
        clasesComboBox.setSelectedIndex(-1);
    }

    // Getters de filtros (el controller arma el DTO de filtros)
    public String getDniTexto() {
        return dniTextField.getText() != null ? dniTextField.getText().trim() : "";
    }

    public String getEstadoSeleccionado() {
        Object o = estadoComboBox.getSelectedItem();
        return o != null ? o.toString().trim() : null;
    }

    public String getVencenEnSeleccionado() {
        Object o = vencenEnComboBox.getSelectedItem();
        return o != null ? o.toString().trim() : null;
    }

    public String getClaseSeleccionada() {
        Object o = clasesComboBox.getSelectedItem();
        return o != null ? o.toString().trim() : null;
    }

    public LocalDate getEmisionDesde() { return toLocalDate(emisionDesdeDateChooser.getDate()); }
    public LocalDate getEmisionHasta() { return toLocalDate(emisionHastaDateChooser.getDate()); }
    public LocalDate getVencimientoDesde() { return toLocalDate(vencimientoDesdeDateChooser.getDate()); }
    public LocalDate getVencimientoHasta() { return toLocalDate(vencimientoHastaDateChooser.getDate()); }

    // Limpiar UI
    public void limpiarFiltros() {
        dniTextField.setText("");
        if (estadoComboBox.getItemCount() > 0) estadoComboBox.setSelectedIndex(0);
        vencenEnComboBox.setSelectedIndex(-1);

        emisionDesdeDateChooser.setDate(null);
        emisionHastaDateChooser.setDate(null);
        vencimientoDesdeDateChooser.setDate(null);
        vencimientoHastaDateChooser.setDate(null);

        clasesComboBox.setSelectedIndex(-1);

       
        actualizarEnabledVencenEn();
    }

    // Tabla (controller setea resultados)
    public void limpiarTabla() {
        DefaultTableModel model = (DefaultTableModel) datosTable.getModel();
        model.setRowCount(0);
    }

    public void agregarFilaTabla(String titular, Long dni, String clase, LocalDate fechaExpiracion) {
        DefaultTableModel model = (DefaultTableModel) datosTable.getModel();
        model.addRow(new Object[] { titular, dni, clase, fechaExpiracion });
    }

    public void setBotonReporteEnabled(boolean enabled) {
        emitirReporteButton.setEnabled(enabled);
    }

    // =========================
    // Helpers
    // =========================
    private LocalDate toLocalDate(Date d) {
        if (d == null) return null;
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        encabezadoPanel = new javax.swing.JPanel();
        logoLabel = new javax.swing.JLabel();
        tituloLabel = new javax.swing.JLabel();
        cuerpoCompletoPanel = new javax.swing.JPanel();
        datosScrollPane = new javax.swing.JScrollPane();
        datosTable = new javax.swing.JTable();
        cerrarButton = new javax.swing.JButton();
        emitirReporteButton = new javax.swing.JButton();
        filtrosCompletosPanel = new javax.swing.JPanel();
        limpiarButton = new javax.swing.JButton();
        aplicarFiltrosButton = new javax.swing.JButton();
        dniLabel = new javax.swing.JLabel();
        dniTextField = new javax.swing.JTextField();
        informativoLabel = new javax.swing.JLabel();
        estadoLabel = new javax.swing.JLabel();
        estadoComboBox = new javax.swing.JComboBox<>();
        vencenEnComboBox = new javax.swing.JComboBox<>();
        vencenEnLabel = new javax.swing.JLabel();
        filtrosAvanzadosPanel = new javax.swing.JPanel();
        emisionDesdeLabel = new javax.swing.JLabel();
        emisionDesdeDateChooser = new com.toedter.calendar.JDateChooser();
        emisionHastaLabel = new javax.swing.JLabel();
        emisionHastaDateChooser = new com.toedter.calendar.JDateChooser();
        vencimientoDesdeLabel = new javax.swing.JLabel();
        vencimientoHastaLabel = new javax.swing.JLabel();
        vencimientoDesdeDateChooser = new com.toedter.calendar.JDateChooser();
        vencimientoHastaDateChooser = new com.toedter.calendar.JDateChooser();
        clasesComboBox = new javax.swing.JComboBox<>();
        clasesLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png"))); // NOI18N

        tituloLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        tituloLabel.setText("CONSULTAR LICENCIAS");

        javax.swing.GroupLayout encabezadoPanelLayout = new javax.swing.GroupLayout(encabezadoPanel);
        encabezadoPanel.setLayout(encabezadoPanelLayout);
        encabezadoPanelLayout.setHorizontalGroup(
            encabezadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, encabezadoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                .addComponent(logoLabel)
                .addContainerGap())
        );
        encabezadoPanelLayout.setVerticalGroup(
            encabezadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(encabezadoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(encabezadoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tituloLabel)
                    .addComponent(logoLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        datosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Titular", "DNI", "Clase", "Fecha Expiración"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Long.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        datosScrollPane.setViewportView(datosTable);

        cerrarButton.setText("Cerrar");

        emitirReporteButton.setBackground(new java.awt.Color(204, 255, 204));
        emitirReporteButton.setText("Emitir Reporte");

        limpiarButton.setText("Limpiar");

        aplicarFiltrosButton.setBackground(new java.awt.Color(204, 255, 204));
        aplicarFiltrosButton.setText("Aplicar Filtros");

        dniLabel.setText("DNI:");

        informativoLabel.setText("Usted puede aplicar una búsqueda de licencias por filtros.");

        estadoLabel.setText("Estado:");

        estadoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        vencenEnComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        vencenEnLabel.setText("Vencen en: ");

        emisionDesdeLabel.setText("Emisión desde:");

        emisionHastaLabel.setText("Hasta:");

        vencimientoDesdeLabel.setText("Vencimiento desde:");

        vencimientoHastaLabel.setText("Hasta:");

        javax.swing.GroupLayout filtrosAvanzadosPanelLayout = new javax.swing.GroupLayout(filtrosAvanzadosPanel);
        filtrosAvanzadosPanel.setLayout(filtrosAvanzadosPanelLayout);
        filtrosAvanzadosPanelLayout.setHorizontalGroup(
            filtrosAvanzadosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filtrosAvanzadosPanelLayout.createSequentialGroup()
                .addGroup(filtrosAvanzadosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(emisionHastaLabel)
                    .addComponent(emisionDesdeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(filtrosAvanzadosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(emisionDesdeDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                    .addComponent(emisionHastaDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(70, 70, 70)
                .addGroup(filtrosAvanzadosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(vencimientoDesdeLabel)
                    .addComponent(vencimientoHastaLabel))
                .addGap(12, 12, 12)
                .addGroup(filtrosAvanzadosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vencimientoDesdeDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addComponent(vencimientoHastaDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        filtrosAvanzadosPanelLayout.setVerticalGroup(
            filtrosAvanzadosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filtrosAvanzadosPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(filtrosAvanzadosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(emisionDesdeDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emisionDesdeLabel)
                    .addComponent(vencimientoDesdeLabel)
                    .addComponent(vencimientoDesdeDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(filtrosAvanzadosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emisionHastaLabel)
                    .addComponent(emisionHastaDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vencimientoHastaLabel)
                    .addComponent(vencimientoHastaDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        clasesComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        clasesLabel.setText("Clases:");

        javax.swing.GroupLayout filtrosCompletosPanelLayout = new javax.swing.GroupLayout(filtrosCompletosPanel);
        filtrosCompletosPanel.setLayout(filtrosCompletosPanelLayout);
        filtrosCompletosPanelLayout.setHorizontalGroup(
            filtrosCompletosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filtrosCompletosPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(filtrosCompletosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(filtrosCompletosPanelLayout.createSequentialGroup()
                        .addComponent(informativoLabel)
                        .addGap(113, 113, 113))
                    .addGroup(filtrosCompletosPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(filtrosCompletosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(vencenEnLabel)
                            .addComponent(estadoLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(filtrosCompletosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(filtrosCompletosPanelLayout.createSequentialGroup()
                                .addComponent(estadoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(dniLabel))
                            .addGroup(filtrosCompletosPanelLayout.createSequentialGroup()
                                .addComponent(vencenEnComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clasesLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(filtrosCompletosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(clasesComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dniTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, filtrosCompletosPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(filtrosCompletosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filtrosAvanzadosPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, filtrosCompletosPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(limpiarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(aplicarFiltrosButton)))
                .addContainerGap())
        );
        filtrosCompletosPanelLayout.setVerticalGroup(
            filtrosCompletosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filtrosCompletosPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(informativoLabel)
                .addGap(26, 26, 26)
                .addGroup(filtrosCompletosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dniLabel)
                    .addComponent(dniTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estadoLabel)
                    .addComponent(estadoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(filtrosCompletosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vencenEnComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vencenEnLabel)
                    .addComponent(clasesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clasesLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filtrosAvanzadosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(filtrosCompletosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(limpiarButton)
                    .addComponent(aplicarFiltrosButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout cuerpoCompletoPanelLayout = new javax.swing.GroupLayout(cuerpoCompletoPanel);
        cuerpoCompletoPanel.setLayout(cuerpoCompletoPanelLayout);
        cuerpoCompletoPanelLayout.setHorizontalGroup(
            cuerpoCompletoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cuerpoCompletoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cuerpoCompletoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filtrosCompletosPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(datosScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cuerpoCompletoPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(emitirReporteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cerrarButton)
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );
        cuerpoCompletoPanelLayout.setVerticalGroup(
            cuerpoCompletoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cuerpoCompletoPanelLayout.createSequentialGroup()
                .addComponent(filtrosCompletosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(datosScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(cuerpoCompletoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cerrarButton)
                    .addComponent(emitirReporteButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(encabezadoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(cuerpoCompletoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(encabezadoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cuerpoCompletoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aplicarFiltrosButton;
    private javax.swing.JButton cerrarButton;
    private javax.swing.JComboBox<String> clasesComboBox;
    private javax.swing.JLabel clasesLabel;
    private javax.swing.JPanel cuerpoCompletoPanel;
    private javax.swing.JScrollPane datosScrollPane;
    private javax.swing.JTable datosTable;
    private javax.swing.JLabel dniLabel;
    private javax.swing.JTextField dniTextField;
    private com.toedter.calendar.JDateChooser emisionDesdeDateChooser;
    private javax.swing.JLabel emisionDesdeLabel;
    private com.toedter.calendar.JDateChooser emisionHastaDateChooser;
    private javax.swing.JLabel emisionHastaLabel;
    private javax.swing.JButton emitirReporteButton;
    private javax.swing.JPanel encabezadoPanel;
    private javax.swing.JComboBox<String> estadoComboBox;
    private javax.swing.JLabel estadoLabel;
    private javax.swing.JPanel filtrosAvanzadosPanel;
    private javax.swing.JPanel filtrosCompletosPanel;
    private javax.swing.JLabel informativoLabel;
    private javax.swing.JButton limpiarButton;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JLabel tituloLabel;
    private javax.swing.JComboBox<String> vencenEnComboBox;
    private javax.swing.JLabel vencenEnLabel;
    private com.toedter.calendar.JDateChooser vencimientoDesdeDateChooser;
    private javax.swing.JLabel vencimientoDesdeLabel;
    private com.toedter.calendar.JDateChooser vencimientoHastaDateChooser;
    private javax.swing.JLabel vencimientoHastaLabel;
    // End of variables declaration//GEN-END:variables
}
