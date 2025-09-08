package com.municipalidad.licencias.appLicencias.security;


public interface PasswordHasher {

    String hash(char[] rawPassword); 
    boolean matches(char[] rawPassword, String storedHash); //verificar que el usuario ingresa la contraseña correcta

} 
