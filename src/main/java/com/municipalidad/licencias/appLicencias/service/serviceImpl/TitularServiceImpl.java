package com.municipalidad.licencias.appLicencias.service.serviceImpl;

import com.municipalidad.licencias.appLicencias.modules.emitirlicencia.ActualizarTitularRequestDTO;
import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.entities.TipoSangre;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.exception.ValidationException;
import com.municipalidad.licencias.appLicencias.mapper.TitularMapper;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.municipalidad.licencias.appLicencias.entities.Titular;
import com.municipalidad.licencias.appLicencias.repository.TipoSangreRepository;
import com.municipalidad.licencias.appLicencias.repository.TitularRepository;
import com.municipalidad.licencias.appLicencias.service.TitularService;
import com.municipalidad.licencias.appLicencias.session.CurrentUserProvider;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;



@Service
public class TitularServiceImpl implements TitularService {
    
    private static final Logger logger = LoggerFactory.getLogger(TitularServiceImpl.class);
    private final TitularRepository titularRepository;
    private final TipoSangreRepository tipoSangreRepository;
    private final TitularMapper titularMapper;
    private final CurrentUserProvider currentUserProvider;
     
    @Autowired
    public TitularServiceImpl(TitularRepository titularRepository, 
                              TipoSangreRepository tipoSangreRepository,
                              TitularMapper titularMapper,
                              CurrentUserProvider currentUserProvider) {
        this.titularRepository = titularRepository;
        this.tipoSangreRepository = tipoSangreRepository;
        this.titularMapper = titularMapper;
        this.currentUserProvider = currentUserProvider;
    }
     
        
    @Override
    public TitularDTO guardarTitular(TitularDTO titularDTO) throws ServiceException, ValidationException {
    
        //Validar Input (Redundancia - Seguridad/Integridad)
        validarDatosTitular(titularDTO);
        //Regla de negocio: Edad titular > 17
        validarEdad(titularDTO.getFechaNacimiento());
        //Regla de negocio + integridad: único DNI por titular   
        validarDniUnico(titularDTO.getDni());
            

            try {
                //Mapeo y persistencia
                Titular titular = titularMapper.toEntity(titularDTO);

                String grupo = titularDTO.getGrupoSanguineo().trim().toUpperCase();
                String factor = titularDTO.getFactorSanguineo().trim();

                TipoSangre ts = tipoSangreRepository.findByGrupoAndFactor(grupo, factor)
                .orElseThrow(() -> new ValidationException("Tipo de sangre inválido"));

                titular.setTipoSangre(ts);
                
                
                titular.setUsuario(currentUserProvider.getOrThrow());
                titular.setFechaLicenciaClaseB(null);
                titular.setTuvoLicenciaProfesional(false);

                //Guardar con manejo de errores
                Titular titularGuardado = titularRepository.save(titular);

                return titularMapper.toDTO(titularGuardado);

            } catch (DataIntegrityViolationException e) {
                throw new ServiceException("Error de integridad: DNI duplicado");
            } catch (ValidationException e) {
                throw e;
            } catch (Exception e) {
                logger.error("Error al guardar titular", e);
                throw new ServiceException("Error al guardar titular");
            }
            
    }

    
    @Override
    public Optional<TitularDTO> buscarPorDni(Long dni) {
          return titularRepository.findByDni(dni)
                  .map(titularMapper::toDTO);
    }
    
    
    @Transactional
    @Override
    public TitularDTO actualizarDatosTitular(ActualizarTitularRequestDTO req) {
        Titular titular = titularRepository.findByDni(req.getDni())
                .orElseThrow(() -> new ServiceException("Titular no encontrado."));

        
        titular.setTelefono(req.getTelefono());
        titular.setEmail(req.getEmail());
        titular.setDomicilio(req.getDomicilio());
        titular.setEsDonante(req.getEsDonante());

        Titular guardado = titularRepository.save(titular);
        return titularMapper.toDTO(guardado);
    }
    
  
    private void validarDatosTitular(TitularDTO titularDTO) throws ValidationException {

        
        if (titularDTO == null || titularDTO.getDni() == null) {
                throw new ValidationException("Datos del titular requeridos");
            }
        
        if (titularDTO.getNombre() == null || titularDTO.getNombre().trim().isEmpty()) {
            throw new ValidationException("Nombre es requerido");
        }

        if (titularDTO.getApellido() == null || titularDTO.getApellido().trim().isEmpty()) {
            throw new ValidationException("Apellido es requerido");
        }

        if (titularDTO.getFechaNacimiento() == null) {
            throw new ValidationException("Fecha de nacimiento es requerida");
        }

        if (titularDTO.getTelefono() == null) {
            throw new ValidationException("Teléfono es requerido");
        }

        if (titularDTO.getEmail() == null || titularDTO.getEmail().trim().isEmpty()) {
            throw new ValidationException("Correo es requerido");
        }

        if (titularDTO.getDomicilio() == null || titularDTO.getDomicilio().trim().isEmpty()) {
            throw new ValidationException("Domicilio es requerido");
        }

        
        String grupo = titularDTO.getGrupoSanguineo();
        Set<String> validos = Set.of("A", "B", "O", "AB");
        
        if (grupo == null || !validos.contains(grupo.trim().toUpperCase())) {
            throw new ValidationException("Grupo sanguíneo debe ser A, B, O o AB");
        }
        
        String factor = titularDTO.getFactorSanguineo();
        
        if (!factor.equals("+") && !factor.equals("-")) {
            throw new ValidationException("Factor sanguíneo debe ser + o -.");
        }
  
    }
    
    private void validarEdad(LocalDate fechaNacimiento) throws ServiceException {
        
        if (!(Period.between(fechaNacimiento, LocalDate.now()).getYears() > 17)) {
            throw new ServiceException("La persona debe ser mayor a 17 años para ser titular.");
        }
    }
        
    private void  validarDniUnico(Long dni) throws ServiceException {
        if (titularRepository.existsByDni(dni)) {
                throw new ServiceException("Ya existe un titular con DNI: " + dni);
        }       
    }
}
