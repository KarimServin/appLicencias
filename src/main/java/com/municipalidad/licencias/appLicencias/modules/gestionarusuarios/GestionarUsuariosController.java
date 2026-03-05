package com.municipalidad.licencias.appLicencias.modules.gestionarusuarios;

import com.municipalidad.licencias.appLicencias.dto.UsuarioDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.service.UsuarioService;
import com.municipalidad.licencias.appLicencias.viewforms.Dialogs;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GestionarUsuariosController {

    private static final Logger logger = LoggerFactory.getLogger(GestionarUsuariosController.class);

    private final UsuarioService usuarioService;
    private final EditarUsuarioController editarUsuarioController;
    private GestionarUsuariosView view;

    @Autowired
    public GestionarUsuariosController(UsuarioService usuarioService,
                                       EditarUsuarioController editarUsuarioController) {
        this.usuarioService = usuarioService;
        this.editarUsuarioController = editarUsuarioController;
    }

    public void display() {
        SwingUtilities.invokeLater(() -> {
            view = new GestionarUsuariosView();
            configurarListeners();
            cargarUsuariosAsync();
            view.setVisible(true);
        });
    }

    private void configurarListeners() {
        view.setEditarAction(e -> editarUsuario());
        view.setEstadoAction(e -> toggleEstado());
        view.setPrivilegiosAction(e -> togglePrivilegios());
        view.setCerrarAction(e -> view.dispose());
    }

    // ── Cargar tabla ──

    private void cargarUsuariosAsync() {
        view.setBotonesEnabled(false);
        new SwingWorker<List<UsuarioDTO>, Void>() {
            @Override
            protected List<UsuarioDTO> doInBackground() {
                return usuarioService.obtenerTodosLosUsuarios();
            }

            @Override
            protected void done() {
                try {
                    List<UsuarioDTO> usuarios = get();
                    if (usuarios == null) usuarios = Collections.emptyList();
                    view.cargarUsuarios(usuarios);
                } catch (Exception ex) {
                    logger.error("Error al cargar usuarios: {}", ex.getMessage(), ex);
                    view.cargarUsuarios(Collections.emptyList());
                    Dialogs.error(view, "Error al cargar usuarios: " + ex.getMessage());
                }
            }
        }.execute();
    }

    // ── Editar ──

    private void editarUsuario() {
        Long id = view.getUsuarioIdSeleccionado();
        if (id == null) {
            Dialogs.error(view, "Seleccione un usuario de la tabla.");
            return;
        }

        editarUsuarioController.display(id, () -> cargarUsuariosAsync());
    }

    // ── Activar / Desactivar ──

    private void toggleEstado() {
        Long id = view.getUsuarioIdSeleccionado();
        String nombre = view.getNombreUsuarioSeleccionado();
        String estado = view.getEstadoSeleccionado();

        if (id == null) {
            Dialogs.error(view, "Seleccione un usuario de la tabla.");
            return;
        }

        boolean esActivar = "Inactivo".equals(estado);

        String mensaje = esActivar
            ? "¿Está seguro que desea activar al usuario \"" + nombre + "\"?\n\n"
              + "El usuario podrá volver a iniciar sesión en el sistema."
            : "¿Está seguro que desea desactivar al usuario \"" + nombre + "\"?\n\n"
              + "El usuario no podrá iniciar sesión hasta que sea reactivado.";

        String titulo = esActivar ? "Confirmar activación" : "Confirmar desactivación";

        int opcion = JOptionPane.showConfirmDialog(
            view, mensaje, titulo,
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (opcion != JOptionPane.YES_OPTION) return;

        try {
            if (esActivar) {
                usuarioService.activar(id);
                logger.info("Usuario '{}' activado.", nombre);
                Dialogs.exito(view, "El usuario \"" + nombre + "\" fue activado correctamente.");
            } else {
                usuarioService.desactivar(id);
                logger.info("Usuario '{}' desactivado.", nombre);
                Dialogs.exito(view, "El usuario \"" + nombre + "\" fue desactivado correctamente.");
            }
            cargarUsuariosAsync();

        } catch (ServiceException e) {
            Dialogs.error(view, e.getMessage());
        } catch (Exception e) {
            logger.error("Error al cambiar estado: {}", e.getMessage(), e);
            Dialogs.error(view, "Error inesperado: " + e.getMessage());
        }
    }

    // ── Cambiar privilegios ──

    private void togglePrivilegios() {
        Long id = view.getUsuarioIdSeleccionado();
        String nombre = view.getNombreUsuarioSeleccionado();
        String rol = view.getRolSeleccionado();

        if (id == null) {
            Dialogs.error(view, "Seleccione un usuario de la tabla.");
            return;
        }

        boolean esAdmin = "ADMIN".equals(rol);

        String mensaje = esAdmin
            ? "¿Está seguro que desea quitar privilegios de superusuario a \"" + nombre + "\"?\n\n"
              + "El usuario pasará a tener rol de usuario administrativo."
            : "¿Está seguro que desea conceder privilegios de superusuario a \"" + nombre + "\"?\n\n"
              + "El usuario tendrá acceso completo al sistema.";

        String titulo = esAdmin ? "Confirmar quitar privilegios" : "Confirmar conceder privilegios";

        int opcion = JOptionPane.showConfirmDialog(
            view, mensaje, titulo,
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );

        if (opcion != JOptionPane.YES_OPTION) return;

        try {
            if (esAdmin) {
                usuarioService.quitarPrivilegios(id);
                logger.info("Privilegios de superusuario quitados a '{}'.", nombre);
                Dialogs.exito(view, "Se quitaron los privilegios de superusuario a \"" + nombre + "\".");
            } else {
                usuarioService.concederPrivilegios(id);
                logger.info("Privilegios de superusuario concedidos a '{}'.", nombre);
                Dialogs.exito(view, "Se concedieron privilegios de superusuario a \"" + nombre + "\".");
            }
            cargarUsuariosAsync();

        } catch (ServiceException e) {
            Dialogs.error(view, e.getMessage());
        } catch (Exception e) {
            logger.error("Error al cambiar privilegios: {}", e.getMessage(), e);
            Dialogs.error(view, "Error inesperado: " + e.getMessage());
        }
    }
}
