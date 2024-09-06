package com.eduardo.proyectocomerce.ecomercevideojuegos.validation;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {
    private Map<String, String> errors = new HashMap<>();

    public void addError(String field, String message) {
        errors.put(field, message);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
