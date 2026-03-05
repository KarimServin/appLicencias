package com.municipalidad.licencias.appLicencias.service;

import com.municipalidad.licencias.appLicencias.modules.consultarlicencias.ConsultarLicenciasFiltroDTO;
import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import java.util.List;

public interface LicenciaConsultaService {
    List<LicenciaDTO> buscar(ConsultarLicenciasFiltroDTO filtro);
}
