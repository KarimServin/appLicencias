/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.municipalidad.licencias.appLicencias.service;
import com.municipalidad.licencias.appLicencias.model.ClaseLicencia;
import com.municipalidad.licencias.appLicencias.model.Licencia;
import com.municipalidad.licencias.appLicencias.model.Titular;
import com.municipalidad.licencias.appLicencias.repository.LicenciaRepository;
import com.municipalidad.licencias.appLicencias.repository.TitularRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import java.util.Optional;

/**
 *
 * @author Carlos
 */
public class LicenciaServiceTest {
    
    private LicenciaService licenciaService;
    private TitularRepository titularRepo;
    private LicenciaRepository licenciaRepo;
    private Licencia licencia;
    private Titular titular;
    
    
    @BeforeEach
    public void setUp() {
        
        titularRepo = mock(TitularRepository.class);
        licenciaRepo = mock(LicenciaRepository.class);
        licencia = new Licencia();
        titular = new Titular();
        licencia.setTitular(titular);        
        Long dni = 40703261L;
        titular.setDni(dni);
        licenciaService = new LicenciaService(titularRepo, licenciaRepo);
    }
    //pruebas unitarias correspondiente al módulo calcularVigencia(licencia, titular)
    @Test
    public void calcularVigenciaLicenciaMenorDe21(){
    titular.setFechaNacimiento(LocalDate.of(2008, 7, 1));
    licencia.setClaseLicencia(ClaseLicencia.A);
    if(licenciaRepo.findByTitularDni(titular.getDni()).isEmpty())
        Assertions.assertEquals(1, licenciaService.calcularVigencia(licencia, titular));
    else
        Assertions.assertEquals(3, licenciaService.calcularVigencia(licencia, titular));
    }
    @Test
    public void calcularVigenciaLicenciaMAyorDe21(){
    titular.setFechaNacimiento(LocalDate.of(2001, 9, 11));
    licencia.setClaseLicencia(ClaseLicencia.D);
    Assertions.assertEquals(5, licenciaService.calcularVigencia(licencia, titular));
    }
    @Test
    public void calcularVigenciaLicenciaMAyorDe47(){
    titular.setFechaNacimiento(LocalDate.of(1975, 1, 1));
    licencia.setClaseLicencia(ClaseLicencia.D);
    Assertions.assertEquals(4, licenciaService.calcularVigencia(licencia, titular));
    }
    @Test
    public void calcularVigenciaLicenciaMAyorDe61(){
    titular.setFechaNacimiento(LocalDate.of(1963, 4, 23));
    licencia.setClaseLicencia(ClaseLicencia.B);
    Assertions.assertEquals(3, licenciaService.calcularVigencia(licencia, titular));
    }
    @Test
    public void calcularVigenciaLicenciaMAyorDe70(){
    titular.setFechaNacimiento(LocalDate.of(1950, 1, 22));
    licencia.setClaseLicencia(ClaseLicencia.A);
    Assertions.assertEquals(1, licenciaService.calcularVigencia(licencia, titular));
    }
    
