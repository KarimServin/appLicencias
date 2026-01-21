package com.municipalidad.licencias.appLicencias.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoSangre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2) // "AB", "A", "O"
    private String grupo;

    @Column(nullable = false, length = 1) // "+", "-"
    private String factor;

    // Método utilitario para mostrar el tipo completo (ej: "A+")
    public String getNombreCompleto() {
        return grupo + factor;
    }
}