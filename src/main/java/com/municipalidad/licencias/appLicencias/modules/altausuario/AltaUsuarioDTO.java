package com.municipalidad.licencias.appLicencias.modules.altausuario;


public class AltaUsuarioDTO {

    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String email;
    private char[] password;
    private boolean admin;

    public AltaUsuarioDTO(String nombreUsuario, String nombre, String apellido,
                          String email, char[] password, boolean admin) {
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    public String getNombreUsuario() { return nombreUsuario; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getEmail() { return email; }
    public char[] getPassword() { return password; }
    public boolean isAdmin() { return admin; }
}
