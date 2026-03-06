package com.municipalidad.licencias.appLicencias.modules.gestionarcostos;

import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.service.CostoLicenciaService;
import com.municipalidad.licencias.appLicencias.viewforms.Dialogs;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class GestionarCostosController {

    private static final Logger logger = LoggerFactory.getLogger(GestionarCostosController.class);

    // Mapeo columna tabla → vigencia real
    // col 1 = 5 años, col 2 = 4 años, col 3 = 3 años, col 4 = 1 año
    private static final int[] VIGENCIAS = {5, 4, 3, 1};

    private final CostoLicenciaService costoService;

    private GestionarCostosView view;

    // Estado original para detectar cambios
    private Map<String, Integer> costosOriginales = new HashMap<>(); // "A-5" → 48
    private Integer costoOriginalCopia;

    // Cache para IDs al guardar
    private Map<String, Long> idsPorClave = new HashMap<>(); // "A-5" → id
    private Long idCopia;

    public GestionarCostosController(CostoLicenciaService costoService) {
        this.costoService = costoService;
    }

    // ══════════════════════════════════════════════════════════════
    //  INICIALIZACIÓN
    // ══════════════════════════════════════════════════════════════

    public void display() {
        logger.info("Abriendo vista de gestión de costos");
        SwingUtilities.invokeLater(() -> {
            view = new GestionarCostosView();
            setListeners();
            cargarCostos();
            view.setVisible(true);
        });
    }

    private void setListeners() {
        view.onGuardar(e -> guardarCambios());
        view.onCerrar(e -> cerrar());
        view.onAplicarPorcentaje(e -> aplicarPorcentaje());
    }

    // ══════════════════════════════════════════════════════════════
    //  CARGAR DATOS
    // ══════════════════════════════════════════════════════════════

    private void cargarCostos() {
        try {
            List<CostoLicenciaDTO> todos = costoService.listarTodos();

            // Indexar costos normales: "A-5" → CostoLicenciaDTO
            Map<String, CostoLicenciaDTO> mapa = todos.stream()
                .filter(c -> !Boolean.TRUE.equals(c.getEsCopia()))
                .collect(Collectors.toMap(
                    c -> c.getClaseLicencia().name() + "-" + c.getVigencia(),
                    c -> c
                ));

            // Guardar estado original e IDs
            costosOriginales.clear();
            idsPorClave.clear();

            mapa.forEach((clave, dto) -> {
                costosOriginales.put(clave, dto.getCosto());
                idsPorClave.put(clave, dto.getId());
            });

            // Cargar matriz en la view
            view.limpiarMatriz();
            for (String clase : view.getClases()) {
                Integer c5 = obtenerCosto(mapa, clase, 5);
                Integer c4 = obtenerCosto(mapa, clase, 4);
                Integer c3 = obtenerCosto(mapa, clase, 3);
                Integer c1 = obtenerCosto(mapa, clase, 1);
                view.agregarFilaMatriz(clase, c5, c4, c3, c1);
            }

            // Cargar copia
            CostoLicenciaDTO copiaBD = todos.stream()
                .filter(c -> Boolean.TRUE.equals(c.getEsCopia()))
                .findFirst()
                .orElse(null);

            if (copiaBD != null) {
                costoOriginalCopia = copiaBD.getCosto();
                idCopia = copiaBD.getId();
            } else {
                costoOriginalCopia = null;
                idCopia = null;
            }
            view.setCostoCopia(costoOriginalCopia);

            logger.info("Costos cargados: {} registro(s)", todos.size());
            view.setStatus("Costos cargados. Edite los valores y presione Guardar.");

        } catch (Exception e) {
            logger.error("Error al cargar costos", e);
            Dialogs.error(view, "Error al cargar los costos: " + e.getMessage());
        }
    }

    private Integer obtenerCosto(Map<String, CostoLicenciaDTO> mapa, String clase, int vigencia) {
        CostoLicenciaDTO dto = mapa.get(clase + "-" + vigencia);
        return dto != null ? dto.getCosto() : null;
    }

    // ══════════════════════════════════════════════════════════════
    //  APLICAR PORCENTAJE
    // ══════════════════════════════════════════════════════════════

    private void aplicarPorcentaje() {
        String texto = view.getPorcentajeTexto();

        if (texto.isBlank()) {
            Dialogs.error(view, "Ingrese un porcentaje. Ej: 10 para +10%, -5 para -5%");
            return;
        }

        double porcentaje;
        try {
            porcentaje = Double.parseDouble(texto.replace(",", "."));
        } catch (NumberFormatException e) {
            Dialogs.error(view, "Porcentaje inválido. Use números. Ej: 10 o -5");
            return;
        }

        if (porcentaje == 0) {
            Dialogs.info(view, "Un ajuste del 0% no modifica ningún valor.");
            return;
        }

        // Confirmar
        String signo = porcentaje >= 0 ? "+" : "";
        int confirmar = JOptionPane.showConfirmDialog(view,
            "Se aplicará un ajuste de " + signo + texto + "% a TODOS los costos.\n\n"
            + "Esto modificará los valores en pantalla.\n"
            + "Los cambios NO se guardan hasta que presione 'Guardar Cambios'.\n\n"
            + "¿Desea continuar?",
            "Confirmar ajuste porcentual",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (confirmar != JOptionPane.YES_OPTION) {
            logger.debug("Ajuste porcentual cancelado por el usuario");
            return;
        }

        logger.info("Aplicando ajuste porcentual: {}%", porcentaje);

        view.detenerEdicion();
        double factor = 1.0 + (porcentaje / 100.0);

        // Aplicar a la matriz
        int celdasModificadas = 0;
        for (int fila = 0; fila < view.getFilaCount(); fila++) {
            for (int col = 1; col <= 4; col++) {
                Integer actual = obtenerCostoCelda(fila, col);
                if (actual != null) {
                    int nuevo = (int) Math.round(actual * factor);
                    if (nuevo < 0) nuevo = 0;
                    view.setCostoEnCelda(fila, col, nuevo);
                    celdasModificadas++;
                }
            }
        }

        // Aplicar a copia
        Integer copiaActual = obtenerCostoCopiaSeguro();
        if (copiaActual != null) {
            int nuevaCopia = (int) Math.round(copiaActual * factor);
            if (nuevaCopia < 0) nuevaCopia = 0;
            view.setCostoCopia(nuevaCopia);
            celdasModificadas++;
        }

        view.limpiarPorcentaje();
        view.setStatus("Ajuste de " + signo + texto + "% aplicado a "
            + celdasModificadas + " valor(es). Presione Guardar para confirmar.");

        logger.info("Ajuste porcentual aplicado: {}% (factor: {}) — {} celdas modificadas",
            porcentaje, factor, celdasModificadas);
    }

    // ══════════════════════════════════════════════════════════════
    //  GUARDAR CAMBIOS
    // ══════════════════════════════════════════════════════════════

    private void guardarCambios() {
        logger.info("Iniciando guardado de cambios de costos");

        view.detenerEdicion();

        // ── Detectar cambios ──
        List<CambioDTO> cambios = detectarCambios();

        if (cambios.isEmpty()) {
            logger.debug("No se detectaron cambios");
            Dialogs.info(view, "No hay cambios para guardar.");
            return;
        }

        // ── Validar ──
        for (CambioDTO cambio : cambios) {
            if (cambio.nuevoCosto == null || cambio.nuevoCosto < 0) {
                logger.warn("Costo inválido: {} → {}", cambio.descripcion, cambio.nuevoCosto);
                Dialogs.error(view,
                    "Costo inválido para " + cambio.descripcion + ".\n"
                    + "Debe ser un número mayor o igual a cero.");
                return;
            }
        }

        // ── Confirmar con resumen ──
        //String resumen = construirResumen(cambios);

        int confirmar = JOptionPane.showConfirmDialog(view,
            "Se modificarán " + cambios.size() + " costo(s).\n\n"
            + "\n"
            + "¿Confirma los cambios?",
            "Confirmar cambios de costos",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (confirmar != JOptionPane.YES_OPTION) {
            logger.debug("Guardado cancelado por el usuario");
            return;
        }

        // ── Aplicar cambios ──
        view.setGuardarEnabled(false);
        int exitosos = 0;
        int fallidos = 0;

        for (CambioDTO cambio : cambios) {
            try {
                costoService.actualizar(cambio.id, cambio.nuevoCosto);
                exitosos++;
                logger.info("Costo actualizado: {} | ${} → ${}",
                    cambio.descripcion, cambio.costoAnterior, cambio.nuevoCosto);
            } catch (Exception e) {
                fallidos++;
                logger.error("Error al actualizar costo {}: {}", cambio.descripcion, e.getMessage());
            }
        }

        view.setGuardarEnabled(true);

        if (fallidos == 0) {
            logger.info("Todos los costos actualizados exitosamente ({} cambios)", exitosos);
            Dialogs.info(view, exitosos + " costo(s) actualizados exitosamente.");
            view.setStatus(exitosos + " costo(s) actualizados correctamente.");
        } else {
            logger.warn("Guardado parcial: {} exitosos, {} fallidos", exitosos, fallidos);
            Dialogs.error(view,
                "Se actualizaron " + exitosos + " costo(s) pero fallaron " + fallidos + ".\n"
                + "Revise los logs para más detalle.");
        }

        // Recargar para reflejar estado real de la BD
        cargarCostos();
    }

    // ══════════════════════════════════════════════════════════════
    //  DETECTAR CAMBIOS
    // ══════════════════════════════════════════════════════════════

    private List<CambioDTO> detectarCambios() {
        List<CambioDTO> cambios = new ArrayList<>();

        // Comparar matriz
        for (int fila = 0; fila < view.getFilaCount(); fila++) {
            String clase = view.getClaseEnFila(fila);

            for (int col = 1; col <= 4; col++) {
                int vigencia = VIGENCIAS[col - 1];
                String clave = clase + "-" + vigencia;

                Integer costoActual = obtenerCostoCelda(fila, col);
                Integer original = costosOriginales.get(clave);
                Long id = idsPorClave.get(clave);

                if (id != null && costoActual != null && !costoActual.equals(original)) {
                    CambioDTO cambio = new CambioDTO();
                    cambio.id = id;
                    cambio.descripcion = "Clase " + clase + " / " + vigencia + " año(s)";
                    cambio.costoAnterior = original;
                    cambio.nuevoCosto = costoActual;
                    cambios.add(cambio);
                }
            }
        }

        // Comparar copia
        Integer copiaActual = obtenerCostoCopiaSeguro();
        if (idCopia != null && copiaActual != null && !copiaActual.equals(costoOriginalCopia)) {
            CambioDTO cambio = new CambioDTO();
            cambio.id = idCopia;
            cambio.descripcion = "Copia de licencia";
            cambio.costoAnterior = costoOriginalCopia;
            cambio.nuevoCosto = copiaActual;
            cambios.add(cambio);
        }

        logger.debug("Cambios detectados: {}", cambios.size());
        return cambios;
    }

    /*
    private String construirResumen(List<CambioDTO> cambios) {
        StringBuilder sb = new StringBuilder();
        for (CambioDTO c : cambios) {
            sb.append("• ").append(c.descripcion)
              .append(":  $").append(c.costoAnterior)
              .append("  →  $").append(c.nuevoCosto)
              .append("\n");
        }
        return sb.toString();
    }
    */
    // ══════════════════════════════════════════════════════════════
    //  CERRAR
    // ══════════════════════════════════════════════════════════════

    private void cerrar() {
        view.detenerEdicion();

        List<CambioDTO> cambios = detectarCambios();

        if (!cambios.isEmpty()) {
            int confirmar = JOptionPane.showConfirmDialog(view,
                "Tiene " + cambios.size() + " cambio(s) sin guardar.\n"
                + "¿Desea salir sin guardar?",
                "Cambios sin guardar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            if (confirmar != JOptionPane.YES_OPTION) return;
        }

        logger.info("Cerrando vista de gestión de costos");
        view.dispose();
    }

    // ══════════════════════════════════════════════════════════════
    //  HELPERS PRIVADOS
    // ══════════════════════════════════════════════════════════════

    private Integer obtenerCostoCelda(int fila, int col) {
        try {
            return view.getCostoEnCelda(fila, col);
        } catch (NumberFormatException e) {
            logger.warn("Valor no numérico en fila {} columna {}", fila, col);
            return null;
        }
    }

    private Integer obtenerCostoCopiaSeguro() {
        try {
            return view.getCostoCopia();
        } catch (NumberFormatException e) {
            logger.warn("Valor no numérico en costo de copia");
            return null;
        }
    }

    // ════════════════════���═════════════════════════════════════════
    //  DTO INTERNO
    // ══════════════════════════════════════════════════════════════

    private static class CambioDTO {
        Long id;
        String descripcion;
        Integer costoAnterior;
        Integer nuevoCosto;
    }
}