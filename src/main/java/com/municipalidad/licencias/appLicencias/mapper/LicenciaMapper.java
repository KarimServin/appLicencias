package com.municipalidad.licencias.appLicencias.mapper;

import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import com.municipalidad.licencias.appLicencias.entities.Licencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LicenciaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "titular", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "licenciaAnterior", ignore = true)        
    Licencia licenciaDTOToLicencia(LicenciaDTO licenciaDTO);

    @Mapping(source = "titular.dni", target = "titularDni")
    LicenciaDTO licenciaToLicenciaDTO(Licencia licencia);
}
