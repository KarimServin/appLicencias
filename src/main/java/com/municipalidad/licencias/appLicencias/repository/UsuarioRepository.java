
package com.municipalidad.licencias.appLicencias.repository;

import com.municipalidad.licencias.appLicencias.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 *
 * @author karim
 */

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // busca un usuario por nombre
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
