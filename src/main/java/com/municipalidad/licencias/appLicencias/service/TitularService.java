
package com.municipalidad.licencias.appLicencias.service;

import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.exception.ValidationException;
import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.model.Usuario;

import java.time.LocalDate;
import java.util.Optional;

public interface TitularService {
        
        TitularDTO guardarTitular(TitularDTO titularDTO) throws ServiceException, ValidationException;
    
        Optional<Titular> buscarPorDni(Long dni);

        void actualizarTitular(Long dni, String nombre, String apellido, boolean esDonante, Long telefono, String email, String direccion);
        
    
}