    //pruebas unitarias correspondiente al módulo calcularCosto(licencia)
    @Test
    public void calcularCostoLicenciaCopia() {
        Assertions.assertEquals(50, licenciaService.calcularCosto(licencia, true));
    }
    @Test
    public void calcularCostoLicenciaAVigencia5Anios(){
        titular.setFechaNacimiento(LocalDate.of(2000, 1, 22));
        licencia.setClaseLicencia(ClaseLicencia.A);
        Assertions.assertEquals(48, licenciaService.calcularCosto(licencia, false));
    }
    @Test
    public void calcularCostoLicenciaAVigencia1Anios(){
        titular.setFechaNacimiento(LocalDate.of(2008, 1, 22));
        licencia.setClaseLicencia(ClaseLicencia.A);
        Assertions.assertEquals(28, licenciaService.calcularCosto(licencia, false));
    }
    @Test
    public void calcularCostoLicenciaBVigencia4Anios(){
        titular.setFechaNacimiento(LocalDate.of(1975, 1, 22));
        licencia.setClaseLicencia(ClaseLicencia.B);
        Assertions.assertEquals(38, licenciaService.calcularCosto(licencia, false));
    }
    @Test
    public void calcularCostoLicenciaBVigencia3Anios(){
        titular.setFechaNacimiento(LocalDate.of(1960, 1, 22));
        licencia.setClaseLicencia(ClaseLicencia.A);
        Assertions.assertEquals(33, licenciaService.calcularCosto(licencia, false));
    }
    @Test
    public void calcularCostoLicenciaCVigencia5Anios(){
        titular.setFechaNacimiento(LocalDate.of(2001, 8, 22));
        licencia.setClaseLicencia(ClaseLicencia.C);
        Assertions.assertEquals(55, licenciaService.calcularCosto(licencia, false));
    }
    @Test
    public void calcularCostoLicenciaCVigencia4Anios(){
        titular.setFechaNacimiento(LocalDate.of(1974, 8, 12));
        licencia.setClaseLicencia(ClaseLicencia.C);
        Assertions.assertEquals(43, licenciaService.calcularCosto(licencia, false));
    }
    @Test
    public void calcularCostoLicenciaDVigencia5Anios(){
        titular.setFechaNacimiento(LocalDate.of(2000, 8, 12));
        licencia.setClaseLicencia(ClaseLicencia.D);
        Assertions.assertEquals(108, licenciaService.calcularCosto(licencia, false));
    }
    @Test
    public void calcularCostoLicenciaDVigencia1Anios(){
        titular.setFechaNacimiento(LocalDate.of(1950, 8, 12));
        licencia.setClaseLicencia(ClaseLicencia.D);
        Assertions.assertEquals(58, licenciaService.calcularCosto(licencia, false));
    }
    @Test
    public void calcularCostoLicenciaEVigencia5Anios(){
        titular.setFechaNacimiento(LocalDate.of(2000, 8, 12));
        licencia.setClaseLicencia(ClaseLicencia.E);
        Assertions.assertEquals(67, licenciaService.calcularCosto(licencia, false));
    }
    @Test
    public void calcularCostoLicenciaEVigencia1Anios(){
        titular.setFechaNacimiento(LocalDate.of(1951, 12, 12));
        licencia.setClaseLicencia(ClaseLicencia.E);
        Assertions.assertEquals(37, licenciaService.calcularCosto(licencia, false));
    }
    @Test
    public void calcularCostoLicenciaFVigencia5Anios(){
        titular.setFechaNacimiento(LocalDate.of(2002, 8, 12));
        licencia.setClaseLicencia(ClaseLicencia.F);
        Assertions.assertEquals(28, licenciaService.calcularCosto(licencia, false));
    }
    @Test
    public void calcularCostoLicenciaFVigencia1Anios(){
        titular.setFechaNacimiento(LocalDate.of(1952, 4, 12));
        licencia.setClaseLicencia(ClaseLicencia.F);
        Assertions.assertEquals(13, licenciaService.calcularCosto(licencia, false));
    }
    @Test
    public void calcularCostoLicenciaGVigencia5Anios(){
        titular.setFechaNacimiento(LocalDate.of(2002, 8, 12));
        licencia.setClaseLicencia(ClaseLicencia.G);
        Assertions.assertEquals(48, licenciaService.calcularCosto(licencia, false));
    }
    @Test
    public void calcularCostoLicenciaGVigencia1Anios(){
        titular.setFechaNacimiento(LocalDate.of(1950, 1, 1));
        licencia.setClaseLicencia(ClaseLicencia.G);
        Assertions.assertEquals(28, licenciaService.calcularCosto(licencia, false));
    }
    
