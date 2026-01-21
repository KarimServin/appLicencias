package com.municipalidad.licencias.appLicencias.repository;

import java.util.List;
import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.entities.Licencia;
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
    AND (:apellido IS NULL OR LOWER(l.titular.apellido) LIKE LOWER(CONCAT('%', :apellido, '%')))
    AND (:grupoSanguineo IS NULL OR l.titular.tipoSangre.grupo = :grupoSanguineo)
    AND (:factorSanguineo IS NULL OR l.titular.tipoSangre.factor = :factorSanguineo)
    AND (:esDonante IS NULL OR l.titular.esDonante = :esDonante)
    """)
List<Licencia> findLicenciasVigentesFiltradas(
    @Param("nombre") String nombre,
    @Param("apellido") String apellido,
    @Param("grupoSanguineo") String grupoSanguineo,
    @Param("factorSanguineo") String factorSanguineo,
    @Param("esDonante") Boolean esDonante
);

}
