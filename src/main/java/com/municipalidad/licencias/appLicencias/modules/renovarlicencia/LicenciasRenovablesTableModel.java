package com.municipalidad.licencias.appLicencias.modules.renovarlicencia;

import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class LicenciasRenovablesTableModel extends AbstractTableModel {

    private final String[] columnNames = {
        "Clase",
        "Vigente",
        "Fecha Expiración",
        "Renovar"
    };

    private List<LicenciaRenovableRowDTO> rows = new ArrayList<>();

    public void setRows(List<LicenciaRenovableRowDTO> rows) {
        this.rows = (rows != null) ? rows : new ArrayList<>();
        fireTableDataChanged();
    }

    public List<LicenciaRenovableRowDTO> getRows() {
        return rows;
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return switch (col) {
            case 0 -> ClaseLicencia.class;
            case 1 -> Boolean.class;
            case 2 -> LocalDate.class;
            case 3 -> Boolean.class;
            default -> Object.class;
        };
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        // Solo la columna "Renovar" es editable
        return col == 3;
    }

    @Override
    public Object getValueAt(int row, int col) {
        LicenciaRenovableRowDTO r = rows.get(row);
        return switch (col) {
            case 0 -> r.getClase();
            case 1 -> r.getVigente();
            case 2 -> r.getFechaVencimiento();
            case 3 -> r.getRenovar();
            default -> null;
        };
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        if (col == 3 && value instanceof Boolean) {
            rows.get(row).setRenovar((Boolean) value);
            fireTableCellUpdated(row, col);
        }
    }
}