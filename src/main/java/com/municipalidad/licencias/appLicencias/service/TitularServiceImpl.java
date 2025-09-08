package com.municipalidad.licencias.appLicencias.service;

import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.exception.ValidationException;
import com.municipalidad.licencias.appLicencias.mapper.TitularMapper;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.model.Usuario;
import com.municipalidad.licencias.appLicencias.repository.TitularRepository;
import com.municipalidad.licencias.appLicencias.repository.UsuarioRepository;
import com.municipalidad.licencias.appLicencias.session.SessionInfo;
import java.time.LocalDate;
import java.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;



@Service
public class TitularServiceImpl implements TitularService {
    private static final Logger logger = LoggerFactory.getLogger(TitularService.class);
    private final TitularRepository titularRepository;
    private final UsuarioRepository usuarioRepository;
    private final SessionInfo sessionInfo;
    private final TitularMapper titularMapper;
     
    @Autowired
    public TitularServiceImpl(TitularRepository titularRepository, 
                              UsuarioRepository usuarioRepository, 
                              SessionInfo sessionInfo, 
                              TitularMapper titularMapper) {
        this.titularRepository = titularRepository;
        this.usuarioRepository = usuarioRepository;
        this.sessionInfo = sessionInfo;
        this.titularMapper = titularMapper;
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

                //Lógica de negocio del Service
                titular.setUsuario(obtenerUsuarioActualAux());
                titular.setFechaLicenciaClaseB(null);
                titular.setTuvoLicenciaProfesional(false);

                //Guardar con manejo de errores
                Titular titularGuardado = titularRepository.save(titular);

                return titularMapper.toDTO(titularGuardado);

            } catch (DataIntegrityViolationException e) {
                throw new ServiceException("Error de integridad: DNI duplicado");
            } catch (Exception e) {
                throw new ServiceException("Error al guardar titular");
            }
            
    }

    @Override
    public Optional<Titular> buscarPorDni(Long dni) {
        return titularRepository.findById(dni);
    }

    @Override
    public void actualizarTitular(Long dni, String nombre, String apellido, 
                                  boolean esDonante, Long telefono, String email, 
                                  String domicilio) {

        Titular titular = titularRepository.findById(dni)
                .orElseThrow(() -> new RuntimeException("Titular no encontrado"));

        titular.setNombre(nombre);
        titular.setApellido(apellido);
        titular.setEsDonante(esDonante);
        titular.setTelefono(telefono);
        titular.setEmail(email);
        titular.setDomicilio(domicilio);
        titular.setUsuario(obtenerUsuarioActualAux());

        titularRepository.save(titular);
        
    }
    
    private Usuario obtenerUsuarioActualAux() {
        
        String nombreUsuario = sessionInfo.getNombreUsuarioActual();
         
        if (nombreUsuario == null || nombreUsuario.isBlank()) {
            throw new IllegalStateException("No hay usuario autenticado en sesión.");
        }
        logger.error("Usuario en sesión no existe en BD: {}", nombreUsuario); 
        return usuarioRepository.findByNombreUsuario(nombreUsuario)
        .orElseThrow(() -> new IllegalStateException("Usuario no encontrado: " + nombreUsuario));
        
    }
    
    
    /** 
     * Se validan nuevamente los datos por motivos de integridad y seguridad
     * @param titularDTO
     * @throws ValidationException
     * @throws ServiceException 
     */
    private void validarDatosTitular(TitularDTO titularDTO) throws ValidationException {

        
        if (titularDTO == null || titularDTO.getDni() == null) {
                throw new ValidationException("Datos del titular requeridos");
            }
        
        if (titularDTO == null) {
            throw new ValidationException("Los datos del titular son requeridos");
        }

      
        if (titularDTO.getDni() == null) {
            throw new ValidationException("DNI es requerido");
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

        
        char grupo = titularDTO.getGrupoSanguineo();
        if (grupo != 'A' && grupo != 'B' && grupo != 'O' && grupo != 'X') { // X para AB
            throw new ValidationException("Grupo sanguíneo debe ser A, B, O o AB");
        }
        
    }
    
    private void validarEdad(LocalDate fechaNacimiento) throws ServiceException {

        if (!(Period.between(fechaNacimiento, LocalDate.now()).getYears() > 17)) {
            throw new ServiceException("La persona debe ser mayor a 17 años para ser titular.");
        }
            
    }
        
    private void  validarDniUnico(Long dni) throws ServiceException {
    
     if (titularRepository.existsById(dni)) {
                throw new ServiceException("Ya existe un titular con DNI: " + dni);
            }
        
    }
    
}
