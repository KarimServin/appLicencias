package com.municipalidad.licencias.appLicencias.service;

import com.municipalidad.licencias.appLicencias.modules.emitirlicencia.EmisionLicenciaDTO;
import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import java.util.List;
import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.entities.Titular;
import java.time.LocalDate;
import java.util.Set;



public interface LicenciaService {

    boolean puedeEmitirLicencia(Titular titular, Set<ClaseLicencia> clasesSolicitadas, LocalDate hoy);

    LicenciaDTO emitirLicencia(EmisionLicenciaDTO dto);

    boolean estaVigente(ClaseLicencia clase, Titular titular);

    int calcularVigencia(Titular titular);

    int calcularCosto(ClaseLicencia clase, boolean esCopia, int vigencia);

    List<LicenciaDTO> obtenerLicenciasExpiradas();

    List<LicenciaDTO> obtenerLicenciasPorTitular(Long dni);

    List<LicenciaDTO> obtenerLicenciasVigentesFiltradas(String nombre,
                                                        String apellido,
                                                        String grupoSanguineo,
                                                        String factorSanguineo,
                                                        Boolean esDonante);
}