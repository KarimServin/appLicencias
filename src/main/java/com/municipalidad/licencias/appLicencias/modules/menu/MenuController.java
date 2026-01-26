package com.municipalidad.licencias.appLicencias.modules.menu;

import com.municipalidad.licencias.appLicencias.modules.altatitular.AltaTitularController;
import com.municipalidad.licencias.appLicencias.modules.altausuario.AltaUsuarioController;
import com.municipalidad.licencias.appLicencias.modules.emitirlicencia.EmitirLicenciaController;
import com.municipalidad.licencias.appLicencias.controller.ListarLicenciasExpiradasController;
import com.municipalidad.licencias.appLicencias.modules.modificartitular.ModificarTitularController;
import com.municipalidad.licencias.appLicencias.modules.modificarusuario.ModificarUsuarioController;
import com.municipalidad.licencias.appLicencias.factory.ControllerFactory;
import com.municipalidad.licencias.appLicencias.modules.emitircopialicencia.EmitirCopiaLicenciaController;
import com.municipalidad.licencias.appLicencias.modules.listarlicenciasvigentes.ListarLicenciasVigentesController;
import com.municipalidad.licencias.appLicencias.modules.renovarlicencia.RenovarLicenciaController;
import com.municipalidad.licencias.appLicencias.session.SessionController;
import javax.swing.SwingUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MenuController {
    
    
    private final SessionController sessionController;
    private final ControllerFactory controllerFactory;
    
    private MenuView menuView;
    
    
    @Autowired
    public MenuController(SessionController sessionController, ControllerFactory controllerFactory) {
        this.sessionController = sessionController;
        this.controllerFactory = controllerFactory;
       
    }
    
    public void display() {
        SwingUtilities.invokeLater(() -> {
            this.menuView = new MenuView();
        
            menuView.setLabelBienvenida(sessionController.getNombreUsuarioActual());
            verificarPermisos();
            setListeners();
            menuView.setVisible(true);     
        });
    }
    
    private void verificarPermisos() {
        if(!sessionController.esSuperusuario()) {
            menuView.ocultarBotones();
        } 
    }
    
    private void setListeners() {
    
            menuView.setEmitirNuevaLicenciaAction(e -> mostrarPantallaEmitirLicencia());
            menuView.setEmitirCopiaLicenciaAction(e -> mostrarPantallaEmitirCopiaLicencia());
            menuView.setAltaTitularAction(e -> mostrarPantallaAltaTitular());
            menuView.setListarLicenciasVigentesAction(e -> mostrarPantallaLicenciasVigentes());
            menuView.setRenovarLicenciaAction(e -> mostrarPantallaRenovarLicencia());
            menuView.setModificarDatosTitularAction(e -> mostrarPantallaModificarDatosTitular());
            menuView.setLicExpiradasAction(e -> mostrarPantallaLicenciasExpiradas());
            menuView.setAltaUsuarioAction(e -> mostrarPantallaAltaUsuario());
            menuView.setModificarDatosUsuarioAction(e -> mostrarPantallaModificarUsuario());
            menuView.setSalirAction(e -> salir());
        
    }
    
    private void mostrarPantallaEmitirLicencia() {
        EmitirLicenciaController controller = controllerFactory.createEmitirLicenciaController();
        controller.display();
    }

    private void mostrarPantallaAltaTitular() {
        AltaTitularController controller = controllerFactory.createAltaTitularController();
        controller.display();
    }

    private void mostrarPantallaLicenciasVigentes() {
        ListarLicenciasVigentesController controller = controllerFactory.createListarLicenciasVigentesController();
        controller.display();
    }

    private void mostrarPantallaLicenciasExpiradas() {
        ListarLicenciasExpiradasController controller = controllerFactory.createListarLicenciasExpiradasController();
        controller.display();
    }

    private void mostrarPantallaModificarDatosTitular() {
        ModificarTitularController controller = controllerFactory.createModificarTitularController();
        controller.display();
    }

    private void mostrarPantallaAltaUsuario() {
        AltaUsuarioController controller = controllerFactory.createAltaUsuarioController();
        controller.display();
    }

    private void mostrarPantallaModificarUsuario() {
        ModificarUsuarioController controller = controllerFactory.createModificarUsuarioController();
        controller.display();
    }

    private void mostrarPantallaEmitirCopiaLicencia() {
        EmitirCopiaLicenciaController controller = controllerFactory.createEmitirCopiaLicenciaController();
        controller.display();
    }

    private void mostrarPantallaRenovarLicencia() {
        RenovarLicenciaController controller = controllerFactory.createRenovarLicenciaController();
        controller.display();
    }

    private void salir() {
        sessionController.logout();
        System.exit(0);
    }
    

}