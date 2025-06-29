package com.municipalidad.licencias.appLicencias.controller;


import java.util.List;

import org.springframework.stereotype.Controller;
import com.municipalidad.licencias.appLicencias.model.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.model.Licencia;
import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.model.Usuario;
import com.municipalidad.licencias.appLicencias.service.LicenciaService;

@Controller
public class LicenciaController {

    private final LicenciaService licenciaService;

    public LicenciaController(LicenciaService licenciaService) {
        this.licenciaService = licenciaService;
    }

    public boolean puedeEmitir(Long dni,  ClaseLicencia clase) {
        return licenciaService.puedeEmitirLicencia(dni, clase);
    }
    
    public boolean poseeLicencia(ClaseLicencia clase,Titular titular){
        return licenciaService.estaVigente(clase, titular);
    }

    public Licencia emitirLicencia( Long dni, ClaseLicencia clase, String observaciones, Usuario usuario) {
        return licenciaService.emitirLicencia(dni, clase, observaciones, usuario);
    }
    
    public Licencia emitirLicencia(Licencia licencia, String observaciones, Usuario usuario) {
        return licenciaService.emitirLicencia(licencia, observaciones, usuario);
    }

    public int calcularCosto(Licencia licencia, boolean esCopia) {
        return licenciaService.calcularCosto(licencia, esCopia);
    }

    public Licencia renovarLicencia(Licencia licencia, String observaciones, Usuario usuario) {
        return licenciaService.renovarLicencia(licencia, observaciones, usuario);
    }

    public List<Licencia> obtenerLicenciasPorTitular(Long dni) {
        return licenciaService.obtenerLicenciasPorTitular(dni);
    }
    
    public List<Licencia> obtenerLicenciasExpiradas(){
        return licenciaService.obtenerLicenciasExpiradas();
    }

}
