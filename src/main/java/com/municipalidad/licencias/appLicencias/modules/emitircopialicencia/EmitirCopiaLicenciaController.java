package com.municipalidad.licencias.appLicencias.modules.emitircopialicencia;

import com.municipalidad.licencias.appLicencias.service.LicenciaService;
import com.municipalidad.licencias.appLicencias.service.TitularService;
import javax.swing.SwingUtilities;

public class EmitirCopiaLicenciaController {
    
    
   private final LicenciaService licenciaService;
   private final TitularService titularService;
   private EmitirCopiaLicenciaView view;
   
   public EmitirCopiaLicenciaController(LicenciaService licenciaService,
                                        TitularService titularService) {
       this.licenciaService = licenciaService;
       this.titularService = titularService;
   }
   
   
    public void display() {
        SwingUtilities.invokeLater(() -> {    
            view = new EmitirCopiaLicenciaView();
        });
        configurarListeners();
        view.setVisible(true);
    }
    
    private void configurarListeners() {
    
        view.setContinuarAction(e -> buscarTitular());
        view.setEmitirCopiaAction(e -> emitirCopia());
        view.setCancelarAction(e -> cancelar());
    
    }
    
    private void buscarTitular() {
    }
    
    private void emitirCopia() {
    }

    private void cancelar() {
      view.dispose();
    }
    
  
    
    
    
    
    
    
    
    
   
   
}


