package com.municipalidad.licencias.appLicencias.mapper;

import com.municipalidad.licencias.appLicencias.dto.UsuarioDTO;
import com.municipalidad.licencias.appLicencias.entities.Usuario;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(source = "nombreUsuario", target = "nombreUsuario")
    @Mapping(target = "password", ignore = true)
    UsuarioDTO toDTO(Usuario usuario);

    @Mapping(source = "nombreUsuario", target = "nombreUsuario")
    @Mapping(target = "passwordHash", ignore = true)
    Usuario toEntity(UsuarioDTO dto);

    @Mapping(source = "nombreUsuario", target = "nombreUsuario")
    @Mapping(target = "passwordHash", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(UsuarioDTO dto, @MappingTarget Usuario usuario);

}