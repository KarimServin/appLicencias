package com.municipalidad.licencias.appLicencias.modules.consultarlicencias;


import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultarLicenciasFiltroDTO {

    public enum Estado {
        VIGENTES,
        EXPIRADAS,
        VENCEN_PRONTO,
        TODAS
    }

    private Estado estado;

    /** Solo aplica si estado = VENCEN_PRONTO (ej: 7, 15, 30, 60) */
    private Integer vencenEnDias;

    private Long dniTitular;                 // opcional

    private ClaseLicencia clase;             // opcional (si querés multi-clase después, lo cambiás a Set)

    // Rangos opcionales (siempre interpretados como “intersección” con estado)
    private LocalDate emisionDesde;
    private LocalDate emisionHasta;

    private LocalDate vencimientoDesde;
    private LocalDate vencimientoHasta;
}
