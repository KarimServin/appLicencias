package com.municipalidad.licencias.appLicencias.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.municipalidad.licencias.appLicencias.model.Usuario;
import com.municipalidad.licencias.appLicencias.repository.UsuarioRepository;
import com.municipalidad.licencias.appLicencias.singleton.SesionUsuario;
import java.util.List;
import java.util.stream.Collectors;

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
    
    @Override
    public List<String> obtenerTodosLosNombresDeUsuario() {
    return usuarioRepository.findAll()
        .stream()
        .map(Usuario::getNombreUsuario)
        .collect(Collectors.toList());
    }
    
    @Override
    public void actualizarUsuario(String nombreUsuarioActual, String nuevoNombre, String nuevaContrasenia, boolean esSuperusuario) {
    
        Usuario usuarioActual = SesionUsuario.getUsuarioActual();
        if (usuarioActual == null || !usuarioActual.isEsSuperusuario()) {
        throw new RuntimeException("No tiene permisos para modificar usuarios.");
        }
        
        Usuario usuario = usuarioRepository.findByNombreUsuario(nombreUsuarioActual).orElse(null);
        usuario.setNombreUsuario(nuevoNombre);
        usuario.setContrasenia(nuevaContrasenia);
        usuario.setEsSuperusuario(esSuperusuario);
        usuarioRepository.save(usuario);
    
    }
    
}