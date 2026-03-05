package com.municipalidad.licencias.appLicencias.repository;

import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.entities.CostoClaseLicencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CostoLicenciaRepository extends JpaRepository<CostoClaseLicencia, Long> {

    Optional<CostoClaseLicencia> findByClaseLicenciaAndVigenciaAndEsCopia(
        ClaseLicencia clase, Integer vigencia, Boolean esCopia);

    List<CostoClaseLicencia> findByEsCopiaTrue();

    List<CostoClaseLicencia> findAllByOrderByClaseLicenciaAscVigenciaDesc();
}