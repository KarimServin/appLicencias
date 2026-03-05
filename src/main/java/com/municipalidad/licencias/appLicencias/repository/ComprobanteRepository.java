/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.municipalidad.licencias.appLicencias.repository;

import com.municipalidad.licencias.appLicencias.entities.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author karim
 */
@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {

    @Override
    public Comprobante save(Comprobante comprobante);
}
