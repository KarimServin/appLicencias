package com.municipalidad.licencias.appLicencias.repository;

import java.util.List;
import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.entities.Licencia;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenciaRepository extends JpaRepository<Licencia, Long> {

    @Query("""
        select distinct l
        from Licencia l
        left join fetch l.clases
        where l.titular.dni = :dni
        order by l.fechaEmision desc
    """)
    List<Licencia> findByTitularDniFetchClases(@Param("dni") Long dni);

    List<Licencia> findByTitularDni(Long titularDni);

    List<Licencia> findByFechaVencimientoLessThanEqual(LocalDate now);

    // Sin fetch — solo para referencia interna en emitirLicencia()
    Optional<Licencia> findTopByTitularDniOrderByFechaEmisionDesc(Long dni);

    // Con fetch — para mapear a DTO
    @Query("""
        SELECT l FROM Licencia l
        LEFT JOIN FETCH l.clases
        WHERE l.titular.dni = :dni
        ORDER BY l.fechaEmision DESC
        LIMIT 1
    """)
    Optional<Licencia> findTopByTitularDniFetchClasesOrderByFechaEmisionDesc(@Param("dni") Long dni);

    @Query("""
        select distinct l
        from Licencia l
        join l.clases lc
        where l.titular.dni = :dni
          and lc.claseLicencia = :clase
        order by l.fechaEmision desc
    """)
    List<Licencia> findByTitularDniAndClase(@Param("dni") Long dni,
                                            @Param("clase") ClaseLicencia clase);

    @Query("""
        select distinct l
        from Licencia l
        join fetch l.clases lc
        where l.titular.dni = :dni
          and lc.claseLicencia = :clase
        order by l.fechaEmision desc
    """)
    List<Licencia> findByTitularDniAndClaseFetchClases(@Param("dni") Long dni,
                                                       @Param("clase") ClaseLicencia clase);

    @Query("""
        select (count(l) > 0)
        from Licencia l
        join l.clases lc
        where l.titular.dni = :dni
          and lc.claseLicencia = :clase
          and l.fechaVencimiento > :hoy
    """)
    boolean existsVigenteByTitularDniAndClase(@Param("dni") Long dni,
                                              @Param("clase") ClaseLicencia clase,
                                              @Param("hoy") LocalDate hoy);

    @Query("""
        select distinct l
        from Licencia l
        left join fetch l.clases
        where l.fechaVencimiento > CURRENT_DATE
          and (:nombre IS NULL OR LOWER(l.titular.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')))
          and (:apellido IS NULL OR LOWER(l.titular.apellido) LIKE LOWER(CONCAT('%', :apellido, '%')))
          and (:grupoSanguineo IS NULL OR l.titular.tipoSangre.grupo = :grupoSanguineo)
          and (:factorSanguineo IS NULL OR l.titular.tipoSangre.factor = :factorSanguineo)
          and (:esDonante IS NULL OR l.titular.esDonante = :esDonante)
        order by l.fechaEmision desc
    """)
    List<Licencia> findLicenciasVigentesFiltradas(
            @Param("nombre") String nombre,
            @Param("apellido") String apellido,
            @Param("grupoSanguineo") String grupoSanguineo,
            @Param("factorSanguineo") String factorSanguineo,
            @Param("esDonante") Boolean esDonante);

    @Query("""
        select distinct l
        from Licencia l
        left join fetch l.clases
        where l.fechaVencimiento <= :hoy
        order by l.fechaVencimiento desc
    """)
    List<Licencia> findExpiradasFetchClases(@Param("hoy") LocalDate hoy);

    @Query("""
        SELECT MIN(l.fechaEmision) FROM Licencia l
        JOIN l.clases lc
        WHERE l.titular.dni = :dni
          AND lc.claseLicencia = :clase
    """)
    Optional<LocalDate> findFechaEmisionMasAntiguaByTitularDniAndClase(
            @Param("dni") Long dni,
            @Param("clase") ClaseLicencia clase);
    
    List<Licencia> findByTitularDniAndVigenteTrue(Long dni);
    
    //obtener licencias con titular y usuario en UNA sola query
    @Query("""
        SELECT DISTINCT l
        FROM Licencia l
        LEFT JOIN FETCH l.clases
        LEFT JOIN FETCH l.titular
        LEFT JOIN FETCH l.usuario
        WHERE l.id IN :ids
    """)
    List<Licencia> findByIdInFetchAll(@Param("ids") Set<Long> ids);
    
}