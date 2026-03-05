

package com.municipalidad.licencias.appLicencias.repository;

import com.municipalidad.licencias.appLicencias.entities.Titular;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TitularRepository extends JpaRepository<Titular, Long> {

    Optional<Titular> findByDni(Long dni);
    boolean existsByDni(Long dni);

    @Query("SELECT t FROM Titular t WHERE t.dni IN :dnis")
    List<Titular> findByDniIn(@Param("dnis") Set<Long> dnis);
    
}