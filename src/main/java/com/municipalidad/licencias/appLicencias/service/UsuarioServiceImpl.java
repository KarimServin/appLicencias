package com.municipalidad.licencias.appLicencias.service;
import com.municipalidad.licencias.appLicencias.dto.AltaUsuarioDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.exception.usuarioException.CredencialesInvalidasException;
import com.municipalidad.licencias.appLicencias.exception.usuarioException.UsuarioYaExisteException;
import com.municipalidad.licencias.appLicencias.mapper.AltaUsuarioMapper;
import com.municipalidad.licencias.appLicencias.entities.Role;
import org.springframework.stereotype.Service;
import com.municipalidad.licencias.appLicencias.entities.Usuario;
import com.municipalidad.licencias.appLicencias.repository.UsuarioRepository;
import com.municipalidad.licencias.appLicencias.security.PasswordHasher;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordHasher passwordHasher;
    private final AltaUsuarioMapper altaUsuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordHasher passwordHasher, AltaUsuarioMapper altaUsuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordHasher = passwordHasher;
        this.altaUsuarioMapper = altaUsuarioMapper;
    }
    
    @Override
    public Usuario buscarPorNombreUsuario(String nombreUsuario) {
        
        if (nombreUsuario == null) {
            return null;
        }
       
        return usuarioRepository.findByNombreUsuario(nombreUsuario).orElse(null);
    
    }

    
    @Override
    public void nuevoUsuario(AltaUsuarioDTO altaUsuarioDTO) throws ServiceException {
    
        //Normalizar nombre de usuario
        String nombreUsuarioNormalizado = altaUsuarioDTO.getNombreUsuario().trim();
        
        //nombreUsuario no puede ser nulo ni vacío
        if (nombreUsuarioNormalizado == null || nombreUsuarioNormalizado.isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no es válido.");
        }
        
        //nombreUsuario no puede estar en uso
        if (usuarioRepository.findByNombreUsuario(nombreUsuarioNormalizado).isPresent()) {
            throw new ServiceException("Ya existe un usuario con ese nombre");
        }

        char[] password = altaUsuarioDTO.getPassword();
        
        //Validar longitud de contraseña
        validarLongitud(password);
        
        String hash = passwordHasher.hash(password);
        
        Arrays.fill(password, ' ');
        
        Usuario usuario = altaUsuarioMapper.toEntity(altaUsuarioDTO);
        usuario.setNombreUsuario(nombreUsuarioNormalizado);
        usuario.setPasswordHash(hash);
 
        
        
        setRoles(usuario,altaUsuarioDTO.isAdmin());
 
        //guardar usuario en la BD
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
    public void actualizarUsuario(String nombreUsuarioActual, 
                                  String nuevoNombre, 
                                  char[] nuevaContrasenia, 
                                  boolean esSuperusuario) {
    
        Usuario usuario = obtenerUsuarioExistente(nombreUsuarioActual);
        

        //Si el nombre va a cambiar
        if(!nombreUsuarioActual.equals(nuevoNombre)) {
            actualizarNombre(usuario, nuevoNombre);
        }
        
        actualizarPasswordObligatoria(usuario, nuevaContrasenia);
        
        setRoles(usuario, esSuperusuario);

        usuarioRepository.save(usuario);
    
    }
    
    
    /* ===================== MÉTODOS AUXILIARES ======================== */

    private Usuario obtenerUsuarioExistente(String nombreUsuarioActual) {
    
        return usuarioRepository.findByNombreUsuario(nombreUsuarioActual)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

    }

    private void actualizarNombre(Usuario usuario, String nuevoNombre) {
    
        if (nuevoNombre == null) return;
        String normalizado = nuevoNombre.trim();
        if (normalizado.isEmpty()) return;
        if (!normalizado.equals(usuario.getNombreUsuario())) {
        // Verificar que no exista otro con ese nombre
            if (usuarioRepository.findByNombreUsuario(normalizado).isPresent()) {
                throw new UsuarioYaExisteException();
            }
   
            usuario.setNombreUsuario(normalizado);
        }
        
    }

    private void actualizarPasswordObligatoria(Usuario usuario, char[] nuevaContrasenia) {
    // Política actual: null NO permitido (cambiar implica proveer contraseña)
        if (nuevaContrasenia == null) {
            throw new IllegalArgumentException("La contraseña no puede ser null.");
        }

        if (nuevaContrasenia.length == 0 || esSoloEspacios(nuevaContrasenia)) {
            java.util.Arrays.fill(nuevaContrasenia, ' ');
        throw new IllegalArgumentException("La contraseña no puede estar vacía.");
        }

    
        validarLongitud(nuevaContrasenia);
  

        try {
            String nuevoHash = passwordHasher.hash(nuevaContrasenia);
            usuario.setPasswordHash(nuevoHash);
        } finally {
            java.util.Arrays.fill(nuevaContrasenia, ' '); // siempre limpiar
        }
    }

    private void setRoles(Usuario usuario, boolean esSuperusuario) {
         usuario.getRoles().clear();
         usuario.getRoles().add(Role.USER);
         if (esSuperusuario) {
            usuario.getRoles().add(Role.ADMIN);
        }
         
    }

    private boolean esSoloEspacios(char[] nuevaContrasenia) {
   
            for (char c : nuevaContrasenia) {
                if (!Character.isWhitespace(c)) return false;
            }
        return true;
    }
    
    private void validarLongitud(char[] nuevaContrasenia) {
    
        int longitud = nuevaContrasenia.length;
        
        if( longitud < 8 || longitud > 16) {
            throw new IllegalArgumentException("La contraseña debe tener entre 8 y 16 caracteres.");    
        } 
    }
    
}

    
    
    
    
    
   

