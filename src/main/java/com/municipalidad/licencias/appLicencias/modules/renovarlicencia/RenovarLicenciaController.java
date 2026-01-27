package com.municipalidad.licencias.appLicencias.modules.renovarlicencia;

import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.service.LicenciaService;
import com.municipalidad.licencias.appLicencias.view.Dialogs;
import java.util.List;
import javax.swing.SwingUtilities;
import org.springframework.stereotype.Component;

@Component
public class RenovarLicenciaController {

    private final LicenciaService licenciaService;

    private RenovarLicenciaView view;
    private final LicenciasRenovablesTableModel tableModel = new LicenciasRenovablesTableModel();

    public RenovarLicenciaController(LicenciaService licenciaService) {
        this.licenciaService = licenciaService;
    }

    public void display() {
        SwingUtilities.invokeLater(() -> {
            view = new RenovarLicenciaView();
            view.setLocationRelativeTo(null);

            // Seteo del model real con checkbox
            view.setTableModel(tableModel);

            // Estado inicial
            view.setConfirmarEnabled(false);

            setListeners();
            view.setVisible(true);
        });
    }

    private void setListeners() {
        view.setBuscarAction(e -> buscar());
        view.setLimpiarAction(e -> limpiar());
        view.setConfirmarRenovacionAction(e -> confirmarRenovacion());
        view.setCancelarAction(e -> cancelar());
    }

    private void buscar() {
        try {
            Long dni = parseDni(view.getDniIngresado());

            List<LicenciaRenovableRowDTO> rows =
                    licenciaService.buscarUltimasPorClaseParaRenovar(dni);

            tableModel.setRows(rows);
            view.setConfirmarEnabled(!rows.isEmpty());

            if (rows.isEmpty()) {
                Dialogs.error(view, "No se encontraron licencias asociadas a ese DNI.");
            }

        } catch (ServiceException ex) {
            Dialogs.error(view, ex.getMessage());
        } catch (Exception ex) {
            Dialogs.error(view, "Error inesperado al buscar licencias.");
        }
    }

    private void confirmarRenovacion() {
        try {
            Long dni = parseDni(view.getDniIngresado());

            List<ClaseLicencia> seleccionadas = tableModel.getRows().stream()
                    .filter(r -> Boolean.TRUE.equals(r.getRenovar()))
                    .map(LicenciaRenovableRowDTO::getClase)
                    .toList();

            if (seleccionadas.isEmpty()) {
                Dialogs.error(view, "Seleccioná al menos una licencia para renovar.");
                return;
            }

            List<LicenciaDTO> renovadas = licenciaService.renovarLicencias(dni, seleccionadas);

            Dialogs.exito(view, "Renovación exitosa. Licencias renovadas: " + renovadas.size());

            // Refrescar: ahora las últimas por clase pasan a ser las nuevas
            buscar();

        } catch (ServiceException ex) {
            Dialogs.error(view, ex.getMessage());
        } catch (Exception ex) {
            Dialogs.error(view, "Error inesperado al renovar.");
        }
    }

    private void limpiar() {
        // Limpia tabla y deshabilita confirmar
        tableModel.setRows(List.of());
        view.setConfirmarEnabled(false);

        // Limpia input DNI (necesita método en la view)
        view.setDniIngresado("");

        // Opcional: focus al campo
        view.focusDni();
    }

    private void cancelar() {
        view.dispose();
    }

    private Long parseDni(String dniStr) {
        if (dniStr == null || dniStr.isBlank()) {
            throw new ServiceException("Ingresá un DNI.");
        }
        try {
            return Long.valueOf(dniStr.trim());
        } catch (NumberFormatException ex) {
            throw new ServiceException("El DNI debe ser numérico.");
        }
    }
}
