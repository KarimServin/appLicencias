
package com.municipalidad.licencias.appLicencias.service;

import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.model.Usuario;

import java.time.LocalDate;
import java.util.Optional;

public interface TitularService {
    Titular guardarTitular(Long dni, String nombre, LocalDate fechaNacimiento,
                                 char grupoSanguineo, char factorSanguineo,
                                 boolean esDonante, boolean tuvoLicenciaProfesional,
                                 LocalDate fechaLicenciaClaseB, Long telefono, String email, String direccion, Usuario usuario);
    Optional<Titular> buscarPorDni(Long dni);
}
