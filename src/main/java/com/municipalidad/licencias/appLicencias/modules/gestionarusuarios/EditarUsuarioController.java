package com.municipalidad.licencias.appLicencias.modules.gestionarusuarios;


import com.municipalidad.licencias.appLicencias.dto.UsuarioDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.service.UsuarioService;
import com.municipalidad.licencias.appLicencias.viewforms.Dialogs;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EditarUsuarioController {

    private static final Logger logger = LoggerFactory.getLogger(EditarUsuarioController.class);

    private final UsuarioService usuarioService;

    private EditarUsuarioView view;
    private Long usuarioId;
    private Runnable onGuardadoExitoso;

    @Autowired
    public EditarUsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void display(Long usuarioId, Runnable onGuardadoExitoso) {
        this.usuarioId = usuarioId;
        this.onGuardadoExitoso = onGuardadoExitoso;

        SwingUtilities.invokeLater(() -> {
            view = new EditarUsuarioView();
            cargarDatosUsuario();
            configurarListeners();
            view.setVisible(true);
        });
    }

    private void cargarDatosUsuario() {
        try {
            UsuarioDTO usuario = usuarioService.buscarPorId(usuarioId);
            view.setNombreUsuario(usuario.getNombreUsuario());
            view.setNombre(usuario.getNombre());
            view.setApellido(usuario.getApellido());
            view.setEmail(usuario.getEmail());
        } catch (ServiceException e) {
            Dialogs.error(view, e.getMessage());
            view.dispose();
        }
    }

    private void configurarListeners() {
        view.setEditarAction(e -> editar());
        view.setCancelarAction(e -> view.dispose());
    }

    private void editar() {
        if (!view.validarCampos()) {
            return;
        }

        // Confirmación antes de guardar
        int opcion = JOptionPane.showConfirmDialog(
            view,
            "¿Está seguro que desea guardar los cambios?",
            "Confirmar edición",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (opcion != JOptionPane.YES_OPTION) return;

        try {
            char[] pass = view.getPassword();
            String password = new String(pass).trim();

            ActualizarUsuarioRequestDTO dto = new ActualizarUsuarioRequestDTO();
            dto.setNuevoNombreUsuario(view.getNombreUsuario());
            dto.setNombre(view.getNombre());
            dto.setApellido(view.getApellido());
            dto.setEmail(view.getEmail().isEmpty() ? null : view.getEmail());
            dto.setNuevaContrasenia(password.isEmpty() ? null : password.toCharArray());

            view.setEditarEnabled(false);

            usuarioService.actualizarUsuario(usuarioId, dto);

            logger.info("Usuario ID {} editado correctamente.", usuarioId);
            Dialogs.exito(view, "Usuario editado correctamente.");
            view.dispose();

            if (onGuardadoExitoso != null) {
                onGuardadoExitoso.run();
            }

        } catch (ServiceException e) {
            logger.warn("Error al editar usuario: {}", e.getMessage());
            Dialogs.error(view, e.getMessage());
            view.setEditarEnabled(true);
        } catch (Exception e) {
            logger.error("Error inesperado al editar usuario: {}", e.getMessage(), e);
            Dialogs.error(view, "Error inesperado: " + e.getMessage());
            view.setEditarEnabled(true);
        }
    }
}