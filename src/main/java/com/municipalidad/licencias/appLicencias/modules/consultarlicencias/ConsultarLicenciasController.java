package com.municipalidad.licencias.appLicencias.modules.consultarlicencias;

import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.service.LicenciaConsultaService;
import com.municipalidad.licencias.appLicencias.view.Dialogs;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import javax.swing.SwingUtilities;
import org.springframework.stereotype.Component;



@Component
public class ConsultarLicenciasController {

    private final LicenciaConsultaService consultaService;

    private ConsultarLicenciasView view;

    // cache simple para el botón “Emitir Reporte”
    private List<LicenciaDTO> ultimaBusqueda = List.of();

    public ConsultarLicenciasController(LicenciaConsultaService consultaService) {
        this.consultaService = consultaService;
    }

    public void display() {
        SwingUtilities.invokeLater(() -> {
            view = new ConsultarLicenciasView();
            configurarCombosIniciales();
            setListeners();
            view.setVisible(true);
        });
    }

    private void configurarCombosIniciales() {
        // Texto de UI (lo mapeamos a enum en parseEstado)
        view.setEstados(new String[] { "Vigentes", "Expiradas", "Vencen pronto", "Todas" });
        view.setVencenEnOpciones(new String[] { "7 días", "15 días", "30 días", "60 días" });

        // Clases: simple. Si preferís “Todas” como opción, se agrega.
        view.setClases(new String[] { "A", "B", "C", "D", "E", "F", "G" });

        view.setBotonReporteEnabled(false);
    }

    private void setListeners() {
        view.onAplicarFiltros(e -> aplicarFiltros());
        view.onLimpiar(e -> limpiar());
        view.onCerrar(e -> cerrar());
        view.onEmitirReporte(e -> emitirReporte());
    }

    private void aplicarFiltros() {
        try {
            ConsultarLicenciasFiltroDTO filtro = construirFiltroDesdeView();
            validarFiltro(filtro);

            List<LicenciaDTO> resultados = consultaService.buscar(filtro);
            ultimaBusqueda = resultados;

            cargarTabla(resultados);
            view.setBotonReporteEnabled(!resultados.isEmpty());

            if (resultados.isEmpty()) {
                Dialogs.error(view, "No se encontraron resultados con los filtros seleccionados.");
            }

        } catch (IllegalArgumentException ex) {
            Dialogs.error(view, ex.getMessage());
        } catch (Exception ex) {
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
        if (f.getEstado() == ConsultarLicenciasFiltroDTO.Estado.VENCEN_PRONTO && f.getVencenEnDias() == null) {
            throw new IllegalArgumentException("Seleccione en cuántos días vencen (7, 15, 30 o 60).");
        }

        // Rangos: validación básica (desde <= hasta)
        validarRango("Emisión", f.getEmisionDesde(), f.getEmisionHasta());
        validarRango("Vencimiento", f.getVencimientoDesde(), f.getVencimientoHasta());

        // Regla UX: si puso “Vencen pronto”, no tiene sentido además “vencimientoHasta” muy lejano
        // (lo dejamos permitido, el service intersecta).
    }

    private void validarRango(String nombre, LocalDate desde, LocalDate hasta) {
        if (desde != null && hasta != null && desde.isAfter(hasta)) {
            throw new IllegalArgumentException(nombre + ": 'desde' no puede ser mayor que 'hasta'.");
        }
    }

    private void cargarTabla(List<LicenciaDTO> lista) {
        view.limpiarTabla();

        for (LicenciaDTO dto : lista) {
            String titular = String.valueOf(dto.getTitularDni()); // si después tenés nombre+apellido, lo cambiás
            Long dni = dto.getTitularDni();

            // ahora hay Set de clases en tu LicenciaDTO (refactor reciente)
            Set<?> clases = (Set<?>) dto.getClases();
            String clasesTexto = (clases == null || clases.isEmpty())
                    ? "-"
                    : String.join(", ", dto.getClases().stream().map(Enum::name).toList());

            // la tabla del diseño dice "Fecha Expiración" (equivale a vencimiento)
            view.agregarFilaTabla(titular, dni, clasesTexto, dto.getFechaVencimiento());
        }
    }

    private void limpiar() {
        view.limpiarFiltros();
        view.limpiarTabla();
        view.setBotonReporteEnabled(false);
        ultimaBusqueda = List.of();
    }

    private void cerrar() {
        view.dispose();
    }

    private void emitirReporte() {
        if (ultimaBusqueda == null || ultimaBusqueda.isEmpty()) {
            Dialogs.info(view, "No hay datos para emitir reporte. Aplique filtros primero.");
            return;
        }
        // Stub: lo implementamos después (CSV o PDF)
        Dialogs.info(view, "Reporte: pendiente de implementación (CSV/PDF).");
    }

    // =========================
    // Parsers (robustos)
    // =========================

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
        // formatos esperados: "7 días", "15 días"...
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