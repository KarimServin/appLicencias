package com.municipalidad.licencias.appLicencias.service;

import com.municipalidad.licencias.appLicencias.modules.gestionarcostos.CostoLicenciaDTO;
import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;

import java.util.List;

public interface CostoLicenciaService {

    int obtenerCosto(ClaseLicencia clase, boolean esCopia, int vigencia);

    List<CostoLicenciaDTO> listarTodos();

    CostoLicenciaDTO actualizar(Long id, Integer nuevoCosto);
}