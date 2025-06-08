

package com.municipalidad.licencias.appLicencias.repository;

import com.municipalidad.licencias.appLicencias.model.Titular;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitularRepository extends JpaRepository<Titular, Long> {}