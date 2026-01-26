package com.municipalidad.licencias.appLicencias.modules.modificartitular;

import com.municipalidad.licencias.appLicencias.dto.ActualizarTitularRequestDTO;
import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.service.TitularService;
import com.municipalidad.licencias.appLicencias.view.Dialogs;
import java.util.Optional;
import javax.swing.SwingUtilities;
import org.springframework.stereotype.Component;

@Component
public class ModificarTitularController {

    private final TitularService titularService;
    private ModificarTitularView view;

    private TitularDTO titularActual; // estado en pantalla

    public ModificarTitularController(TitularService titularService) {
        this.titularService = titularService;
    }

    public void display() {
        SwingUtilities.invokeLater(() -> {
            view = new ModificarTitularView();
            view.setEdicionEnabled(false);
            view.setLocationRelativeTo(null);
            view.setVisible(true);
            setListeners();
            limpiarPantalla(); // deja labels “vacíos” si querés
        });
    }

    private void setListeners() {
        view.setBuscarTitularAction(e -> buscarTitular());
        view.setLimpiarAction(e -> limpiarPantalla());
        view.setConfirmarAction(e -> confirmar());
        view.setCancelarAction(e -> cancelar());
    }

    private void buscarTitular() {
        String dniStr = view.getDniIngresado();

        if (dniStr.isBlank()) {
            Dialogs.error(view, "Ingresá un DNI.");
            return;
        }

        Long dni;
        try {
            dni = Long.valueOf(dniStr);
        } catch (NumberFormatException ex) {
            Dialogs.error(view, "El DNI debe ser numérico.");
            return;
        }

        Optional<TitularDTO> opt = titularService.buscarPorDni(dni);

        if (opt.isEmpty()) {
            titularActual = null;
            view.setEdicionEnabled(false);
            // Si querés, limpiá labels:
            view.setTitularInfo("-", "-", "-");
            Dialogs.error(view, "No se encontró un titular con DNI " + dni + ".");
            return;
        }

        titularActual = opt.get();

        String nombreCompleto = titularActual.getApellido() + ", " + titularActual.getNombre();
        String domicilio = titularActual.getDomicilio() != null ? titularActual.getDomicilio() : "-";
        String esDonanteTxt = Boolean.TRUE.equals(titularActual.getEsDonante()) ? "Sí" : "No";

        view.setTitularInfo(nombreCompleto, domicilio, esDonanteTxt);

        // Pre-cargar campos editables con los valores actuales
        view.setEdicionDefaults(titularActual.getDomicilio(), Boolean.TRUE.equals(titularActual.getEsDonante()));
        view.setEdicionEnabled(true);
    }

    private void confirmar() {
        if (titularActual == null) {
            Dialogs.error(view, "Primero tenés que buscar un titular válido.");
            return;
        }

        String domicilioNuevo = view.getDomicilioActualizado();
        boolean esDonanteNuevo = view.getEsDonanteSeleccionado();

        // Validación simple (ajustá reglas según tu sistema)
        if (domicilioNuevo.isBlank()) {
            Dialogs.error(view, "El domicilio no puede quedar vacío.");
            return;
        }

        // Si no cambió nada, evitás pegarle a la DB al pedo
        boolean mismoDomicilio = domicilioNuevo.equalsIgnoreCase(
                titularActual.getDomicilio() == null ? "" : titularActual.getDomicilio().trim()
        );
        boolean mismoDonante = esDonanteNuevo == Boolean.TRUE.equals(titularActual.getEsDonante());

        if (mismoDomicilio && mismoDonante) {
            Dialogs.error(view, "No hay cambios para guardar.");
            return;
        }

        try {
            ActualizarTitularRequestDTO req = ActualizarTitularRequestDTO.builder()
                    .domicilio(domicilioNuevo)
                    .esDonante(esDonanteNuevo)
                    .build();

            TitularDTO actualizado = titularService.actualizarDatosTitular(titularActual.getDni(), req);

            titularActual = actualizado; // refrescás estado
            // refrescar labels con los nuevos
            String nombreCompleto = actualizado.getApellido() + ", " + actualizado.getNombre();
            String esDonanteTxt = Boolean.TRUE.equals(actualizado.getEsDonante()) ? "Sí" : "No";
            view.setTitularInfo(nombreCompleto, actualizado.getDomicilio(), esDonanteTxt);

            Dialogs.exito(view, "Titular actualizado correctamente.");
            view.setEdicionEnabled(false); // opcional: lo bloqueás luego de guardar
        } catch (Exception ex) {
            Dialogs.error(view, "No se pudo actualizar el titular: " + ex.getMessage());
        }
    }


    private void limpiarPantalla() {
        titularActual = null;
        // limpiás inputs
        // (si no tenés setters, crealos o hacelo directo con el designer)
        view.setTitularInfo("-", "-", "-");
        view.setEdicionDefaults("", false);
        view.setEdicionEnabled(false);
        // también podrías limpiar dniTitularField si agregás setDni("")
    }

    private void cancelar() {
        view.dispose();
    }
}

