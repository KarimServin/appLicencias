package com.municipalidad.licencias.appLicencias.modules.consultaroperaciones;

import com.municipalidad.licencias.appLicencias.entities.RegistroOperacion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

public class ConsultarOperacionesView extends JFrame {

    private static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final String SPINNER_FORMAT = "dd/MM/yyyy";

    private JComboBox<String> cmbTipoOperacion;
    private JComboBox<String> cmbUsuario;
    private JCheckBox chkFechaDesde;
    private JSpinner spFechaDesde;
    private JCheckBox chkFechaHasta;
    private JSpinner spFechaHasta;

    private JButton btnBuscar;
    private JButton btnLimpiar;
    private JButton btnCerrar;

    private JTable tabla;
    private DefaultTableModel tableModel;
    private JLabel lblEstado;

    public ConsultarOperacionesView() {
        super("Consultar Registro de Operaciones");
        initComponents();
        setIconFromResources();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        add(buildPanelFiltros(), BorderLayout.NORTH);
        add(buildPanelTabla(), BorderLayout.CENTER);
        add(buildPanelSur(), BorderLayout.SOUTH);
    }

    private JPanel buildPanelFiltros() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Filtros"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tipo de operación
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Tipo de operación:"), gbc);
        cmbTipoOperacion = new JComboBox<>();
        cmbTipoOperacion.setPreferredSize(new Dimension(200, 26));
        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(cmbTipoOperacion, gbc);

        // Usuario
        gbc.gridx = 2; gbc.gridy = 0;
        panel.add(new JLabel("Usuario:"), gbc);
        cmbUsuario = new JComboBox<>();
        cmbUsuario.setPreferredSize(new Dimension(200, 26));
        gbc.gridx = 3; gbc.gridy = 0;
        panel.add(cmbUsuario, gbc);

        // Fecha desde
        chkFechaDesde = new JCheckBox("Desde:");
        chkFechaDesde.addActionListener(e -> spFechaDesde.setEnabled(chkFechaDesde.isSelected()));
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(chkFechaDesde, gbc);

        spFechaDesde = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorDesde = new JSpinner.DateEditor(spFechaDesde, SPINNER_FORMAT);
        spFechaDesde.setEditor(editorDesde);
        spFechaDesde.setEnabled(false);
        spFechaDesde.setPreferredSize(new Dimension(130, 26));
        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(spFechaDesde, gbc);

        // Fecha hasta
        chkFechaHasta = new JCheckBox("Hasta:");
        chkFechaHasta.addActionListener(e -> spFechaHasta.setEnabled(chkFechaHasta.isSelected()));
        gbc.gridx = 2; gbc.gridy = 1;
        panel.add(chkFechaHasta, gbc);

        spFechaHasta = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor editorHasta = new JSpinner.DateEditor(spFechaHasta, SPINNER_FORMAT);
        spFechaHasta.setEditor(editorHasta);
        spFechaHasta.setEnabled(false);
        spFechaHasta.setPreferredSize(new Dimension(130, 26));
        gbc.gridx = 3; gbc.gridy = 1;
        panel.add(spFechaHasta, gbc);

        // Botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        Color colorVerde = new Color(153, 255, 204);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(colorVerde);
        btnBuscar.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 14));

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));

        btnCerrar = new JButton("Cerrar");
        btnCerrar.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));

        panelBotones.add(btnBuscar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCerrar);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 4;
        panel.add(panelBotones, gbc);

        return panel;
    }

    private JScrollPane buildPanelTabla() {
        String[] columnas = {"Fecha y hora", "Usuario", "Tipo de operación", "Detalle"};
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(tableModel);
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabla.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));
        tabla.setRowHeight(22);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(130);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(160);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(400);

        return new JScrollPane(tabla);
    }

    private JPanel buildPanelSur() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblEstado = new JLabel(" ");
        panel.add(lblEstado);
        return panel;
    }

    private void setIconFromResources() {
        try {
            java.net.URL iconUrl = getClass().getResource("/img/SantaFeCapital_Logo.png");
            if (iconUrl != null) {
                ImageIcon icon = new ImageIcon(iconUrl);
                Image img = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
                setIconImage(img);
            }
        } catch (Exception e) {
            // Icon not critical - silently ignore
        }
    }

    // ── API pública ──

    public void onBuscar(ActionListener listener) {
        btnBuscar.addActionListener(listener);
    }

    public void onLimpiar(ActionListener listener) {
        btnLimpiar.addActionListener(listener);
    }

    public void onCerrar(ActionListener listener) {
        btnCerrar.addActionListener(listener);
    }

    public void setTiposOperacion(String[] tipos) {
        cmbTipoOperacion.removeAllItems();
        cmbTipoOperacion.addItem("Todos");
        for (String t : tipos) {
            cmbTipoOperacion.addItem(t);
        }
    }

    public void setUsuarios(String[] usuarios) {
        cmbUsuario.removeAllItems();
        cmbUsuario.addItem("Todos");
        for (String u : usuarios) {
            cmbUsuario.addItem(u);
        }
    }

    public String getTipoOperacionSeleccionado() {
        return (String) cmbTipoOperacion.getSelectedItem();
    }

    public String getUsuarioSeleccionado() {
        return (String) cmbUsuario.getSelectedItem();
    }

    public LocalDate getFechaDesde() {
        if (!chkFechaDesde.isSelected()) return null;
        Date d = (Date) spFechaDesde.getValue();
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public LocalDate getFechaHasta() {
        if (!chkFechaHasta.isSelected()) return null;
        Date d = (Date) spFechaHasta.getValue();
        return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public void cargarResultados(List<RegistroOperacion> registros) {
        tableModel.setRowCount(0);
        for (RegistroOperacion r : registros) {
            tableModel.addRow(new Object[]{
                r.getFechaHora() != null ? r.getFechaHora().format(DT_FORMATTER) : "",
                r.getUsuario(),
                r.getTipoOperacion(),
                r.getDetalle()
            });
        }
        int count = registros.size();
        lblEstado.setText(count + " registro(s) encontrado(s).");
    }

    public void limpiarFiltros() {
        cmbTipoOperacion.setSelectedIndex(0);
        cmbUsuario.setSelectedIndex(0);
        chkFechaDesde.setSelected(false);
        spFechaDesde.setEnabled(false);
        chkFechaHasta.setSelected(false);
        spFechaHasta.setEnabled(false);
        tableModel.setRowCount(0);
        lblEstado.setText(" ");
    }

    public void mostrarCargando(boolean cargando) {
        btnBuscar.setEnabled(!cargando);
        lblEstado.setText(cargando ? "Buscando..." : lblEstado.getText());
    }
}
