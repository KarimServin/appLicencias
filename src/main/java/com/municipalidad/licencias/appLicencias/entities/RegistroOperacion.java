package com.municipalidad.licencias.appLicencias.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "registro_operaciones", indexes = {
    @Index(name = "idx_reg_op_fecha_hora", columnList = "fecha_hora"),
    @Index(name = "idx_reg_op_usuario", columnList = "usuario"),
    @Index(name = "idx_reg_op_tipo_operacion", columnList = "tipo_operacion")
})
public class RegistroOperacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @Column(name = "usuario", length = 50, nullable = false)
    private String usuario;

    @Column(name = "tipo_operacion", length = 50, nullable = false)
    private String tipoOperacion;

    @Column(name = "detalle", length = 500)
    private String detalle;
}
