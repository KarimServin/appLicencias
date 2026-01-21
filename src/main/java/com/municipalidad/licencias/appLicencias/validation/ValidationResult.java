package com.municipalidad.licencias.appLicencias.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidationResult {
    
    private final List<String> errors = new ArrayList<>();
    
    public void addError(String error) {
        errors.add(error);
    }
    
    public boolean isValid() {
        return errors.isEmpty();
    }
    
    public String getFirstError() {
        return errors.isEmpty() ? "" : errors.get(0);
    }
    
    public List<String> getAllErrors() {
        return Collections.unmodifiableList(errors);
    }
}