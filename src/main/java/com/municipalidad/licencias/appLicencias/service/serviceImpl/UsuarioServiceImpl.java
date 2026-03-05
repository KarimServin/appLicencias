package com.municipalidad.licencias.appLicencias.service.serviceImpl;

import com.municipalidad.licencias.appLicencias.modules.gestionarusuarios.ActualizarUsuarioRequestDTO;
import com.municipalidad.licencias.appLicencias.modules.altausuario.AltaUsuarioDTO;
import com.municipalidad.licencias.appLicencias.dto.UsuarioDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.exception.usuarioException.UsuarioYaExisteException;
import com.municipalidad.licencias.appLicencias.mapper.AltaUsuarioMapper;
import com.municipalidad.licencias.appLicencias.entities.Role;
import com.municipalidad.licencias.appLicencias.entities.Usuario;
import com.municipalidad.licencias.appLicencias.repository.UsuarioRepository;
import com.municipalidad.licencias.appLicencias.security.PasswordHasher;
import com.municipalidad.licencias.appLicencias.service.UsuarioService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordHasher passwordHasher;
    private final AltaUsuarioMapper altaUsuarioMapper;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                              PasswordHasher passwordHasher,
                              AltaUsuarioMapper altaUsuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordHasher = passwordHasher;
        this.altaUsuarioMapper = altaUsuarioMapper;
    }

    // ══════════════════════════════════════════════════════════════
    //  EXISTENTES (sin cambios)
    // ══════════════════════════════════════════════════════════════

    @Override
    public Usuario buscarPorNombreUsuario(String nombreUsuario) {
        if (nombreUsuario == null) return null;
        return usuarioRepository.findByNombreUsuario(nombreUsuario).orElse(null);
    }

    @Override
    public void nuevoUsuario(AltaUsuarioDTO altaUsuarioDTO) throws ServiceException {

        String nombreUsuarioNormalizado = altaUsuarioDTO.getNombreUsuario().trim();

        if (nombreUsuarioNormalizado.isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario no es válido.");
        }

        if (usuarioRepository.findByNombreUsuario(nombreUsuarioNormalizado).isPresent()) {
            throw new ServiceException("Ya existe un usuario con ese nombre");
        }

        char[] password = altaUsuarioDTO.getPassword();
        validarLongitud(password);

        String hash = passwordHasher.hash(password);
        Arrays.fill(password, ' ');

        Usuario usuario = altaUsuarioMapper.toEntity(altaUsuarioDTO);
        usuario.setNombreUsuario(nombreUsuarioNormalizado);
        usuario.setPasswordHash(hash);

        setRoles(usuario, altaUsuarioDTO.isAdmin());

        usuarioRepository.save(usuario);
    }

    // ══════════════════════════════════════════════════════════════
    //  REFACTORIZADOS
    // ══════════════════════════════════════════════════════════════

    @Override
    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public void actualizarUsuario(Long idUsuario, ActualizarUsuarioRequestDTO req) {
        if (idUsuario == null) {
            throw new IllegalArgumentException("El idUsuario no puede ser null.");
        }
        if (req == null) {
            throw new IllegalArgumentException("El request no puede ser null.");
        }

        Usuario usuario = obtenerUsuarioExistentePorId(idUsuario);

        // Nombre de usuario
        actualizarNombreUsuario(usuario, req.getNuevoNombreUsuario());

        // Nombre y apellido
        actualizarCampoTexto(req.getNombre(), usuario::setNombre);
        actualizarCampoTexto(req.getApellido(), usuario::setApellido);

        // Email (puede ser null = no cambiar, vacío = limpiar)
        if (req.getEmail() != null) {
            usuario.setEmail(req.getEmail().trim().isEmpty() ? null : req.getEmail().trim());
        }

        // Contraseña: opcional, si viene null o vacía → no se cambia
        actualizarPasswordOpcional(usuario, req.getNuevaContrasenia());

        // Roles: si viene null → no tocar
        if (req.getSuperusuario() != null) {
            setRoles(usuario, req.getSuperusuario());
        }

        usuarioRepository.save(usuario);
    }

    // ══════════════════════════════════════════════════════════════
    //  NUEVOS
    // ══════════════════════════════════════════════════════════════

    @Override
    public UsuarioDTO buscarPorId(Long usuarioId) {
        Usuario usuario = obtenerUsuarioExistentePorId(usuarioId);
        return toDTO(usuario);
    }

    @Override
    public void activar(Long usuarioId) {
        Usuario usuario = obtenerUsuarioExistentePorId(usuarioId);

        if (usuario.isActivo()) {
            throw new ServiceException("El usuario ya se encuentra activo.");
        }

        usuario.setActivo(true);
        usuarioRepository.save(usuario);
    }

    @Override
    public void desactivar(Long usuarioId) {
        Usuario usuario = obtenerUsuarioExistentePorId(usuarioId);

        if (!usuario.isActivo()) {
            throw new ServiceException("El usuario ya se encuentra inactivo.");
        }

        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }

    @Override
    public void concederPrivilegios(Long usuarioId) {
        Usuario usuario = obtenerUsuarioExistentePorId(usuarioId);

        if (usuario.esAdmin()) {
            throw new ServiceException("El usuario ya tiene privilegios de superusuario.");
        }

        usuario.getRoles().add(Role.ADMIN);
        usuarioRepository.save(usuario);
    }

    @Override
    public void quitarPrivilegios(Long usuarioId) {
        Usuario usuario = obtenerUsuarioExistentePorId(usuarioId);

        if (!usuario.esAdmin()) {
            throw new ServiceException("El usuario no tiene privilegios de superusuario.");
        }

        usuario.getRoles().remove(Role.ADMIN);
        usuarioRepository.save(usuario);
    }

    // ══════════════════════════════════════════════════════════════
    //  AUXILIARES
    // ══════════════════════════════════════════════════════════════

    private Usuario obtenerUsuarioExistentePorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new ServiceException("No existe el usuario (id=" + idUsuario + ")."));
    }

    private UsuarioDTO toDTO(Usuario u) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(u.getId());
        dto.setNombreUsuario(u.getNombreUsuario());
        dto.setNombre(u.getNombre());
        dto.setApellido(u.getApellido());
        dto.setEmail(u.getEmail());
        dto.setRoles(u.getRoles());
        dto.setActivo(u.isActivo());
        return dto;
    }

    private void actualizarNombreUsuario(Usuario usuario, String nuevoNombre) {
        if (nuevoNombre == null) return;
        String normalizado = nuevoNombre.trim();
        if (normalizado.isEmpty()) return;

        if (!normalizado.equals(usuario.getNombreUsuario())) {
            if (usuarioRepository.findByNombreUsuario(normalizado).isPresent()) {
                throw new UsuarioYaExisteException();
            }
            usuario.setNombreUsuario(normalizado);
        }
    }

    private void actualizarCampoTexto(String valor, java.util.function.Consumer<String> setter) {
        if (valor == null) return;
        String normalizado = valor.trim();
        if (!normalizado.isEmpty()) {
            setter.accept(normalizado);
        }
    }

    private void actualizarPasswordOpcional(Usuario usuario, char[] nuevaContrasenia) {
        // null o vacía → no cambiar
        if (nuevaContrasenia == null || nuevaContrasenia.length == 0) return;

        if (esSoloEspacios(nuevaContrasenia)) {
            Arrays.fill(nuevaContrasenia, ' ');
            return; // no cambiar
        }

        validarLongitud(nuevaContrasenia);

        try {
            String nuevoHash = passwordHasher.hash(nuevaContrasenia);
            usuario.setPasswordHash(nuevoHash);
        } finally {
            Arrays.fill(nuevaContrasenia, ' ');
        }
    }

    private void setRoles(Usuario usuario, boolean esSuperusuario) {
        usuario.getRoles().clear();
        usuario.getRoles().add(Role.USER);
        if (esSuperusuario) {
            usuario.getRoles().add(Role.ADMIN);
        }
    }

    private boolean esSoloEspacios(char[] chars) {
        for (char c : chars) {
            if (!Character.isWhitespace(c)) return false;
        }
        return true;
    }

    private void validarLongitud(char[] password) {
        int longitud = password.length;
        if (longitud < 8 || longitud > 16) {
            throw new IllegalArgumentException("La contraseña debe tener entre 8 y 16 caracteres.");
        }
    }
}