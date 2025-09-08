package com.municipalidad.licencias.appLicencias.mapper;

import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import com.municipalidad.licencias.appLicencias.model.Titular;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TitularMapper {
    Titular toEntity(TitularDTO dto);
    TitularDTO toDTO(Titular entity);
}