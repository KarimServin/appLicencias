package com.municipalidad.licencias.appLicencias.service.serviceImpl;

import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import com.municipalidad.licencias.appLicencias.modules.consultarlicencias.LicenciaReporteDTO;
import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.entities.Licencia;
import com.municipalidad.licencias.appLicencias.entities.Titular;
import com.municipalidad.licencias.appLicencias.repository.LicenciaRepository;
import com.municipalidad.licencias.appLicencias.service.ExcelExportService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExcelExportServiceImpl implements ExcelExportService {

    private static final int BATCH_SIZE = 500;
    private static final DateTimeFormatter FMT_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FMT_TIMESTAMP = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private static final String[] COLUMNAS = {
        "ID", "DNI Titular", "Titular", "Clases",
        "Fecha Emisión", "Fecha Vencimiento", "Vigente", "Emitida por"
    };

    private final LicenciaRepository licenciaRepository;

    public ExcelExportServiceImpl(LicenciaRepository licenciaRepository) {
        this.licenciaRepository = licenciaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public File exportarLicencias(List<LicenciaDTO> licencias, File destino) throws Exception {

        // ── Enriquecer datos por lotes ──
        List<LicenciaReporteDTO> datosReporte = enriquecerDatos(licencias);

        // ── Generar Excel ──
        try (Workbook wb = new XSSFWorkbook()) {

            Sheet sheet = wb.createSheet("Licencias");
            Estilos estilos = new Estilos(wb);

            int filaActual = 0;

            // ── Encabezado del reporte ──
            filaActual = escribirCeldaMerge(sheet, filaActual,
                "REPORTE DE LICENCIAS — Municipalidad de Santa Fe Capital",
                estilos.titulo);

            filaActual = escribirCeldaMerge(sheet, filaActual,
                "Generado: " + LocalDateTime.now().format(FMT_TIMESTAMP),
                estilos.subtitulo);

            filaActual = escribirCeldaMerge(sheet, filaActual,
                "Total de registros: " + licencias.size(),
                estilos.subtitulo);

            filaActual++; // fila vacía

            // ── Encabezados de columna ──
            Row filaEnc = sheet.createRow(filaActual);
            for (int i = 0; i < COLUMNAS.length; i++) {
                setCelda(filaEnc, i, COLUMNAS[i], estilos.encabezado);
            }
            int filaEncabezado = filaActual;
            filaActual++;

            // ── Datos ──
            for (LicenciaReporteDTO dto : datosReporte) {
                Row fila = sheet.createRow(filaActual++);

                setCelda(fila, 0, safe(dto.getId()), estilos.celda);
                setCelda(fila, 1, safe(dto.getTitularDni()), estilos.celda);
                setCelda(fila, 2, safeStr(dto.getTitularNombreCompleto()), estilos.celda);
                setCelda(fila, 3, formatearClases(dto.getClases()), estilos.celda);
                setCelda(fila, 4, formatearFecha(dto.getFechaEmision()), estilos.fecha);
                setCelda(fila, 5, formatearFecha(dto.getFechaVencimiento()), estilos.fecha);

                boolean vigente = Boolean.TRUE.equals(dto.getVigente())
                    && dto.getFechaVencimiento() != null
                    && !dto.getFechaVencimiento().isBefore(LocalDate.now());
                setCelda(fila, 6, vigente ? "Sí" : "No",
                    vigente ? estilos.vigente : estilos.expirada);

                setCelda(fila, 7, safeStr(dto.getEmitidaPor()), estilos.celda);
            }

            // ── Autoajustar ──
            for (int i = 0; i < COLUMNAS.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // ── Filtro automático ──
            sheet.setAutoFilter(new CellRangeAddress(
                filaEncabezado, filaEncabezado, 0, COLUMNAS.length - 1));

            // ── Guardar ──
            try (FileOutputStream fos = new FileOutputStream(destino)) {
                wb.write(fos);
            }

            return destino;
        }
    }

    // ══════════════════════════════════════════════════════════════
    //  ENRIQUECIMIENTO BATCH (sin N+1)
    // ══════════════════════════════════════════════════════════════

    private List<LicenciaReporteDTO> enriquecerDatos(List<LicenciaDTO> licencias) {
        if (licencias.isEmpty()) return Collections.emptyList();

        List<LicenciaReporteDTO> resultado = new ArrayList<>(licencias.size());

        for (List<LicenciaDTO> lote : partirEnLotes(licencias, BATCH_SIZE)) {

            Set<Long> ids = lote.stream()
                .map(LicenciaDTO::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

            Map<Long, Licencia> entidadesPorId = licenciaRepository.findByIdInFetchAll(ids)
                .stream()
                .collect(Collectors.toMap(Licencia::getId, l -> l));

            for (LicenciaDTO dto : lote) {
                resultado.add(construirReporteDTO(dto, entidadesPorId.get(dto.getId())));
            }
        }

        return resultado;
    }

    private LicenciaReporteDTO construirReporteDTO(LicenciaDTO dto, Licencia entidad) {
        LicenciaReporteDTO.LicenciaReporteDTOBuilder builder = LicenciaReporteDTO.builder()
            .id(dto.getId())
            .clases(dto.getClases())
            .fechaEmision(dto.getFechaEmision())
            .fechaVencimiento(dto.getFechaVencimiento())
            .vigente(dto.getVigente())
            .titularDni(dto.getTitularDni());

        if (entidad != null) {
            Titular titular = entidad.getTitular();
            if (titular != null) {
                builder.titularNombreCompleto(
                    titular.getNombre() + " " + titular.getApellido());
            }

            if (entidad.getUsuario() != null) {
                builder.emitidaPor(entidad.getUsuario().getNombreUsuario());
            }
        }

        return builder.build();
    }

    private <T> List<List<T>> partirEnLotes(List<T> lista, int tamano) {
        List<List<T>> lotes = new ArrayList<>();
        for (int i = 0; i < lista.size(); i += tamano) {
            lotes.add(lista.subList(i, Math.min(i + tamano, lista.size())));
        }
        return lotes;
    }

    // ══════════════════════════════════════════════════════════════
    //  AUXILIARES EXCEL
    // ══════════════════════════════════════════════════════════════

    private int escribirCeldaMerge(Sheet sheet, int fila, String valor, CellStyle estilo) {
        Row row = sheet.createRow(fila);
        Cell celda = row.createCell(0);
        celda.setCellValue(valor);
        celda.setCellStyle(estilo);
        sheet.addMergedRegion(new CellRangeAddress(fila, fila, 0, COLUMNAS.length - 1));
        return fila + 1;
    }

    private void setCelda(Row fila, int col, String valor, CellStyle estilo) {
        Cell celda = fila.createCell(col);
        celda.setCellValue(valor);
        celda.setCellStyle(estilo);
    }

    private String safe(Object obj) {
        return obj != null ? obj.toString() : "";
    }

    private String safeStr(String str) {
        return str != null ? str : "";
    }

    private String formatearFecha(LocalDate fecha) {
        return fecha != null ? fecha.format(FMT_FECHA) : "";
    }

    private String formatearClases(Set<ClaseLicencia> clases) {
        if (clases == null || clases.isEmpty()) return "";
        return clases.stream()
            .map(ClaseLicencia::name)
            .sorted()
            .collect(Collectors.joining(", "));
    }

    // ══════════════════════════════════════════════════════════════
    //  ESTILOS (clase interna para no repetir creación)
    // ══════════════════════════════════════════════════════════════

    private static class Estilos {
        final CellStyle titulo;
        final CellStyle subtitulo;
        final CellStyle encabezado;
        final CellStyle celda;
        final CellStyle fecha;
        final CellStyle vigente;
        final CellStyle expirada;

        Estilos(Workbook wb) {
            this.titulo = crearTitulo(wb);
            this.subtitulo = crearSubtitulo(wb);
            this.encabezado = crearEncabezado(wb);
            this.celda = crearCelda(wb);
            this.fecha = crearFecha(wb);
            this.vigente = crearEstado(wb, IndexedColors.GREEN);
            this.expirada = crearEstado(wb, IndexedColors.RED);
        }

        private static CellStyle crearTitulo(Workbook wb) {
            CellStyle estilo = wb.createCellStyle();
            Font font = wb.createFont();
            font.setBold(true);
            font.setFontHeightInPoints((short) 14);
            font.setColor(IndexedColors.DARK_TEAL.getIndex());
            estilo.setFont(font);
            return estilo;
        }

        private static CellStyle crearSubtitulo(Workbook wb) {
            CellStyle estilo = wb.createCellStyle();
            Font font = wb.createFont();
            font.setItalic(true);
            font.setFontHeightInPoints((short) 10);
            font.setColor(IndexedColors.GREY_50_PERCENT.getIndex());
            estilo.setFont(font);
            return estilo;
        }

        private static CellStyle crearEncabezado(Workbook wb) {
            CellStyle estilo = wb.createCellStyle();
            Font font = wb.createFont();
            font.setBold(true);
            font.setFontHeightInPoints((short) 11);
            font.setColor(IndexedColors.WHITE.getIndex());
            estilo.setFont(font);
            estilo.setFillForegroundColor(IndexedColors.DARK_TEAL.getIndex());
            estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            aplicarBordes(estilo);
            estilo.setAlignment(HorizontalAlignment.CENTER);
            return estilo;
        }

        private static CellStyle crearCelda(Workbook wb) {
            CellStyle estilo = wb.createCellStyle();
            aplicarBordes(estilo);
            Font font = wb.createFont();
            font.setFontHeightInPoints((short) 10);
            estilo.setFont(font);
            return estilo;
        }

        private static CellStyle crearFecha(Workbook wb) {
            CellStyle estilo = crearCelda(wb);
            estilo.setAlignment(HorizontalAlignment.CENTER);
            return estilo;
        }

        private static CellStyle crearEstado(Workbook wb, IndexedColors color) {
            CellStyle estilo = wb.createCellStyle();
            aplicarBordes(estilo);
            Font font = wb.createFont();
            font.setBold(true);
            font.setFontHeightInPoints((short) 10);
            font.setColor(color.getIndex());
            estilo.setFont(font);
            estilo.setAlignment(HorizontalAlignment.CENTER);
            return estilo;
        }

        private static void aplicarBordes(CellStyle estilo) {
            estilo.setBorderBottom(BorderStyle.THIN);
            estilo.setBorderTop(BorderStyle.THIN);
            estilo.setBorderLeft(BorderStyle.THIN);
            estilo.setBorderRight(BorderStyle.THIN);
        }
    }
}