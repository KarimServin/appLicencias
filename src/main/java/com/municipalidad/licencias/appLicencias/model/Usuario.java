
package com.municipalidad.licencias.appLicencias.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombreUsuario;

    private String contrasenia;

    private boolean esSuperusuario;

    @OneToMany(cascade= CascadeType.ALL, mappedBy ="usuario",orphanRemoval=true)
    private List<Titular> titulares = new ArrayList<>();
}
