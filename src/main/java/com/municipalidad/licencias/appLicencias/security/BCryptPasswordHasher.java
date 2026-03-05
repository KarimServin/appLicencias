package com.municipalidad.licencias.appLicencias.security;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class BCryptPasswordHasher implements PasswordHasher {

    private final BCryptPasswordEncoder encoder;

   
    public BCryptPasswordHasher(
            @Value("${security.password.bcrypt.strength:12}") int strength
    ) {
        this.encoder = new BCryptPasswordEncoder(strength);
    }

    @Override
    public String hash(char[] rawPassword) {
        String plain = new String(rawPassword);
        return encoder.encode(plain);
    }

    @Override
    public boolean matches(char[] rawPassword, String storedHash) {
        String plain = new String(rawPassword);
        try {
            return encoder.matches(plain, storedHash);
        } finally {
            Arrays.fill(rawPassword, ' ');
        }
    }
}
