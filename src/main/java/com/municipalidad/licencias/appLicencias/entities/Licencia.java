package com.municipalidad.licencias.appLicencias.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "licencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Licencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaEmision;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "titular_id", nullable = false)
    private Titular titular;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "licencia_anterior_id")
    private Licencia licenciaAnterior;

    // Detalle: clases habilitadas
    @OneToMany(mappedBy = "licencia", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<LicenciaClase> clases = new HashSet<>();

    /**
     * Agrega una clase a la licencia, evitando duplicados lógicos en memoria.
     * La BD también lo asegura con UNIQUE(licencia_id, clase_licencia).
     */
    public void addClase(ClaseLicencia clase) {
        if (clase == null) return;

        boolean existe = this.clases.stream()
                .anyMatch(lc -> lc.getClaseLicencia() == clase);

        if (existe) return;

        LicenciaClase lc = new LicenciaClase();
        lc.setLicencia(this);
        lc.setClaseLicencia(clase);
        this.clases.add(lc);
    }

    /**
     * Quita una clase de la licencia.
     * Con orphanRemoval=true, Hibernate borra el registro en licencia_clase.
     */
    public void removeClase(ClaseLicencia clase) {
        if (clase == null) return;

        this.clases.removeIf(lc -> {
            boolean match = lc.getClaseLicencia() == clase;
            if (match) lc.setLicencia(null); // prolijo: desvincula lado hijo
            return match;
        });
    }
}