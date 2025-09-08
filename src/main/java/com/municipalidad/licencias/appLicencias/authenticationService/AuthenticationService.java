package com.municipalidad.licencias.appLicencias.authenticationService;

import com.municipalidad.licencias.appLicencias.dto.CredencialesLoginDTO;
import com.municipalidad.licencias.appLicencias.dto.UsuarioDTO;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;


public interface AuthenticationService {
 
    public UsuarioDTO login(CredencialesLoginDTO CredencialesLoginDTO) throws ServiceException;
    
}
