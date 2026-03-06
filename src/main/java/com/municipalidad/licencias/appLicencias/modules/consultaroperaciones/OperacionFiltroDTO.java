package com.municipalidad.licencias.appLicencias.modules.consultaroperaciones;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperacionFiltroDTO {

    private String usuario;
    private String tipoOperacion;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
}
