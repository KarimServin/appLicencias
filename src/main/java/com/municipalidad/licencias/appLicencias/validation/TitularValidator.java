package com.municipalidad.licencias.appLicencias.validation;

import com.municipalidad.licencias.appLicencias.dto.TitularDTO;
import java.time.LocalDate;
import org.springframework.stereotype.Component;
@Component
public class TitularValidator {

    private static final String REGEX_SOLO_LETRAS = "[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+";
    private static final String REGEX_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final int MAX_LONGITUD_NOMBRE = 50;
    private static final int MIN_DIGITOS_DNI = 7;
    private static final int MIN_DIGITOS_TELEFONO = 7;
    private static final int MAX_DIGITOS_TELEFONO = 15;
    private static final int EDAD_MINIMA = 17;

    public ValidationResult validarTitular(TitularDTO titular) {
        ValidationResult result = new ValidationResult();

        validarDni(titular.getDni(), result);
        validarNombre(titular.getNombre(), result);
        validarApellido(titular.getApellido(), result);
        validarTelefono(titular.getTelefono(), result);
        validarEmail(titular.getEmail(), result);
        validarDomicilio(titular.getDomicilio(), result);
        validarFechaNacimiento(titular.getFechaNacimiento(), result);

        return result;
    }
    

    private void validarDni(Long dni, ValidationResult result) {
        if (dni == null) {
            result.addError("Debe ingresar el número de documento.");
            return;
        }
        if (String.valueOf(dni).length() < MIN_DIGITOS_DNI) {
            result.addError("El DNI debe tener al menos " + MIN_DIGITOS_DNI + " dígitos.");
        }
    }

    private void validarNombre(String nombre, ValidationResult result) {
        validarCampoTexto(nombre, "nombre", result);
    }

    private void validarApellido(String apellido, ValidationResult result) {
        validarCampoTexto(apellido, "apellido", result);
    }

    private void validarCampoTexto(String valor, String nombreCampo, ValidationResult result) {
        if (valor == null || valor.trim().isEmpty()) {
            result.addError("Debe ingresar el " + nombreCampo + ".");
            return;
        }
        if (!valor.matches(REGEX_SOLO_LETRAS)) {
            result.addError("El " + nombreCampo + " solo puede contener letras.");
            return;
        }
        if (valor.length() > MAX_LONGITUD_NOMBRE) {
            result.addError("El " + nombreCampo + " no puede exceder " + MAX_LONGITUD_NOMBRE + " caracteres.");
        }
    }

    private void validarTelefono(Long telefono, ValidationResult result) {
        if (telefono == null) {
            result.addError("Debe ingresar el teléfono.");
            return;
        }
        int digitos = String.valueOf(telefono).length();
        if (digitos < MIN_DIGITOS_TELEFONO || digitos > MAX_DIGITOS_TELEFONO) {
            result.addError("El teléfono debe tener entre " + MIN_DIGITOS_TELEFONO + " y " + MAX_DIGITOS_TELEFONO + " dígitos.");
        }
    }

    private void validarEmail(String email, ValidationResult result) {
        if (email == null || email.trim().isEmpty()) {
            result.addError("Debe ingresar el correo electrónico.");
            return;
        }
        if (!email.matches(REGEX_EMAIL)) {
            result.addError("Debe ingresar una dirección de correo válida. Ejemplo: juanperez@example.com");
        }
    }

    private void validarDomicilio(String domicilio, ValidationResult result) {
        if (domicilio == null || domicilio.trim().isEmpty()) {
            result.addError("Debe ingresar el domicilio.");
        }
    }

    private void validarFechaNacimiento(LocalDate fechaNacimiento, ValidationResult result) {
        if (fechaNacimiento == null) {
            result.addError("Debe ingresar la fecha de nacimiento.");
            return;
        }
        if (fechaNacimiento.isAfter(LocalDate.now())) {
            result.addError("La fecha de nacimiento no puede ser una fecha futura.");
            return;
        }
        if (LocalDate.now().minusYears(EDAD_MINIMA).isBefore(fechaNacimiento)) {
            result.addError("El titular debe tener al menos " + EDAD_MINIMA + " años.");
        }
    }
}