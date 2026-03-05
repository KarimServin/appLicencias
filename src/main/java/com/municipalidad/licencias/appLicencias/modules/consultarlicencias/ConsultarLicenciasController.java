package com.municipalidad.licencias.appLicencias.modules.consultarlicencias;

import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.service.ExcelExportService;
import com.municipalidad.licencias.appLicencias.service.LicenciaConsultaService;
import com.municipalidad.licencias.appLicencias.viewforms.Dialogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Cursor;
import java.awt.Desktop;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Component
public class ConsultarLicenciasController {

    private static final Logger logger = LoggerFactory.getLogger(ConsultarLicenciasController.class);

    private final LicenciaConsultaService consultaService;
    private final ExcelExportService excelExportService;

    private ConsultarLicenciasView view;
    private List<LicenciaDTO> ultimaBusqueda = List.of();

    public ConsultarLicenciasController(LicenciaConsultaService consultaService,
                                        ExcelExportService excelExportService) {
        this.consultaService = consultaService;
        this.excelExportService = excelExportService;
    }

    // ══════════════════════════════════════════════════════════════
    //  INICIALIZACIÓN
    // ══════════════════════════════════════════════════════════════

    public void display() {
        logger.info("Abriendo vista de consulta de licencias");
        SwingUtilities.invokeLater(() -> {
            view = new ConsultarLicenciasView();
            configurarCombosIniciales();
            setListeners();
            view.setVisible(true);
            logger.debug("Vista de consulta de licencias visible");
        });
    }

    private void configurarCombosIniciales() {
        view.setEstados(new String[]{"Vigentes", "Expiradas", "Vencen pronto", "Todas"});
        view.setVencenEnOpciones(new String[]{"7 días", "15 días", "30 días", "60 días"});
        view.setClases(new String[]{"A", "B", "C", "D", "E", "F", "G"});
        view.setBotonReporteEnabled(false);
    }

    private void setListeners() {
        view.onAplicarFiltros(e -> aplicarFiltros());
        view.onLimpiar(e -> limpiar());
        view.onCerrar(e -> cerrar());
        view.onEmitirReporte(e -> emitirReporte());
    }

    // ══════════════════════════════════════════════════════════════
    //  BÚSQUEDA Y FILTROS
    // ══════════════════════════════════════════════════════════════

    private void aplicarFiltros() {
        logger.info("Aplicando filtros de consulta");

        try {
            ConsultarLicenciasFiltroDTO filtro = construirFiltroDesdeView();
            validarFiltro(filtro);

            logger.debug("Filtro construido: estado={}, clase={}, dni={}, emisiónDesde={}, emisiónHasta={}, vencDesde={}, vencHasta={}",
                filtro.getEstado(),
                filtro.getClase(),
                filtro.getDniTitular(),
                filtro.getEmisionDesde(),
                filtro.getEmisionHasta(),
                filtro.getVencimientoDesde(),
                filtro.getVencimientoHasta());

            List<LicenciaDTO> resultados = consultaService.buscar(filtro);
            ultimaBusqueda = resultados;

            logger.info("Consulta finalizada: {} resultado(s) encontrado(s)", resultados.size());

            cargarTabla(resultados);
            view.setBotonReporteEnabled(!resultados.isEmpty());

            if (resultados.isEmpty()) {
                Dialogs.error(view, "No se encontraron resultados con los filtros seleccionados.");
            }

        } catch (IllegalArgumentException ex) {
            logger.warn("Filtro inválido: {}", ex.getMessage());
            Dialogs.error(view, ex.getMessage());
        } catch (Exception ex) {
            logger.error("Error inesperado al consultar licencias", ex);
            Dialogs.error(view, "Ocurrió un error al consultar licencias.");
        }
    }

