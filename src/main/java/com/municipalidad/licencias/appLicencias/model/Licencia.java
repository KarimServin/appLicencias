
package com.municipalidad.licencias.appLicencias.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Miguel
 */
@Getter
@Setter
@Entity
public class Licencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ClaseLicencia clase;// A, B, C, D, E, F, G

    private LocalDate fechaEmision;
    
    private LocalDate fechaVencimiento;

    @ManyToOne
    private Usuario usuario;

    private String observaciones;

    @ManyToOne
    private Titular titular;
    
    public void setFechaVencimiento(int vigencia) {
    
    LocalDate fechaAux =  this.fechaEmision;
    
    int mesAux = this.fechaEmision.getMonthValue();
    int diaAux = this.fechaEmision.getDayOfMonth();
    
    this.fechaVencimiento = fechaAux.plusYears(vigencia);
    this.fechaVencimiento.withMonth(mesAux);
    this.fechaVencimiento.withDayOfMonth(diaAux);
    }
}