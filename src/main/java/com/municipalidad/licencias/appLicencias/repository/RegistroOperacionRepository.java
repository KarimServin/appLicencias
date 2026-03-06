package com.municipalidad.licencias.appLicencias.repository;

import com.municipalidad.licencias.appLicencias.entities.RegistroOperacion;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroOperacionRepository extends JpaRepository<RegistroOperacion, Long> {

    @Query("""
        SELECT r FROM RegistroOperacion r
        WHERE (:usuario IS NULL OR r.usuario = :usuario)
          AND (:tipo IS NULL OR r.tipoOperacion = :tipo)
          AND (:desde IS NULL OR r.fechaHora >= :desde)
          AND (:hasta IS NULL OR r.fechaHora <= :hasta)
        ORDER BY r.fechaHora DESC
    """)
    List<RegistroOperacion> buscarConFiltros(
            @Param("usuario") String usuario,
            @Param("tipo") String tipo,
            @Param("desde") LocalDateTime desde,
            @Param("hasta") LocalDateTime hasta);

    @Query("SELECT DISTINCT r.tipoOperacion FROM RegistroOperacion r ORDER BY r.tipoOperacion")
    List<String> findTiposDistintos();

    @Query("SELECT DISTINCT r.usuario FROM RegistroOperacion r ORDER BY r.usuario")
    List<String> findUsuariosDistintos();
}