    private ConsultarLicenciasFiltroDTO construirFiltroDesdeView() {
        ConsultarLicenciasFiltroDTO.Estado estado = parseEstado(view.getEstadoSeleccionado());
        Integer vencenEnDias = parseVencenEnDias(view.getVencenEnSeleccionado());
        Long dni = parseDniNullable(view.getDniTexto());
        ClaseLicencia clase = parseClaseNullable(view.getClaseSeleccionada());

        return ConsultarLicenciasFiltroDTO.builder()
                .estado(estado)
                .vencenEnDias(vencenEnDias)
                .dniTitular(dni)
                .clase(clase)
                .emisionDesde(view.getEmisionDesde())
                .emisionHasta(view.getEmisionHasta())
                .vencimientoDesde(view.getVencimientoDesde())
                .vencimientoHasta(view.getVencimientoHasta())
                .build();
    }

    private void validarFiltro(ConsultarLicenciasFiltroDTO f) {
        if (f.getEstado() == null) {
            throw new IllegalArgumentException("Debe seleccionar un estado.");
        }
        if (f.getEstado() == ConsultarLicenciasFiltroDTO.Estado.VENCEN_PRONTO
                && f.getVencenEnDias() == null) {
            throw new IllegalArgumentException("Seleccione en cuántos días vencen (7, 15, 30 o 60).");
        }

        validarRango("Emisión", f.getEmisionDesde(), f.getEmisionHasta());
        validarRango("Vencimiento", f.getVencimientoDesde(), f.getVencimientoHasta());
    }

    private void validarRango(String nombre, LocalDate desde, LocalDate hasta) {
        if (desde != null && hasta != null && desde.isAfter(hasta)) {
            throw new IllegalArgumentException(nombre + ": 'desde' no puede ser mayor que 'hasta'.");
        }
    }

    // ══════════════════════════════════════════════════════════════
    //  TABLA
    // ══════════════════════════════════════════════════════════════

    private void cargarTabla(List<LicenciaDTO> lista) {
        view.limpiarTabla();

        for (LicenciaDTO dto : lista) {
            String titular = String.valueOf(dto.getTitularDni());
            Long dni = dto.getTitularDni();

            Set<?> clases = dto.getClases();
            String clasesTexto = (clases == null || clases.isEmpty())
                    ? "-"
                    : String.join(", ", dto.getClases().stream().map(Enum::name).toList());

            view.agregarFilaTabla(titular, dni, clasesTexto, dto.getFechaVencimiento());
        }

        logger.debug("Tabla cargada con {} fila(s)", lista.size());
    }

    // ══════════════════════════════════════════════════════════════
    //  REPORTE EXCEL
    // ══════════════════════════════════════════════════════════════

    private void emitirReporte() {
        if (ultimaBusqueda == null || ultimaBusqueda.isEmpty()) {
            logger.warn("Intento de emitir reporte sin datos");
            Dialogs.info(view, "No hay datos para emitir reporte. Aplique filtros primero.");
            return;
        }

        logger.info("Iniciando exportación a Excel ({} registros)", ultimaBusqueda.size());

        // ── Elegir dónde guardar ──
        File destino = elegirArchivoDestino();
        if (destino == null) {
            logger.debug("Exportación cancelada por el usuario");
            return;
        }

        logger.info("Destino seleccionado: {}", destino.getAbsolutePath());

        // ── Generar en background ──
        final File archivoFinal = destino;

        view.setBotonReporteEnabled(false);
        view.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        new SwingWorker<File, Void>() {
            @Override
            protected File doInBackground() throws Exception {
                logger.debug("SwingWorker: generando Excel en hilo background");
                return excelExportService.exportarLicencias(ultimaBusqueda, archivoFinal);
            }

            @Override
            protected void done() {
                view.setCursor(Cursor.getDefaultCursor());
                view.setBotonReporteEnabled(true);

                try {
                    File archivo = get();
                    logger.info("Reporte generado exitosamente: {} ({} bytes)",
                        archivo.getAbsolutePath(), archivo.length());

                    preguntarAbrirArchivo(archivo);

                } catch (Exception e) {
                    logger.error("Error al exportar a Excel", e);
                    Dialogs.error(view, "Error al exportar: " + e.getMessage());
                }
            }
        }.execute();
    }

    private File elegirArchivoDestino() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Guardar reporte de licencias");

