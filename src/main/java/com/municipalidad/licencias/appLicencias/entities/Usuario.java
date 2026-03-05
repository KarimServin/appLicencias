package com.municipalidad.licencias.appLicencias.entities;

import jakarta.persistence.*;
import java.util.EnumSet;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.EqualsAndHashCode;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = "passwordHash")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "usuario",
    indexes = @Index(name = "idx_usuario_nombre", columnList = "nombre_usuario"))
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nombre_usuario", unique = true, nullable = false, length = 50)
    private String nombreUsuario;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 100)
    private String passwordHash;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_roles",
        joinColumns = @JoinColumn(name = "usuario_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false, length = 20)
    private Set<Role> roles = EnumSet.noneOf(Role.class);

    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    public boolean esAdmin() {
        return roles.contains(Role.ADMIN);
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}