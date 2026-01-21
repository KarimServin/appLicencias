package com.municipalidad.licencias.appLicencias.service;

import com.municipalidad.licencias.appLicencias.dto.EmisionLicenciaDTO;
import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import java.util.List;
import org.springframework.stereotype.Service;
import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.entities.Licencia;
import com.municipalidad.licencias.appLicencias.entities.Titular;
import java.time.LocalDate;

@Service
public interface LicenciaService {
  
    public boolean puedeEmitirLicencia(Titular titular, ClaseLicencia claseSolicitada, LocalDate hoy);
    public LicenciaDTO emitirLicencia(EmisionLicenciaDTO emisionLicenciaDTO);
    public Licencia renovarLicencia(Licencia licencia, String observaciones);
    public boolean estaVigente(ClaseLicencia clase, Titular titular);
    public int calcularVigencia(Titular titular);
    public int calcularCosto(ClaseLicencia clase, boolean esCopia, int vigencia);
    public List<LicenciaDTO> obtenerLicenciasExpiradas();
    public List<LicenciaDTO> obtenerLicenciasPorTitular(Long dni);
    public List<LicenciaDTO> obtenerLicenciasVigentesFiltradas(String nombre, 
                                                               String apellido, 
                                                               String grupoSanguineo, 
                                                               String factorSanguineo, 
                                                               Boolean esDonante);
}