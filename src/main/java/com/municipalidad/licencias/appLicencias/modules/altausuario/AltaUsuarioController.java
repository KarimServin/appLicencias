package com.municipalidad.licencias.appLicencias.modules.altausuario;


import com.municipalidad.licencias.appLicencias.events.OperacionEvent;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.service.UsuarioService;
import com.municipalidad.licencias.appLicencias.session.SessionInfo;
import com.municipalidad.licencias.appLicencias.viewforms.Dialogs;
import javax.swing.SwingUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AltaUsuarioController {

    private final UsuarioService usuarioService;
    private final ApplicationEventPublisher eventPublisher;
    private final SessionInfo sessionInfo;
    private AltaUsuarioView view;

    @Autowired
    public AltaUsuarioController(UsuarioService usuarioService,
                                 ApplicationEventPublisher eventPublisher,
                                 SessionInfo sessionInfo) {
        this.usuarioService = usuarioService;
        this.eventPublisher = eventPublisher;
        this.sessionInfo = sessionInfo;
    }

    public void display() {
        SwingUtilities.invokeLater(() -> {
            view = new AltaUsuarioView();
            configurarListeners();
            view.setVisible(true);
        });
    }

    private void configurarListeners() {
        view.setAceptarAction(e -> crearUsuario());
        view.setCancelarAction(e -> cancelar());
    }

    private void crearUsuario() {
        if (!view.validarCampos()) {
            return;
        }

        try {
            AltaUsuarioDTO altaUsuarioDTO = new AltaUsuarioDTO(
                view.getNombreUsuario(),
                view.getNombre(),
                view.getApellido(),
                view.getEmail(),
                view.getPassword(),
                view.isAdmin()
            );

            usuarioService.nuevoUsuario(altaUsuarioDTO);

            eventPublisher.publishEvent(new OperacionEvent(this,
                sessionInfo.getNombreUsuarioActual(),
                "ALTA_USUARIO",
                "Usuario creado: " + altaUsuarioDTO.getNombreUsuario()));

            Dialogs.exito(view, "Alta de usuario exitosa.");
            view.dispose();

        } catch (ServiceException e) {
            Dialogs.error(view, e.getMessage());
        } catch (Exception e) {
            Dialogs.error(view, "Ocurrió un error inesperado: " + e.getMessage());
        }
    }

    private void cancelar() {
        view.dispose();
    }
}