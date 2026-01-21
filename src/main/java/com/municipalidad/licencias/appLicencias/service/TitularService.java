
package com.municipalidad.licencias.appLicencias.service;

import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.exception.ValidationException;
import com.municipalidad.licencias.appLicencias.entities.Titular;
import com.municipalidad.licencias.appLicencias.entities.Usuario;

import java.time.LocalDate;
import java.util.Optional;

public interface TitularService {
        
        TitularDTO guardarTitular(TitularDTO titularDTO) throws ServiceException, ValidationException;
    
        Optional<TitularDTO> buscarPorDni(Long dni);

        void actualizarTitular(Long dni, String nombre, String apellido, Boolean esDonante, Long telefono, String email, String direccion) throws ServiceException;
      
}
