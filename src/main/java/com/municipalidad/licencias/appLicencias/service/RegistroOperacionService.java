package com.municipalidad.licencias.appLicencias.service;

import com.municipalidad.licencias.appLicencias.entities.RegistroOperacion;
import java.time.LocalDateTime;
import java.util.List;

public interface RegistroOperacionService {

    void registrar(String usuario, String tipoOperacion, String detalle);

    List<RegistroOperacion> buscar(String usuario, String tipo, LocalDateTime desde, LocalDateTime hasta);

    List<String> obtenerTipos();

    List<String> obtenerUsuarios();
}
