package com.municipalidad.licencias.appLicencias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.municipalidad.licencias.appLicencias.model.Licencia;

public interface LicenciaRepository extends JpaRepository<Licencia, Long> {
    List<Licencia> findByTitularId(Long titularId);

    List<Licencia> findByTitularIdAndClase(Long titularId, String clase);

}
