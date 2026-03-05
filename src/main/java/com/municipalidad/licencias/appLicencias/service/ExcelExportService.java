package com.municipalidad.licencias.appLicencias.service;

import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import java.io.File;
import java.util.List;

public interface ExcelExportService {
    File exportarLicencias(List<LicenciaDTO> licencias, File destino) throws Exception;
}