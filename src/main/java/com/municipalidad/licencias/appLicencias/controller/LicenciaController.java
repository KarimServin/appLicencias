package com.municipalidad.licencias.appLicencias.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.municipalidad.licencias.appLicencias.service.LicenciaService;

@RestController
public class LicenciaController {

    private final LicenciaService licenciaService;

    public LicenciaController(LicenciaService licenciaService) {
        this.licenciaService = licenciaService;
    }

    @GetMapping("/puedeEmitir")
    public boolean puedeEmitir(@RequestParam Long titularId, @RequestParam String clase) {
        return licenciaService.puedeEmitirLicencia(titularId, clase);
    }
}
