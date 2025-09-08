package com.municipalidad.licencias.appLicencias.model;


public enum Role {
    USER,
    ADMIN;    

    
    /**
     * 
     * formato requerido para escalar a Spring Security
     * 
     * @return devuelve un string que representa el nombre del rol con el prefijo "ROLE_"
     */
    public String asAuthority() {
        return "ROLE_" + name();
    }

    /**
     * 
     * Etiqueta legible para mostrar en la UI
     * 
     * @return devuelve un String indicando si es usuario o administrador
     */
    public String etiqueta() {
        return switch (this) {
            case USER -> "Usuario";
            case ADMIN -> "Administrador";
        };
    }
    
    
}

