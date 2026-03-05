package com.municipalidad.licencias.appLicencias.repository;

import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.entities.CostoLicencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CostoLicenciaRepository extends JpaRepository<CostoLicencia, Long> {

    Optional<CostoLicencia> findByClaseLicenciaAndVigenciaAndEsCopia(
        ClaseLicencia clase, Integer vigencia, Boolean esCopia);

    List<CostoLicencia> findByEsCopiaTrue();

    List<CostoLicencia> findAllByOrderByClaseLicenciaAscVigenciaDesc();
}