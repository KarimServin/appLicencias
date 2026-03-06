package com.municipalidad.licencias.appLicencias.events;

import com.municipalidad.licencias.appLicencias.service.RegistroOperacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OperacionEventListener {

    private static final Logger logger = LoggerFactory.getLogger(OperacionEventListener.class);

    private final RegistroOperacionService registroService;

    public OperacionEventListener(RegistroOperacionService registroService) {
        this.registroService = registroService;
    }

    @EventListener
    public void onOperacionEvent(OperacionEvent event) {
        try {
            registroService.registrar(event.getUsuario(), event.getTipoOperacion(), event.getDetalle());
        } catch (Exception e) {
            logger.error("Error al registrar operación: tipo={}, usuario={}", 
                    event.getTipoOperacion(), event.getUsuario(), e);
        }
    }
}
