package com.municipalidad.licencias.appLicencias.dto;

import com.municipalidad.licencias.appLicencias.entities.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = "password")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor

public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    private Long id;

    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String email;

    private String password;

    private Set<Role> roles = EnumSet.noneOf(Role.class);

    private boolean activo = true;

    public UsuarioDTO(Long id, String usuario) {
        this.id = id;
        this.nombreUsuario = usuario;
    }

    public boolean esAdmin() {
        return roles.contains(Role.ADMIN);
    }

    public void agregarRol(Role rol) {
        roles.add(rol);
    }

    public boolean tieneRol(Role rol) {
        return roles.contains(rol);
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}