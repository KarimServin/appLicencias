package com.municipalidad.licencias.appLicencias.mapper;


import com.municipalidad.licencias.appLicencias.modules.altausuario.AltaUsuarioDTO;
import com.municipalidad.licencias.appLicencias.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AltaUsuarioMapper {
    
    @Mapping(target = "passwordHash", ignore = true)
    Usuario toEntity(AltaUsuarioDTO dto);
}




