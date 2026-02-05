package com.municipalidad.licencias.appLicencias.modules.emitirlicencia;

import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.service.LicenciaService;
import com.municipalidad.licencias.appLicencias.service.TitularService;
import com.municipalidad.licencias.appLicencias.view.Dialogs;
import java.util.Optional;
import javax.swing.SwingUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class EmitirLicenciaController {
    
    private final TitularService titularService;
    private final LicenciaService licenciaService;
    private EmitirLicenciaView view;
    

    @Autowired
    public EmitirLicenciaController(TitularService titularService,
                                    LicenciaService licenciaService) {
        this.titularService = titularService;
        this.licenciaService = licenciaService;
    }
    
    
       public void display() {
            SwingUtilities.invokeLater(() -> {
                view = new EmitirLicenciaView();
                view.setVisible(true);
            });
            setListeners();
        }
    
    public void setListeners() {
    
        view.setAceptarAction(e -> aceptar());
        view.setCancelarAction(e -> cancelar());
        view.setBuscarTitularAction(e-> buscarTitular());
        
    }
    
    public void buscarTitular() {
        try {

            Optional<TitularDTO> titularDTO = 
                    titularService.buscarPorDni(Long.valueOf(view.getDni()));

            if (titularDTO.isPresent()) {
                TitularDTO dto = titularDTO.get();
                view.setInfoTitular(dto.getDni().toString(), dto.getNombre(), dto.getApellido());
            } else {
                Dialogs.error(view,"No se encontró un titular con ese DNI.");
            }

        } catch(Exception e) {
            Dialogs.error(view,"No se pudo realizar la búsqueda de titular");
        }
    }
    
    public void aceptar() {
        try {
            Long dni = Long.valueOf(view.getDni());
            var clases = view.getClasesSeleccionadas();

            if (clases.isEmpty()) {
                Dialogs.error(view, "Debe seleccionar al menos una clase de licencia.");
                return;
            }

            EmisionLicenciaDTO emisionLicenciaDTO =
                    new EmisionLicenciaDTO(dni, clases);

            licenciaService.emitirLicencia(emisionLicenciaDTO);

            Dialogs.exito(view, "La licencia fue emitida correctamente.");

        } catch (IllegalArgumentException e) {
            Dialogs.error(view, "Error en los datos proporcionados: " + e.getMessage());
        } catch (RuntimeException e) {
            Dialogs.error(view, "No se pudo emitir la licencia: " + e.getMessage());
        } catch (Exception e) {
            Dialogs.error(view, "Ocurrió un error inesperado: " + e.getMessage());
        }
    }
    
    
    public void cancelar() {
        view.dispose();
    }
    
    
}
