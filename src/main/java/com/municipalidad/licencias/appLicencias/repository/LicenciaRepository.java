package com.municipalidad.licencias.appLicencias.repository;

import java.util.List;
import com.municipalidad.licencias.appLicencias.model.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.model.Licencia;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenciaRepository extends JpaRepository<Licencia, Long> {
    List<Licencia> findByTitularDni(Long titularDni);

    List<Licencia> findByTitularDniAndClaseLicencia(Long titularDni, ClaseLicencia claseLicencia);

    boolean existsByClaseLicenciaAndTitularDni(ClaseLicencia claseLicencia, Long titularDni);
    
    List<Licencia> findByFechaVencimiento(LocalDate now);

}
