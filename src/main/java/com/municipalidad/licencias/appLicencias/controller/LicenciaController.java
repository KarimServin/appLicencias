package com.municipalidad.licencias.appLicencias.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.municipalidad.licencias.appLicencias.model.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.model.Licencia;
import com.municipalidad.licencias.appLicencias.service.LicenciaService;

@RestController
@RequestMapping("/licencia")
public class LicenciaController {

    private final LicenciaService licenciaService;

    public LicenciaController(LicenciaService licenciaService) {
        this.licenciaService = licenciaService;
    }

     @GetMapping("puedeEmitir")
    public boolean puedeEmitir(@RequestParam Long dni, @RequestParam ClaseLicencia clase) {
        return licenciaService.puedeEmitirLicencia(dni, clase);
    }

    @PostMapping("/emitir")
    public Licencia emitirLicencia(@RequestParam Long dni, @RequestBody Licencia licencia) {
        return licenciaService.emitirLicencia(dni, licencia);
    }

}
