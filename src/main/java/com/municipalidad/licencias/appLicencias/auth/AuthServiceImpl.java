package com.municipalidad.licencias.appLicencias.auth;


import com.municipalidad.licencias.appLicencias.dto.UsuarioDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.mapper.UsuarioMapper;
import com.municipalidad.licencias.appLicencias.entities.Usuario;
import com.municipalidad.licencias.appLicencias.repository.UsuarioRepository;
import com.municipalidad.licencias.appLicencias.security.PasswordHasher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.municipalidad.licencias.appLicencias.dto.CredencialesDTO;

@Service
public class AuthServiceImpl implements AuthService {
    
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UsuarioRepository usuarioRepository;
    private final PasswordHasher passwordHasher;
    private final UsuarioMapper usuarioMapper;
    
    public AuthServiceImpl(UsuarioRepository usuarioRepository, 
                                     PasswordHasher passwordHasher,
                                     UsuarioMapper usuarioMapper) {

        this.usuarioRepository = usuarioRepository;
        this.passwordHasher = passwordHasher;
        this.usuarioMapper = usuarioMapper;
        
    }
    
    

    @Transactional(readOnly = true)
    @Override
    public UsuarioDTO login(CredencialesDTO credenciales) throws ServiceException {
        // Log de inicio de operación - nivel DEBUG
        logger.debug("Intento de login para usuario: {}", credenciales.getNombreUsuario());
        
        if (credenciales == null || 
            credenciales.getNombreUsuario() == null || 
            credenciales.getPassword() == null) {
            // Log de error de validación - nivel WARN
            logger.warn("Intento de login con credenciales incompletas");
            throw new ServiceException("Credenciales incompletas");
        }
        
        Usuario usuario = usuarioRepository.findByNombreUsuario(credenciales.getNombreUsuario())
            .orElseThrow(() -> {
                logger.warn("Usuario no encontrado en login: {}", credenciales.getNombreUsuario());
                return new ServiceException("Usuario o contraseña incorrectos");
            });
        
        if (!usuario.isActivo()) {
            logger.warn("Intento de login con cuenta inactiva: {}", credenciales.getNombreUsuario());
            throw new ServiceException("La cuenta ha sido desactivada");
        }
        
        if (!passwordHasher.matches(credenciales.getPassword(), usuario.getPasswordHash())) {
            // Log de contraseña incorrecta - nivel WARN
            logger.warn("Contraseña incorrecta para usuario: {}", credenciales.getNombreUsuario());
            throw new ServiceException("Usuario o contraseña incorrectos");
        }
        
        // Log de éxito - nivel INFO
        logger.info("Login exitoso para usuario: {} - Fecha: {}", 
                   credenciales.getNombreUsuario(), 
                   java.time.LocalDateTime.now());
        
        return usuarioMapper.toDTO(usuario);
        
    }
        
}
