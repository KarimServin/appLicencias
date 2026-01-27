package com.municipalidad.licencias.appLicencias.factory;

import com.municipalidad.licencias.appLicencias.modules.altatitular.AltaTitularController;
import com.municipalidad.licencias.appLicencias.modules.altausuario.AltaUsuarioController;
import com.municipalidad.licencias.appLicencias.modules.emitirlicencia.EmitirLicenciaController;
import com.municipalidad.licencias.appLicencias.controller.ListarLicenciasExpiradasController;
import com.municipalidad.licencias.appLicencias.modules.emitircopialicencia.EmitirCopiaLicenciaController;
import com.municipalidad.licencias.appLicencias.modules.listarlicenciasvigentes.ListarLicenciasVigentesController;
import com.municipalidad.licencias.appLicencias.modules.modificartitular.ModificarTitularController;
import com.municipalidad.licencias.appLicencias.session.SessionController;
import com.municipalidad.licencias.appLicencias.modules.modificarusuario.ModificarUsuarioController;
import com.municipalidad.licencias.appLicencias.modules.renovarlicencia.RenovarLicenciaController;
import com.municipalidad.licencias.appLicencias.service.*;
import com.municipalidad.licencias.appLicencias.session.SessionInfo;
import com.municipalidad.licencias.appLicencias.validation.TitularValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



//Crea los controladores de pantallas y les pasa los servicios

@Component
public class ControllerFactory {
    
    private final LicenciaService licenciaService;
    private final TitularService titularService;
    private final UsuarioService usuarioService;
    private final TitularValidator titularValidator;
    
    @Autowired
    public ControllerFactory (
            LicenciaService licenciaService,
            TitularService titularService,
            UsuarioService usuarioService,
            SessionController sessionController,
            SessionInfo sessionInfo,
            TitularValidator titularValidator) {
        
        this.licenciaService = licenciaService;
        this.titularService = titularService;
        this.usuarioService = usuarioService;
        this.titularValidator = titularValidator;
    }
    
   

    public AltaUsuarioController createAltaUsuarioController() {
        return new AltaUsuarioController(usuarioService);
    }
    
    public AltaTitularController createAltaTitularController() {
        return new AltaTitularController(titularService,titularValidator);
    }

    public EmitirLicenciaController createEmitirLicenciaController() {
        return new EmitirLicenciaController(titularService, licenciaService);
    } 


    public ListarLicenciasExpiradasController createListarLicenciasExpiradasController() {
        return new ListarLicenciasExpiradasController();
    }

    public ListarLicenciasVigentesController createListarLicenciasVigentesController() {
        return new ListarLicenciasVigentesController();
    }

    public ModificarUsuarioController createModificarUsuarioController() {
        return new ModificarUsuarioController(usuarioService);
    }

    public EmitirCopiaLicenciaController createEmitirCopiaLicenciaController() {
        return new EmitirCopiaLicenciaController(licenciaService, titularService);
    }

    public RenovarLicenciaController createRenovarLicenciaController() {
        return new RenovarLicenciaController(licenciaService);
    }
    
    public ModificarTitularController createModificarTitularController() {
        return new ModificarTitularController(titularService);
    }

}