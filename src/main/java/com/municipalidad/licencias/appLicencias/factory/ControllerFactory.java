package com.municipalidad.licencias.appLicencias.factory;

import com.municipalidad.licencias.appLicencias.modules.altatitular.AltaTitularController;
import com.municipalidad.licencias.appLicencias.modules.altausuario.AltaUsuarioController;
import com.municipalidad.licencias.appLicencias.modules.emitirlicencia.EmitirLicenciaController;
import com.municipalidad.licencias.appLicencias.modules.consultarlicencias.ConsultarLicenciasController;
import com.municipalidad.licencias.appLicencias.modules.emitircopialicencia.EmitirCopiaLicenciaController;
import com.municipalidad.licencias.appLicencias.modules.modificartitular.ModificarTitularController;
import com.municipalidad.licencias.appLicencias.session.SessionController;
import com.municipalidad.licencias.appLicencias.modules.modificarusuario.ModificarUsuarioController;
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
    private final LicenciaConsultaService licenciaConsultaService;
    @Autowired
    public ControllerFactory (
            LicenciaService licenciaService,
            TitularService titularService,
            UsuarioService usuarioService,
            SessionController sessionController,
            SessionInfo sessionInfo,
            TitularValidator titularValidator,
            LicenciaConsultaService licenciaConsultaService) {
        
        this.licenciaService = licenciaService;
        this.titularService = titularService;
        this.usuarioService = usuarioService;
        this.titularValidator = titularValidator;
        this.licenciaConsultaService = licenciaConsultaService;
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


    public ConsultarLicenciasController createConsultarLicenciasController() {
        return new ConsultarLicenciasController(licenciaConsultaService);
    }



    public ModificarUsuarioController createModificarUsuarioController() {
        return new ModificarUsuarioController(usuarioService);
    }

    public EmitirCopiaLicenciaController createEmitirCopiaLicenciaController() {
        return new EmitirCopiaLicenciaController(licenciaService, titularService);
    }

    
    public ModificarTitularController createModificarTitularController() {
        return new ModificarTitularController(titularService);
    }

}