package com.municipalidad.licencias.appLicencias.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.repository.TitularRepository;

@Service
public class TitularService {
    
    private final TitularRepository titularRepo;
    
    public TitularService(TitularRepository titularRepo) {
        this.titularRepo = titularRepo;
    }

    public Titular crearTitular(Titular titular) {
          if (titularRepo.existsById(titular.getId())) {
            throw new RuntimeException("Ya existe un titular con ese DNI.");
        }
        return titularRepo.save(titular);
    }

    public Optional<Titular> buscarPorDni(Long dni) {
        return titularRepo.findById(dni);
    }

}
