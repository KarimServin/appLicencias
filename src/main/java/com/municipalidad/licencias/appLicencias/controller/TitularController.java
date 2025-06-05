package com.municipalidad.licencias.appLicencias.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.service.TitularService;



@Controller
public class TitularController {
    
    private final TitularService titularService;

    public TitularController(TitularService titularService) {
        this.titularService = titularService;
    }

    
    public ResponseEntity<Titular> crearTitular( Titular titular) {
        Titular creado = titularService.crearTitular(titular);
        return ResponseEntity.ok(creado);
    }


    public ResponseEntity<Titular> buscarTitular( Long dni) {
        return titularService.buscarPorDni(dni)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
