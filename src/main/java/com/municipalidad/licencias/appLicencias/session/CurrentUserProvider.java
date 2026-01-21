package com.municipalidad.licencias.appLicencias.session;

import com.municipalidad.licencias.appLicencias.entities.Usuario;
import com.municipalidad.licencias.appLicencias.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserProvider {

    private final SessionInfo sessionInfo;
    private final UsuarioRepository usuarioRepository;

    public CurrentUserProvider(SessionInfo sessionInfo, UsuarioRepository usuarioRepository) {
        this.sessionInfo = sessionInfo;
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario getOrThrow() {
        String nombre = sessionInfo.getNombreUsuarioActual();
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalStateException("No hay usuario autenticado en sesión.");
        }

        Usuario u = usuarioRepository.findByNombreUsuario(nombre)
            .orElseThrow(() -> new IllegalStateException("Usuario en sesión no encontrado: " + nombre));

        if (!u.isActivo()) {
            throw new IllegalStateException("El usuario en sesión está inactivo.");
        }
  
        return u;
    }

    public boolean isAdmin() {
        return sessionInfo.esSuperusuario();
    }
    
}