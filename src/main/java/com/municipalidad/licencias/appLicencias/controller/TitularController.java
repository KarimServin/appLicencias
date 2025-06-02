package com.municipalidad.licencias.appLicencias.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.service.TitularService;



@RestController
@RequestMapping("/titular")
public class TitularController {
    
    private final TitularService titularService;

    public TitularController(TitularService titularService) {
        this.titularService = titularService;
    }

    @PostMapping("/crearTitular")
    public ResponseEntity<Titular> crearTitular(@RequestBody Titular titular) {
        Titular creado = titularService.crearTitular(titular);
        return ResponseEntity.ok(creado);
    }

     @GetMapping("/{dni}")
    public ResponseEntity<Titular> buscarTitular(@PathVariable Long dni) {
        return titularService.buscarPorDni(dni)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
