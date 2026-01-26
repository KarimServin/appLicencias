package com.municipalidad.licencias.appLicencias.modules.modificarusuario;

import com.municipalidad.licencias.appLicencias.dto.ActualizarUsuarioRequestDTO;
import com.municipalidad.licencias.appLicencias.dto.UsuarioDTO;
import com.municipalidad.licencias.appLicencias.service.UsuarioService;
import com.municipalidad.licencias.appLicencias.view.Dialogs;
import java.util.Collections;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.springframework.stereotype.Component;

@Component
public class ModificarUsuarioController {

    private final UsuarioService usuarioService;
    private ModificarUsuarioView view;

    public ModificarUsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public void display() {
        SwingUtilities.invokeLater(() -> {
            view = new ModificarUsuarioView();

            setListeners();
            cargarUsuariosAsync();   // ✅ con SwingWorker

            view.setVisible(true);
        });
    }

    private void setListeners() {
        view.setConfimarAction(e -> confirmar());
        view.setCancelarAction(e -> cancelar());
    }

    private void confirmar() {
            UsuarioDTO seleccionado = view.getUsuarioSeleccionado();

            if (seleccionado == null) {
                Dialogs.error(view,"Seleccione un usuario.");
                return;
            }

            Long idUsuario = seleccionado.getId();
            if (idUsuario == null) {
                Dialogs.error(view,"El usuario seleccionado no tiene ID.");
                return;
            }

            boolean mantenerNombre = view.isMantenerNombreUsuario();

            String nuevoNombre = null;
            if (!mantenerNombre) {
                nuevoNombre = view.getNuevoNombreUsuario().trim();
                if (nuevoNombre.isBlank()) {
                    Dialogs.error(view,"Ingrese un nuevo nombre o marque 'Mantener nombre de usuario actual'.");
                    return;
                }
        }

        char[] nuevaContrasenia = view.getNuevaContrasenia(); // tu service valida obligatoria
        boolean esSuperusuario = view.isSuperusuario();

        ActualizarUsuarioRequestDTO req = new ActualizarUsuarioRequestDTO();
        req.setNuevoNombreUsuario(nuevoNombre);            // null => no cambia
        req.setNuevaContrasenia(nuevaContrasenia);         // validación en service
        req.setSuperusuario(esSuperusuario);               // si tu DTO usa Boolean, esto autoboxea

        try {
            view.setConfirmarEnabled(false);

            usuarioService.actualizarUsuario(idUsuario, req);

            Dialogs.exito(view,"Usuario actualizado correctamente.");
            view.dispose();

        } catch (Exception ex) {
            Dialogs.error(view,"Error al actualizar usuario: " + ex.getMessage());
            view.setConfirmarEnabled(true);

        } finally {
            if (nuevaContrasenia != null) {
                java.util.Arrays.fill(nuevaContrasenia, '\0');
            }
        }
    }


    private void cancelar() {
        view.dispose();
    }

    private void cargarUsuariosAsync() {
        // (opcional) deshabilitar botones mientras carga
        view.setConfirmarEnabled(false);
        new SwingWorker<List<UsuarioDTO>, Void>() {
            @Override
            protected List<UsuarioDTO> doInBackground() {
                return usuarioService.obtenerTodosLosUsuarios(); // BD
            }
            @Override
            protected void done() {
                try {
                    List<UsuarioDTO> usuarios = get();
                    if (usuarios == null) usuarios = Collections.emptyList();

                    view.cargarUsuariosEnLista(usuarios);
                    view.setConfirmarEnabled(true);

                } catch (Exception ex) {
                    view.cargarUsuariosEnLista(Collections.emptyList());
                    view.setConfirmarEnabled(false);
                    Dialogs.error(view,"Error cargando usuarios: " + ex.getMessage());
                }
            }
        }.execute();
    }
}
