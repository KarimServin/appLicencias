
package com.municipalidad.licencias.appLicencias.repository;

import com.municipalidad.licencias.appLicencias.model.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // busca un usuario por nombre
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    
    List<Usuario> findAll();
    
}
