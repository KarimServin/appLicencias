package com.municipalidad.licencias.appLicencias.modules.gestionarusuarios;

import com.municipalidad.licencias.appLicencias.dto.UsuarioDTO;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class GestionarUsuariosView extends javax.swing.JFrame {

    public GestionarUsuariosView() {
        initComponents();
        postInitUi();
    }

    private void postInitUi() {
        setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(
                getClass().getResource("/img/SantaFeCapital_Logo.png")
            ));
        } catch (Exception ignored) {}

        // Modelo no editable
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Usuario", "Apellido", "Rol", "Estado"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        usuariosTable.setModel(model);
        usuariosTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Ocultar columna ID
        usuariosTable.getColumnModel().getColumn(0).setMinWidth(0);
        usuariosTable.getColumnModel().getColumn(0).setMaxWidth(0);
        usuariosTable.getColumnModel().getColumn(0).setPreferredWidth(0);

        // Listener de selección
        usuariosTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                actualizarBotones();
            }
        });

        editarButton.setEnabled(false);
        estadoButton.setEnabled(false);
        privilegiosButton.setEnabled(false);
        estadoButton.setText("Estado");
        privilegiosButton.setText("Cambiar Privilegios");
    }

    private void actualizarBotones() {
        int fila = usuariosTable.getSelectedRow();

        if (fila == -1) {
            editarButton.setEnabled(false);
            estadoButton.setEnabled(false);
            privilegiosButton.setEnabled(false);
            estadoButton.setText("Estado");
            privilegiosButton.setText("Cambiar Privilegios");
            return;
        }

        String estado = (String) usuariosTable.getModel().getValueAt(fila, 4);
        String rol = (String) usuariosTable.getModel().getValueAt(fila, 3);
        boolean esActivo = "Activo".equals(estado);
        boolean esAdmin = "ADMIN".equals(rol);

        editarButton.setEnabled(true);
        estadoButton.setEnabled(true);
        privilegiosButton.setEnabled(true);
        estadoButton.setText(esActivo ? "Desactivar" : "Activar");
        privilegiosButton.setText(esAdmin ? "Quitar Privilegios" : "Conceder Privilegios");
    }

    // ── Listeners ──

    public void setEditarAction(ActionListener l) { editarButton.addActionListener(l); }
    public void setEstadoAction(ActionListener l) { estadoButton.addActionListener(l); }
    public void setPrivilegiosAction(ActionListener l) { privilegiosButton.addActionListener(l); }
    public void setCerrarAction(ActionListener l) { cerrarButton.addActionListener(l); }

    // ── Getters de selección ──

    public Long getUsuarioIdSeleccionado() {
        int fila = usuariosTable.getSelectedRow();
        if (fila == -1) return null;
        return (Long) usuariosTable.getModel().getValueAt(fila, 0);
    }

    public String getNombreUsuarioSeleccionado() {
        int fila = usuariosTable.getSelectedRow();
        if (fila == -1) return null;
        return (String) usuariosTable.getModel().getValueAt(fila, 1);
    }

    public String getEstadoSeleccionado() {
        int fila = usuariosTable.getSelectedRow();
        if (fila == -1) return null;
        return (String) usuariosTable.getModel().getValueAt(fila, 4);
    }

    public String getRolSeleccionado() {
        int fila = usuariosTable.getSelectedRow();
        if (fila == -1) return null;
        return (String) usuariosTable.getModel().getValueAt(fila, 3);
    }

    // ── Habilitar/deshabilitar botones ──

    public void setBotonesEnabled(boolean enabled) {
        editarButton.setEnabled(enabled);
        estadoButton.setEnabled(enabled);
        privilegiosButton.setEnabled(enabled);
    }

    // ── Cargar datos ──

    public void cargarUsuarios(List<UsuarioDTO> usuarios) {
        DefaultTableModel model = (DefaultTableModel) usuariosTable.getModel();
        model.setRowCount(0);
        for (UsuarioDTO u : usuarios) {
            model.addRow(new Object[]{
                u.getId(),
                u.getNombreUsuario(),
                u.getApellido(),
                u.esAdmin() ? "ADMIN" : "OPERADOR",
                u.isActivo() ? "Activo" : "Inactivo"
            });
        }
        editarButton.setEnabled(false);
        estadoButton.setEnabled(false);
        privilegiosButton.setEnabled(false);
        estadoButton.setText("Estado");
        privilegiosButton.setText("Cambiar Privilegios");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        panelEncabezado = new javax.swing.JPanel();
        logoLabel = new javax.swing.JLabel();
        tituloLabel = new javax.swing.JLabel();
        instruccionesLabel = new javax.swing.JLabel();
        tablaScrollPane = new javax.swing.JScrollPane();
        usuariosTable = new javax.swing.JTable();
        privilegiosButton = new javax.swing.JButton();
        estadoButton = new javax.swing.JButton();
        editarButton = new javax.swing.JButton();
        cerrarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Santa_Fe_Capital (1).png")));

        tituloLabel.setFont(new java.awt.Font("Segoe UI", 1, 18));
        tituloLabel.setText("GESTIONAR USUARIOS EXISTENTES");

        javax.swing.GroupLayout panelEncabezadoLayout = new javax.swing.GroupLayout(panelEncabezado);
        panelEncabezado.setLayout(panelEncabezadoLayout);
        panelEncabezadoLayout.setHorizontalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(logoLabel)
                .addContainerGap())
        );
        panelEncabezadoLayout.setVerticalGroup(
            panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tituloLabel)
                    .addComponent(logoLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        instruccionesLabel.setText("Seleccione el usuario que desea modificar:");

        usuariosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Usuario", "Apellido", "Rol", "Estado"}
        ) {
            public boolean isCellEditable(int rowIndex, int columnIndex) { return false; }
        });
        tablaScrollPane.setViewportView(usuariosTable);

        privilegiosButton.setText("Cambiar Privilegios");
        privilegiosButton.setEnabled(false);

        estadoButton.setText("Estado");
        estadoButton.setEnabled(false);

        editarButton.setText("Editar");
        editarButton.setEnabled(false);

        cerrarButton.setText("Cerrar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tablaScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(instruccionesLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(privilegiosButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estadoButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cerrarButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(instruccionesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tablaScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cerrarButton)
                    .addComponent(editarButton)
                    .addComponent(estadoButton)
                    .addComponent(privilegiosButton))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    // Variables declaration - do not modify
    private javax.swing.JButton cerrarButton;
    private javax.swing.JButton editarButton;
    private javax.swing.JButton estadoButton;
    private javax.swing.JLabel instruccionesLabel;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JPanel panelEncabezado;
    private javax.swing.JButton privilegiosButton;
    private javax.swing.JScrollPane tablaScrollPane;
    private javax.swing.JLabel tituloLabel;
    private javax.swing.JTable usuariosTable;
    // End of variables declaration
}