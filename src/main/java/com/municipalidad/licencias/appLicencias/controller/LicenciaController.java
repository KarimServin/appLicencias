package com.municipalidad.licencias.appLicencias.controller;


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
    public int calcularCosto(Licencia licencia) {
        return licenciaService.calcularCosto(licencia);
    }

}
