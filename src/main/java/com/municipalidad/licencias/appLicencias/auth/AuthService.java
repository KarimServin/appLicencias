package com.municipalidad.licencias.appLicencias.auth;

import com.municipalidad.licencias.appLicencias.dto.CredencialesDTO;
import com.municipalidad.licencias.appLicencias.dto.UsuarioDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;


public interface AuthService {
 
    public UsuarioDTO login(CredencialesDTO CredencialesDTO) throws ServiceException;
    
}
