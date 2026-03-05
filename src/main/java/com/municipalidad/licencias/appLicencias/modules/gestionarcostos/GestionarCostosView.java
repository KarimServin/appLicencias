package com.municipalidad.licencias.appLicencias.modules.gestionarcostos;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class GestionarCostosView extends javax.swing.JFrame {

    private static final String[] COLUMNAS = {"Clase", "5 años", "4 años", "3 años", "1 año"};
    private static final String[] CLASES = {"A", "B", "C", "D", "E", "F", "G"};

    private DefaultTableModel matrizModel;

    public GestionarCostosView() {
        initComponents();
        configurarTabla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCostos = new javax.swing.JTable();
        lblCopia = new javax.swing.JLabel();
        lblSignoPeso = new javax.swing.JLabel();
        txtCostoCopia = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lblPorcentaje = new javax.swing.JLabel();
        txtPorcentaje = new javax.swing.JTextField();
        lblSignoPorcentaje = new javax.swing.JLabel();
        btnAplicarPorcentaje = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestionar Costos de Licencias");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 102, 102));
        lblTitulo.setText("Configuración de Costos de Licencias");

        tblCostos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblCostos.setRowHeight(32);
        tblCostos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblCostos);

        lblCopia.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCopia.setText("Costo de Copia:");

        lblSignoPeso.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSignoPeso.setText("$");

        txtCostoCopia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCostoCopia.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        lblPorcentaje.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPorcentaje.setText("Ajustar todos los costos en:");

        txtPorcentaje.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPorcentaje.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPorcentaje.setToolTipText("Ej: 10 para +10%, -5 para -5%");

        lblSignoPorcentaje.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSignoPorcentaje.setText("%");

        btnAplicarPorcentaje.setText("Aplicar");

        lblStatus.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        lblStatus.setForeground(new java.awt.Color(100, 100, 100));
        lblStatus.setText(" ");

        btnGuardar.setBackground(new java.awt.Color(0, 102, 102));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setText("Guardar Cambios");

        btnCerrar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCerrar.setText("Cerrar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblCopia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSignoPeso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCostoCopia, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblPorcentaje)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSignoPorcentaje)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAplicarPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblTitulo)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCopia)
                    .addComponent(lblSignoPeso)
                    .addComponent(txtCostoCopia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPorcentaje)
                    .addComponent(txtPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSignoPorcentaje)
                    .addComponent(btnAplicarPorcentaje))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStatus)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // ══════════════════════════════════════════════════════════════
    //  CONFIGURACIÓN DE TABLA
    // ══════════════════════════════════════════════════════════════

    private void configurarTabla() {
        matrizModel = new DefaultTableModel(COLUMNAS, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column > 0;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? String.class : Integer.class;
            }
        };

        tblCostos.setModel(matrizModel);
        tblCostos.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));

        // Centrar todas las columnas
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        for (int i = 0; i < COLUMNAS.length; i++) {
            tblCostos.getColumnModel().getColumn(i).setCellRenderer(centrado);
        }

        // Renderer especial para columna Clase
        DefaultTableCellRenderer claseRenderer = new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table,
                    Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component c = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
                if (!isSelected) {
                    setBackground(new java.awt.Color(230, 230, 230));
                    setForeground(new java.awt.Color(0, 80, 80));
                }
                return c;
            }
        };
        tblCostos.getColumnModel().getColumn(0).setCellRenderer(claseRenderer);
        tblCostos.getColumnModel().getColumn(0).setPreferredWidth(80);
    }

    // ══════════════════════════════════════════════════════════════
    //  MÉTODOS PÚBLICOS — MATRIZ
    // ══════════════════════════════════════════════════════════════

    public void limpiarMatriz() {
        matrizModel.setRowCount(0);
    }

    public void agregarFilaMatriz(String clase, Integer costo5, Integer costo4,
                                   Integer costo3, Integer costo1) {
        matrizModel.addRow(new Object[]{clase, costo5, costo4, costo3, costo1});
    }

    public String[] getClases() {
        return CLASES;
    }

    public int getFilaCount() {
        return matrizModel.getRowCount();
    }

    public String getClaseEnFila(int fila) {
        return matrizModel.getValueAt(fila, 0).toString();
    }

    public Integer getCostoEnCelda(int fila, int columna) {
        Object val = matrizModel.getValueAt(fila, columna);
        if (val == null) return null;
        if (val instanceof Integer i) return i;
        String s = val.toString().trim();
        if (s.isBlank()) return null;
        return Integer.parseInt(s);
    }

    public void setCostoEnCelda(int fila, int columna, Integer valor) {
        matrizModel.setValueAt(valor, fila, columna);
    }

    // ══════════════════════════════════════════════════════════════
    //  MÉTODOS PÚBLICOS — COPIA
    // ══════════════════════════════════════════════════════════════

    public void setCostoCopia(Integer costo) {
        txtCostoCopia.setText(costo != null ? costo.toString() : "");
    }

    public Integer getCostoCopia() {
        String s = txtCostoCopia.getText().trim();
        if (s.isBlank()) return null;
        return Integer.parseInt(s);
    }

    // ══════════════════════════════════════════════════════════════
    //  MÉTODOS PÚBLICOS — PORCENTAJE
    // ══════════════════════════════════════════════════════════════

    public String getPorcentajeTexto() {
        return txtPorcentaje.getText().trim();
    }

    public void limpiarPorcentaje() {
        txtPorcentaje.setText("");
    }

    // ══════════════════════════════════════════════════════════════
    //  MÉTODOS PÚBLICOS — GENERALES
    // ══════════════════════════════════════════════════════════════

    public void setStatus(String texto) {
        lblStatus.setText(texto);
    }

    public void setGuardarEnabled(boolean enabled) {
        btnGuardar.setEnabled(enabled);
    }

    public void detenerEdicion() {
        if (tblCostos.isEditing()) {
            tblCostos.getCellEditor().stopCellEditing();
        }
    }

    // ══════════════════════════════════════════════════════════════
    //  LISTENERS
    // ══════════════════════════════════════════════════════════════

    public void onGuardar(ActionListener l) {
        btnGuardar.addActionListener(l);
    }

    public void onCerrar(ActionListener l) {
        btnCerrar.addActionListener(l);
    }

    public void onAplicarPorcentaje(ActionListener l) {
        btnAplicarPorcentaje.addActionListener(l);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAplicarPorcentaje;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCopia;
    private javax.swing.JLabel lblPorcentaje;
    private javax.swing.JLabel lblSignoPeso;
    private javax.swing.JLabel lblSignoPorcentaje;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblCostos;
    private javax.swing.JTextField txtCostoCopia;
    private javax.swing.JTextField txtPorcentaje;
    // End of variables declaration//GEN-END:variables
}