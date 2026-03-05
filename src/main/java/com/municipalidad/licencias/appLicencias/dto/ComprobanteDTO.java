package com.municipalidad.licencias.appLicencias.dto;

import java.time.LocalDate;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComprobanteDTO {
    private Long id;           // ← número de comprobante real
    private LocalDate fecha;
    private int monto;
    private String concepto;
    private Long licenciaId;
}