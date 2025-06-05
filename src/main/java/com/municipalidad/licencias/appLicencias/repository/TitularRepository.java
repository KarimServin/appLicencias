/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.municipalidad.licencias.appLicencias.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.municipalidad.licencias.appLicencias.model.Titular;

/**
 *
 * @author Miguel
 */
public interface TitularRepository extends JpaRepository<Titular, Long> {}