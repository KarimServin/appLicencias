package com.municipalidad.licencias.appLicencias.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.municipalidad.licencias.appLicencias.model.Usuario;
import com.municipalidad.licencias.appLicencias.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private final UsuarioRepository usuarioRepository;

        public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    @Override
    public Usuario buscarPorNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario).orElse(null);
    }

    @Override
    public Usuario validarCredenciales(String nombreUsuario, String contrasenia) {
    Usuario usuario = buscarPorNombreUsuario(nombreUsuario);
    if (usuario != null && usuario.getContrasenia().equals(contrasenia)) {
        return usuario;
    }
    return null;
}
    @Override
    public void guardarUsuario(String nombreUsuario, String contrasenia, boolean esSuperusuario){
    Usuario usuario= new Usuario();
    usuario.setNombreUsuario(nombreUsuario);
    usuario.setContrasenia(contrasenia);
    usuario.setEsSuperusuario(esSuperusuario);
    usuarioRepository.save(usuario);
    }
}