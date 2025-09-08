package com.municipalidad.licencias.appLicencias.mapper;


import com.municipalidad.licencias.appLicencias.dto.AltaUsuarioDTO;
import com.municipalidad.licencias.appLicencias.dto.CredencialesLoginDTO;
import com.municipalidad.licencias.appLicencias.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AltaUsuarioMapper {
    
    @Mapping(target = "passwordHash", ignore = true)
    Usuario toEntity(AltaUsuarioDTO dto);
}




