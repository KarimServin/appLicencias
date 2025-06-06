package com.municipalidad.licencias.appLicencias.service;

import java.time.LocalDate;
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

    public Titular crearTitular(Long dni, String nombre, LocalDate fechaNacimiento,
                                 char grupoSanguineo, char factorSanguineo,
                                 boolean esDonante, boolean tuvoLicenciaProfesional,
                                 LocalDate fechaLicenciaClaseB, Long telefono, String email) {
        if (titularRepo.existsById(dni)) {
            throw new RuntimeException("Ya existe un titular con ese DNI.");
        }

        Titular titular = new Titular();
        titular.setId(dni);
        titular.setNombre(nombre);
        titular.setFechaNacimiento(fechaNacimiento);
        titular.setGrupoSanguineo(grupoSanguineo);
        titular.setFactorSanguineo(factorSanguineo);
        titular.setEsDonante(esDonante);
        titular.setTuvoLicenciaProfesional(tuvoLicenciaProfesional);
        titular.setFechaLicenciaClaseB(fechaLicenciaClaseB);
        titular.setTelefono(telefono);
        titular.setEmail(email);

        return titularRepo.save(titular);
    }

    public Optional<Titular> buscarPorDni(Long dni) {
        return titularRepo.findById(dni);
    }
}
