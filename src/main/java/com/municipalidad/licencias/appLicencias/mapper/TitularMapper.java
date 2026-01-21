package com.municipalidad.licencias.appLicencias.mapper;

import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.entities.Titular;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface TitularMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipoSangre", ignore = true) // se resuelve en el service
    @Mapping(target = "usuario", ignore = true)    // se toma de sesión
    Titular toEntity(TitularDTO dto);

    @Mapping(target = "grupoSanguineo", source = "tipoSangre.grupo")
    @Mapping(target = "factorSanguineo", source = "tipoSangre.factor")
    TitularDTO toDTO(Titular entity);
}