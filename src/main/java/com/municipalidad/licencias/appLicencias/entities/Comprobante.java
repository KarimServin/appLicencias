package com.municipalidad.licencias.appLicencias.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "comprobantes")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private int monto;

    private String concepto; // "EMISIÓN" o "DUPLICADO"

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "licencia_id")
    private Licencia licencia;
}
