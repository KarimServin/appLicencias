package com.municipalidad.licencias.appLicencias.service.serviceImpl;

import com.municipalidad.licencias.appLicencias.modules.gestionarcostos.CostoLicenciaDTO;
import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.entities.CostoClaseLicencia;
import com.municipalidad.licencias.appLicencias.repository.CostoLicenciaRepository;
import com.municipalidad.licencias.appLicencias.service.CostoLicenciaService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CostoLicenciaServiceImpl implements CostoLicenciaService {

    private static final Logger logger = LoggerFactory.getLogger(CostoLicenciaServiceImpl.class);

    private final CostoLicenciaRepository costoRepository;

    public CostoLicenciaServiceImpl(CostoLicenciaRepository costoRepository) {
        this.costoRepository = costoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public int obtenerCosto(ClaseLicencia clase, boolean esCopia, int vigencia) {
        if (esCopia) {
            return costoRepository.findByEsCopiaTrue()
                .stream()
                .findFirst()
                .map(CostoClaseLicencia::getCosto)
                .orElseThrow(() -> {
                    logger.error("No se encontró costo de copia configurado en la BD");
                    return new IllegalStateException("Costo de copia no configurado.");
                });
        }

        return costoRepository
            .findByClaseLicenciaAndVigenciaAndEsCopia(clase, vigencia, false)
            .map(CostoClaseLicencia::getCosto)
            .orElseThrow(() -> {
                logger.error("No se encontró costo para clase={}, vigencia={}", clase, vigencia);
                return new IllegalStateException(
                    "Costo no configurado para clase " + clase
                    + " con vigencia " + vigencia + " año(s).");
            });
    }

    @Override
    @Transactional(readOnly = true)
    public List<CostoLicenciaDTO> listarTodos() {
        logger.debug("Listando todos los costos de licencia");

        return costoRepository.findAllByOrderByClaseLicenciaAscVigenciaDesc()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CostoLicenciaDTO actualizar(Long id, Integer nuevoCosto) {
        if (nuevoCosto == null || nuevoCosto < 0) {
            throw new IllegalArgumentException("El costo debe ser un valor mayor o igual a cero.");
        }

        CostoClaseLicencia entidad = costoRepository.findById(id)
            .orElseThrow(() -> {
                logger.error("Costo con ID {} no encontrado", id);
                return new IllegalArgumentException("Costo con ID " + id + " no encontrado.");
            });

        int costoAnterior = entidad.getCosto();
        entidad.setCosto(nuevoCosto);
        CostoClaseLicencia guardado = costoRepository.save(entidad);

        logger.info("Costo actualizado: id={}, clase={}, vigencia={}, esCopia={}, anterior=${}, nuevo=${}",
            id,
            entidad.getClaseLicencia(),
            entidad.getVigencia(),
            entidad.getEsCopia(),
            costoAnterior,
            nuevoCosto);

        return toDTO(guardado);
    }

    private CostoLicenciaDTO toDTO(CostoClaseLicencia entidad) {
        return CostoLicenciaDTO.builder()
            .id(entidad.getId())
            .claseLicencia(entidad.getClaseLicencia())
            .vigencia(entidad.getVigencia())
            .costo(entidad.getCosto())
            .esCopia(entidad.getEsCopia())
            .build();
    }
}