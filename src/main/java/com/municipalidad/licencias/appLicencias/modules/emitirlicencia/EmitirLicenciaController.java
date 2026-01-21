package com.municipalidad.licencias.appLicencias.modules.emitirlicencia;

import com.municipalidad.licencias.appLicencias.dto.EmisionLicenciaDTO;
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
            // Crear el DTO a partir de los datos de la vista
            EmisionLicenciaDTO emisionLicenciaDTO = new EmisionLicenciaDTO(
                Long.valueOf(view.getDni()),            // Obtiene el DNI desde la vista
                view.getClase(),          // Obtiene la clase de licencia desde la vista
                view.getObservaciones()   // Obtiene las observaciones desde la vista
            );

            // Llamar al servicio para emitir la licencia
            licenciaService.emitirLicencia(emisionLicenciaDTO);
            // Mostrar mensaje de éxito
            Dialogs.exito(view, "La licencia fue emitida correctamente.");
        } catch (IllegalArgumentException e) {
            // Captura errores como campos inválidos (por ejemplo, DNI vacío)
            Dialogs.error(view, "Error en los datos proporcionados: " + e.getMessage());
        } catch (RuntimeException e) {
            // Capturar errores del servicio, como incumplimiento de reglas de negocio
            Dialogs.error(view, "No se pudo emitir la licencia: " + e.getMessage());
        } catch (Exception e) {
            // Capturar errores inesperados
            Dialogs.error(view, "Ocurrió un error inesperado: " + e.getMessage());
        }
    }
    
    
    public void cancelar() {
        view.dispose();
    }
    
    
}
