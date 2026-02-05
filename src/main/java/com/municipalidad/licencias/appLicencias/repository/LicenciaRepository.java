package com.municipalidad.licencias.appLicencias.repository;

import java.util.List;
import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.entities.Licencia;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenciaRepository extends JpaRepository<Licencia, Long> {

    /**
     * Útil cuando vas a mapear a DTO incluyendo clases (evita N+1 / Lazy issues).
     */
    @Query("""
        select distinct l
        from Licencia l
        left join fetch l.clases
        where l.titular.dni = :dni
        order by l.fechaEmision desc
    """)
    List<Licencia> findByTitularDniFetchClases(@Param("dni") Long dni);

    /**
     * Versión simple (sin fetch). Usala solo si no vas a tocar l.getClases().
     */
    List<Licencia> findByTitularDni(Long titularDni);

    List<Licencia> findByFechaVencimientoLessThanEqual(LocalDate now);

    Optional<Licencia> findTopByTitularDniOrderByFechaEmisionDesc(Long dni);

    /**
     * Traer licencias de un titular que incluyan una clase X.
     * Nota: no hace fetch de clases; si vas a mapear clases, agregá una variante fetch.
     */
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

    /**
     * Variante con fetch para mapear a DTO con clases sin N+1.
     */
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

    /**
     * Verifica vigencia por clase (join con tabla puente).
     */
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

    /**
     * Licencias vigentes filtradas (con fetch de clases para mapear a DTO sin N+1).
     */
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
            @Param("esDonante") Boolean esDonante
    );

    /**
     * Licencias expiradas (si vas a mapear clases, conviene fetch).
     */
    @Query("""
        select distinct l
        from Licencia l
        left join fetch l.clases
        where l.fechaVencimiento <= :hoy
        order by l.fechaVencimiento desc
    """)
    List<Licencia> findExpiradasFetchClases(@Param("hoy") LocalDate hoy);
}