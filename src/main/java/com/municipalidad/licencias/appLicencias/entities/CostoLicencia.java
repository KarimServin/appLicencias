package com.municipalidad.licencias.appLicencias.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "costo_licencia",
       uniqueConstraints = @UniqueConstraint(columnNames = {"clase_licencia", "vigencia", "es_copia"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostoLicencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "clase_licencia", nullable = false)
    private ClaseLicencia claseLicencia;

    @Column(nullable = false)
    private Integer vigencia; // 1, 3, 4, 5. Para copia: 0

    @Column(nullable = false)
    private Integer costo;

    @Column(name = "es_copia", nullable = false)
    @Builder.Default
    private Boolean esCopia = false;
}