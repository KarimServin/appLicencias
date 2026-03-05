package com.municipalidad.licencias.appLicencias.mapper;

import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import com.municipalidad.licencias.appLicencias.entities.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.entities.Licencia;
import com.municipalidad.licencias.appLicencias.entities.ClaseHabilitada;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface LicenciaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "titular", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "licenciaAnterior", ignore = true)
    @Mapping(target = "clases", ignore = true) // <--- importante: se carga en service con addClase()
    Licencia toEntity(LicenciaDTO dto);

    @Mapping(target = "id", source = "id")
    @Mapping(source = "titular.dni", target = "titularDni")
    @Mapping(target = "clases", expression = "java(mapClases(entity.getClases()))")
    LicenciaDTO toDTO(Licencia entity);

    default Set<ClaseLicencia> mapClases(Set<ClaseHabilitada> clases) {
        if (clases == null || clases.isEmpty()) return Collections.emptySet();
        return clases.stream()
                .map(ClaseHabilitada::getClaseLicencia)
                .collect(Collectors.toSet());
    }
}