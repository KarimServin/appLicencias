package com.municipalidad.licencias.appLicencias.service;

import com.municipalidad.licencias.appLicencias.dto.ComprobanteDTO;
import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;


public interface ComprobanteService {
    ComprobanteDTO generarComprobante(LicenciaDTO licencia, int monto, String concepto);
}
