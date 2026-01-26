
package com.municipalidad.licencias.appLicencias.service;

import com.municipalidad.licencias.appLicencias.dto.ActualizarTitularRequestDTO;
import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.exception.ValidationException;
import java.util.Optional;

public interface TitularService {
        
        TitularDTO guardarTitular(TitularDTO titularDTO) throws ServiceException, ValidationException;
        public Optional<TitularDTO> buscarPorDni(Long dni);
        TitularDTO actualizarDatosTitular(Long dni, ActualizarTitularRequestDTO req);
        void actualizarTitular(Long dni, String nombre, String apellido, Boolean esDonante, Long telefono, String email, String direccion) throws ServiceException;
      
}
