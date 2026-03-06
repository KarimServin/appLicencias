package com.municipalidad.licencias.appLicencias.service.serviceImpl;

import com.municipalidad.licencias.appLicencias.entities.RegistroOperacion;
import com.municipalidad.licencias.appLicencias.repository.RegistroOperacionRepository;
import com.municipalidad.licencias.appLicencias.service.RegistroOperacionService;
import java.time.LocalDateTime;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegistroOperacionServiceImpl implements RegistroOperacionService {

    private static final Logger logger = LoggerFactory.getLogger(RegistroOperacionServiceImpl.class);

    private final RegistroOperacionRepository repository;

    public RegistroOperacionServiceImpl(RegistroOperacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registrar(String usuario, String tipoOperacion, String detalle) {
        RegistroOperacion registro = RegistroOperacion.builder()
                .fechaHora(LocalDateTime.now())
                .usuario(usuario)
                .tipoOperacion(tipoOperacion)
                .detalle(detalle)
                .build();
        repository.save(registro);
        logger.debug("Operación registrada: usuario={}, tipo={}", usuario, tipoOperacion);
    }

    @Override
    public List<RegistroOperacion> buscar(String usuario, String tipo, LocalDateTime desde, LocalDateTime hasta) {
        String usuarioFiltro = (usuario == null || usuario.isBlank()) ? null : usuario;
        String tipoFiltro = (tipo == null || tipo.isBlank()) ? null : tipo;
        return repository.buscarConFiltros(usuarioFiltro, tipoFiltro, desde, hasta);
    }

    @Override
    public List<String> obtenerTipos() {
        return repository.findTiposDistintos();
    }

    @Override
    public List<String> obtenerUsuarios() {
        return repository.findUsuariosDistintos();
    }
}
