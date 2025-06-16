package com.municipalidad.licencias.appLicencias.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.model.Usuario;
import com.municipalidad.licencias.appLicencias.repository.TitularRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TitularServiceImpl implements TitularService {
    @Autowired
    private final TitularRepository titularRepository;

    public TitularServiceImpl(TitularRepository titularRepo) {
        this.titularRepository = titularRepo;
    }
    
    
    @Override
    public Titular guardarTitular(Long dni, String nombre, LocalDate fechaNacimiento,
                                 char grupoSanguineo, char factorSanguineo,
                                 boolean esDonante, boolean tuvoLicenciaProfesional,
                                 LocalDate fechaLicenciaClaseB, Long telefono, String email, String direccion, Usuario usuario) {
        if (titularRepository.existsById(dni)) {
            throw new RuntimeException("Ya existe un titular con ese DNI.");
        }

        Titular titular = new Titular();
        titular.setDni(dni);
        titular.setNombre(nombre);
        titular.setFechaNacimiento(fechaNacimiento);
        titular.setGrupoSanguineo(grupoSanguineo);
        titular.setFactorSanguineo(factorSanguineo);
        titular.setEsDonante(esDonante);
        titular.setTuvoLicenciaProfesional(tuvoLicenciaProfesional);
        titular.setFechaLicenciaClaseB(fechaLicenciaClaseB);
        titular.setTelefono(telefono);
        titular.setEmail(email);
        titular.setDireccion(direccion);
        titular.setUsuario(usuario); 

        return titularRepository.save(titular);
    }

    @Override
    public Optional<Titular> buscarPorDni(Long dni) {
        return titularRepository.findById(dni);
    }
}
