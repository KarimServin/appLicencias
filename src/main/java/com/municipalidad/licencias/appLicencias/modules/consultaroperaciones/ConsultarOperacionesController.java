package com.municipalidad.licencias.appLicencias.modules.consultaroperaciones;

import com.municipalidad.licencias.appLicencias.entities.RegistroOperacion;
import com.municipalidad.licencias.appLicencias.service.RegistroOperacionService;
import com.municipalidad.licencias.appLicencias.viewforms.Dialogs;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ConsultarOperacionesController {

    private static final Logger logger = LoggerFactory.getLogger(ConsultarOperacionesController.class);

    private final RegistroOperacionService registroService;
    private ConsultarOperacionesView view;

    public ConsultarOperacionesController(RegistroOperacionService registroService) {
        this.registroService = registroService;
    }

    // ══════════════════════════════════════════════════════════════
    //  INICIALIZACIÓN
    // ══════════════════════════════════════════════════════════════

    public void display() {
        logger.info("Abriendo vista de consulta de operaciones");
        SwingUtilities.invokeLater(() -> {
            view = new ConsultarOperacionesView();
            cargarCombos();
            setListeners();
            view.setVisible(true);
            logger.debug("Vista de consulta de operaciones visible");
        });
    }

    private void cargarCombos() {
        try {
            List<String> tipos = registroService.obtenerTipos();
            view.setTiposOperacion(tipos.toArray(new String[0]));

            List<String> usuarios = registroService.obtenerUsuarios();
            view.setUsuarios(usuarios.toArray(new String[0]));
        } catch (Exception e) {
            logger.warn("No se pudieron cargar los combos iniciales: {}", e.getMessage());
        }
    }

    private void setListeners() {
        view.onBuscar(e -> buscar());
        view.onLimpiar(e -> limpiar());
        view.onCerrar(e -> cerrar());
    }

    // ══════════════════════════════════════════════════════════════
    //  BÚSQUEDA
    // ══════════════════════════════════════════════════════════════

    private void buscar() {
        logger.info("Iniciando búsqueda de operaciones");

        String tipoRaw = view.getTipoOperacionSeleccionado();
        String usuarioRaw = view.getUsuarioSeleccionado();
        LocalDate desdeDate = view.getFechaDesde();
        LocalDate hastaDate = view.getFechaHasta();

        String tipo = ("Todos".equals(tipoRaw)) ? null : tipoRaw;
        String usuario = ("Todos".equals(usuarioRaw)) ? null : usuarioRaw;

        LocalDateTime desde = (desdeDate != null) ? desdeDate.atStartOfDay() : null;
        LocalDateTime hasta = (hastaDate != null) ? hastaDate.atTime(LocalTime.MAX) : null;

        view.mostrarCargando(true);

        new SwingWorker<List<RegistroOperacion>, Void>() {
            @Override
            protected List<RegistroOperacion> doInBackground() throws Exception {
                return registroService.buscar(usuario, tipo, desde, hasta);
            }

            @Override
            protected void done() {
                view.mostrarCargando(false);
                try {
                    List<RegistroOperacion> resultados = get();
                    view.cargarResultados(resultados);
                    logger.info("Búsqueda finalizada: {} resultado(s)", resultados.size());

                    if (resultados.isEmpty()) {
                        Dialogs.info(view, "No se encontraron registros con los filtros seleccionados.");
                    }
                } catch (Exception e) {
                    logger.error("Error al buscar operaciones", e);
                    Dialogs.error(view, "Error de conexión. Intente nuevamente.");
                }
            }
        }.execute();
    }

    // ══════════════════════════════════════════════════════════════
    //  LIMPIAR Y CERRAR
    // ══════════════════════════════════════════════════════════════

    private void limpiar() {
        logger.debug("Limpiando filtros y resultados");
        view.limpiarFiltros();
    }

    private void cerrar() {
        logger.info("Cerrando vista de consulta de operaciones");
        view.dispose();
    }
}