    //pruebas unitarias correspodientes a las pruebas de puedeEmitirLicencia
    @Test
    public void noPuedeEmitirLicenciaNoProfesionalSiEsMenorDe17Anios() {
        titular.setFechaNacimiento(LocalDate.of(2010, 1, 1));
        when(titularRepo.findById(titular.getDni())).thenReturn(Optional.of(titular));
        Assertions.assertFalse(licenciaService.puedeEmitirLicencia(titular.getDni(), ClaseLicencia.B));
    }
    @Test
    public void puedeEmitirLicenciaNoProfesionalSiEsMayorDe17AniosYMenorDe90() {
        titular.setFechaNacimiento(LocalDate.of(2008, 4, 19));
        when(titularRepo.findById(titular.getDni())).thenReturn(Optional.of(titular));
        Assertions.assertTrue(licenciaService.puedeEmitirLicencia(titular.getDni(), ClaseLicencia.G));
    }
    @Test
    public void noPuedeEmitirLicenciaProfesionalSiEsMenorDe21YMayorA17() {
        titular.setFechaNacimiento(LocalDate.of(2005, 3, 22));
        when(titularRepo.findById(titular.getDni())).thenReturn(Optional.of(titular));
        Assertions.assertFalse(licenciaService.puedeEmitirLicencia(titular.getDni(), ClaseLicencia.C));
    }
    @Test
    public void noPuedeEmitirLicenciaProfesionalSiEsMayorA21YNoTieneLicenciaB() {
        titular.setFechaNacimiento(LocalDate.of(2003, 3, 22));
        when(titularRepo.findById(titular.getDni())).thenReturn(Optional.of(titular));
        Assertions.assertFalse(licenciaService.puedeEmitirLicencia(titular.getDni(), ClaseLicencia.D));
    }
    @Test
    public void noPuedeEmitirLicenciaProfesionalSiEsMayorA21YNoTieneLicenciaBHaceMasDeUnAnio() {
        titular.setFechaNacimiento(LocalDate.of(2005, 3, 22));
        when(titularRepo.findById(titular.getDni())).thenReturn(Optional.of(titular));
        titular.setFechaLicenciaClaseB(LocalDate.of(2025, 3, 22));
        Assertions.assertFalse(licenciaService.puedeEmitirLicencia(titular.getDni(), ClaseLicencia.E));
    }
    @Test
    public void puedeEmitirLicenciaProfesionalSiEsMayorA21YTieneLicenciaBHaceMasDeUnAnio() {
        titular.setFechaNacimiento(LocalDate.of(2000, 12, 25));
        when(titularRepo.findById(titular.getDni())).thenReturn(Optional.of(titular));
        titular.setFechaLicenciaClaseB(LocalDate.of(2020, 3, 11));
        Assertions.assertTrue(licenciaService.puedeEmitirLicencia(titular.getDni(), ClaseLicencia.D));
    }
    @Test
    public void NoPuedeEmitirLicenciaProfesionalSiEsMayorA65YNuncaTuvoLicenciaProfesional() {
        titular.setFechaNacimiento(LocalDate.of(1960, 1, 25));
        when(titularRepo.findById(titular.getDni())).thenReturn(Optional.of(titular));
        titular.setTuvoLicenciaProfesional(false);
        Assertions.assertFalse(licenciaService.puedeEmitirLicencia(titular.getDni(), ClaseLicencia.C));
    }
    @Test
    public void puedeEmitirLicenciaProfesionalSiEsMayorA65YTuvoLicenciaProfesional() {
        titular.setFechaNacimiento(LocalDate.of(1959, 10, 27));
        when(titularRepo.findById(titular.getDni())).thenReturn(Optional.of(titular));
        titular.setTuvoLicenciaProfesional(true);
        Assertions.assertTrue(licenciaService.puedeEmitirLicencia(titular.getDni(), ClaseLicencia.C));
    }
    @Test
    public void noPuedeEmitirLicenciaDeCualquierTipoSiEsMayorDe90() {
        titular.setFechaNacimiento(LocalDate.of(1924, 12, 19));
        when(titularRepo.findById(titular.getDni())).thenReturn(Optional.of(titular));
        Assertions.assertFalse(licenciaService.puedeEmitirLicencia(titular.getDni(), ClaseLicencia.F));
    }
    
}