        String nombreSugerido = "Licencias_" + LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".xlsx";
        chooser.setSelectedFile(new File(nombreSugerido));
        chooser.setFileFilter(new FileNameExtensionFilter("Excel (*.xlsx)", "xlsx"));

        int resultado = chooser.showSaveDialog(view);
        if (resultado != JFileChooser.APPROVE_OPTION) return null;

        File destino = chooser.getSelectedFile();

        // Asegurar extensión .xlsx
        if (!destino.getName().toLowerCase().endsWith(".xlsx")) {
            destino = new File(destino.getAbsolutePath() + ".xlsx");
        }

        // Verificar si ya existe
        if (destino.exists()) {
            int confirmar = JOptionPane.showConfirmDialog(view,
                "El archivo ya existe. ¿Desea reemplazarlo?",
                "Archivo existente",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
            if (confirmar != JOptionPane.YES_OPTION) return null;
        }

        return destino;
    }

    private void preguntarAbrirArchivo(File archivo) {
        int opcion = JOptionPane.showConfirmDialog(view,
            "Reporte generado exitosamente.\n\n"
            + "Archivo: " + archivo.getName() + "\n"
            + "Ubicación: " + archivo.getParent() + "\n\n"
            + "¿Desea abrir el archivo?",
            "Exportar a Excel",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE);

        if (opcion == JOptionPane.YES_OPTION) {
            try {
                Desktop.getDesktop().open(archivo);
                logger.debug("Archivo abierto por el usuario: {}", archivo.getName());
            } catch (Exception e) {
                logger.error("No se pudo abrir el archivo: {}", archivo.getAbsolutePath(), e);
                Dialogs.error(view, "No se pudo abrir el archivo. Ábralo manualmente desde:\n"
                    + archivo.getAbsolutePath());
            }
        }
    }

    // ══════════════════════════════════════════════════════════════
    //  LIMPIAR Y CERRAR
    // ══════════════════════════════════════════════════════════════

    private void limpiar() {
        logger.debug("Limpiando filtros y resultados");
        view.limpiarFiltros();
        view.limpiarTabla();
        view.setBotonReporteEnabled(false);
        ultimaBusqueda = List.of();
    }

    private void cerrar() {
        logger.info("Cerrando vista de consulta de licencias");
        view.dispose();
    }

    // ══════════════════════════════════════════════════════════════
    //  PARSERS
    // ══════════════════════════════════════════════════════════════

    private ConsultarLicenciasFiltroDTO.Estado parseEstado(String s) {
        if (s == null) return null;
        String v = s.trim().toLowerCase();
        return switch (v) {
            case "vigentes" -> ConsultarLicenciasFiltroDTO.Estado.VIGENTES;
            case "expiradas" -> ConsultarLicenciasFiltroDTO.Estado.EXPIRADAS;
            case "vencen pronto" -> ConsultarLicenciasFiltroDTO.Estado.VENCEN_PRONTO;
            case "todas" -> ConsultarLicenciasFiltroDTO.Estado.TODAS;
            default -> throw new IllegalArgumentException("Estado inválido: " + s);
        };
    }

    private Integer parseVencenEnDias(String s) {
        if (s == null || s.isBlank()) return null;
        String digits = s.replaceAll("[^0-9]", "");
        if (digits.isBlank()) return null;
        int n = Integer.parseInt(digits);
        return (n == 7 || n == 15 || n == 30 || n == 60) ? n : null;
    }

    private Long parseDniNullable(String s) {
        if (s == null) return null;
        String v = s.trim();
        if (v.isBlank()) return null;
        if (!v.matches("\\d{7,9}")) {
            throw new IllegalArgumentException("DNI inválido. Ingrese solo números (7 a 9 dígitos).");
        }
        return Long.valueOf(v);
    }

    private ClaseLicencia parseClaseNullable(String s) {
        if (s == null || s.isBlank()) return null;
        String v = s.trim().toUpperCase();
        try {
            return ClaseLicencia.valueOf(v);
        } catch (Exception e) {
            throw new IllegalArgumentException("Clase inválida: " + s);
        }
    }
}