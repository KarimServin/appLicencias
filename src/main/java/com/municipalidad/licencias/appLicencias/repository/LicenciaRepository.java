package com.municipalidad.licencias.appLicencias.repository;

import java.util.List;
import com.municipalidad.licencias.appLicencias.model.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.model.Licencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenciaRepository extends JpaRepository<Licencia, Long> {
    List<Licencia> findByTitularId(Long titularId);

    List<Licencia> findByTitularIdAndClase(Long titularId, ClaseLicencia b);

}
