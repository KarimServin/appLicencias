package com.municipalidad.licencias.appLicencias.factory;

import com.municipalidad.licencias.appLicencias.modules.altatitular.AltaTitularController;
import com.municipalidad.licencias.appLicencias.modules.altausuario.AltaUsuarioController;
import com.municipalidad.licencias.appLicencias.modules.emitirlicencia.EmitirLicenciaController;
import com.municipalidad.licencias.appLicencias.modules.consultarlicencias.ConsultarLicenciasController;
import com.municipalidad.licencias.appLicencias.modules.consultaroperaciones.ConsultarOperacionesController;
import com.municipalidad.licencias.appLicencias.modules.emitircopialicencia.EmitirCopiaLicenciaController;
import com.municipalidad.licencias.appLicencias.modules.gestionarcostos.GestionarCostosController;
import com.municipalidad.licencias.appLicencias.modules.gestionarusuarios.EditarUsuarioController;
import com.municipalidad.licencias.appLicencias.modules.modificartitular.ModificarTitularController;
import com.municipalidad.licencias.appLicencias.session.SessionController;
import com.municipalidad.licencias.appLicencias.modules.gestionarusuarios.GestionarUsuariosController;
import com.municipalidad.licencias.appLicencias.service.*;
import com.municipalidad.licencias.appLicencias.session.SessionInfo;
import com.municipalidad.licencias.appLicencias.validation.TitularValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;



//Crea los controladores de pantallas y les pasa los servicios

@Component
public class ControllerFactory {
    
    private final LicenciaService licenciaService;
    private final TitularService titularService;
    private final UsuarioService usuarioService;
    private final TitularValidator titularValidator;
    private final LicenciaConsultaService licenciaConsultaService;
    private final ComprobanteService comprobanteService;
    private final PrintService printService;
    private final ExcelExportService excelExportService;
    private final CostoLicenciaService costoLicenciaService;
    private final RegistroOperacionService registroOperacionService;
    private final ApplicationEventPublisher eventPublisher;
    private final SessionInfo sessionInfo;

    @Autowired
    public ControllerFactory (
            LicenciaService licenciaService,
            TitularService titularService,
            UsuarioService usuarioService,
            SessionController sessionController,
            SessionInfo sessionInfo,
            TitularValidator titularValidator,
            LicenciaConsultaService licenciaConsultaService,
            ComprobanteService comprobanteService,
            PrintService printService,
            ExcelExportService excelExportService,
            CostoLicenciaService costoLicenciaService,
            RegistroOperacionService registroOperacionService,
            ApplicationEventPublisher eventPublisher) {
        
        this.licenciaService = licenciaService;
        this.titularService = titularService;
        this.usuarioService = usuarioService;
        this.titularValidator = titularValidator;
        this.licenciaConsultaService = licenciaConsultaService;
        this.comprobanteService = comprobanteService;
        this.printService = printService;
        this.excelExportService = excelExportService;
        this.costoLicenciaService = costoLicenciaService;
        this.registroOperacionService = registroOperacionService;
        this.eventPublisher = eventPublisher;
        this.sessionInfo = sessionInfo;
    }
    
   

    public AltaUsuarioController createAltaUsuarioController() {
        return new AltaUsuarioController(usuarioService, eventPublisher, sessionInfo);
    }
    
    public AltaTitularController createAltaTitularController() {
        return new AltaTitularController(titularService, titularValidator, eventPublisher, sessionInfo);
    }

    public EmitirLicenciaController createEmitirLicenciaController() {
        return new EmitirLicenciaController(titularService, licenciaService, comprobanteService, printService, eventPublisher, sessionInfo);
    } 

    public ModificarTitularController createModificarTitularController() {
        return new ModificarTitularController(titularService, licenciaService, eventPublisher, sessionInfo);
    }

    public ConsultarLicenciasController createConsultarLicenciasController() {
        return new ConsultarLicenciasController(licenciaConsultaService, excelExportService);
    }


    public GestionarUsuariosController createModificarUsuarioController() {
        return new GestionarUsuariosController(usuarioService, createEditarUsuarioController(), eventPublisher, sessionInfo);
    }
    
    private EditarUsuarioController createEditarUsuarioController() {
        return new EditarUsuarioController(usuarioService);
    }

    public EmitirCopiaLicenciaController createEmitirCopiaLicenciaController() {
        return new EmitirCopiaLicenciaController(licenciaService, titularService, comprobanteService, printService, eventPublisher, sessionInfo);
    }

    public GestionarCostosController createGestionarCostosController() {
        return new GestionarCostosController(costoLicenciaService, eventPublisher, sessionInfo);
    }

    public ConsultarOperacionesController createConsultarOperacionesController() {
        return new ConsultarOperacionesController(registroOperacionService);
    }
}