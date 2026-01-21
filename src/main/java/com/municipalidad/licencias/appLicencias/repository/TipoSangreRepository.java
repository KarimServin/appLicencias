package com.municipalidad.licencias.appLicencias.repository;

import com.municipalidad.licencias.appLicencias.entities.TipoSangre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoSangreRepository extends JpaRepository<TipoSangre, Long> {

    Optional<TipoSangre> findByGrupoAndFactor(String grupo, String factor);

}
