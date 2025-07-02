package com.municipalidad.licencias.appLicencias.repository;

import java.util.List;
import com.municipalidad.licencias.appLicencias.model.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.model.Licencia;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenciaRepository extends JpaRepository<Licencia, Long> {
    List<Licencia> findByTitularDni(Long titularDni);

    List<Licencia> findByTitularDniAndClaseLicencia(Long titularDni, ClaseLicencia claseLicencia);

    boolean existsByClaseLicenciaAndTitularDni(ClaseLicencia claseLicencia, Long titularDni);
    
    List<Licencia> findByFechaVencimientoLessThanEqual(LocalDate now);
    
    @Query("""
    SELECT l FROM Licencia l
    WHERE l.fechaVencimiento > CURRENT_DATE
    AND (:nombre IS NULL OR LOWER(l.titular.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')))
    AND (:grupoCompleto IS NULL OR CONCAT(l.titular.grupoSanguineo, l.titular.factorSanguineo) = :grupoCompleto)
    AND (:esDonante IS NULL OR l.titular.esDonante = :esDonante)
""")
List<Licencia> findLicenciasVigentesFiltradas(
    @Param("nombre") String nombre,
    @Param("grupoCompleto") String grupoCompleto,
    @Param("esDonante") Boolean esDonante
);


}
