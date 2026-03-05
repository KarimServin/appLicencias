package com.municipalidad.licencias.appLicencias.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "licencia_clase",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_licencia_clase",
                columnNames = {"licencia_id", "clase_licencia"}
        )
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LicenciaClase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "licencia_id", nullable = false)
    private Licencia licencia;

    @Enumerated(EnumType.STRING)
    @Column(name = "clase_licencia", nullable = false, length = 10)
    private ClaseLicencia claseLicencia;
}