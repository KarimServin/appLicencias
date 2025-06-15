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
    public void calcularCostoLicenciaAVigencia5Anios(){
    titular.setFechaNacimiento(LocalDate.of(2000, 1, 22));
    licencia.setClaseLicencia(ClaseLicencia.A);
    Assertions.assertEquals(48, licenciaService.calcularCosto(licencia));
    }
    @Test
    public void calcularCostoLicenciaAVigencia1Anios(){
    titular.setFechaNacimiento(LocalDate.of(2008, 1, 22));
    licencia.setClaseLicencia(ClaseLicencia.A);
    Assertions.assertEquals(28, licenciaService.calcularCosto(licencia));
    }
    @Test
    public void calcularCostoLicenciaBVigencia4Anios(){
    titular.setFechaNacimiento(LocalDate.of(1975, 1, 22));
    licencia.setClaseLicencia(ClaseLicencia.B);
    Assertions.assertEquals(38, licenciaService.calcularCosto(licencia));
    }
    @Test
    public void calcularCostoLicenciaBVigencia3Anios(){
    titular.setFechaNacimiento(LocalDate.of(1960, 1, 22));
    licencia.setClaseLicencia(ClaseLicencia.A);
    Assertions.assertEquals(33, licenciaService.calcularCosto(licencia));
    }
    @Test
    public void calcularCostoLicenciaCVigencia5Anios(){
    titular.setFechaNacimiento(LocalDate.of(2001, 8, 22));
    licencia.setClaseLicencia(ClaseLicencia.C);
    Assertions.assertEquals(55, licenciaService.calcularCosto(licencia));
    }
    @Test
    public void calcularCostoLicenciaCVigencia4Anios(){
    titular.setFechaNacimiento(LocalDate.of(1974, 8, 12));
    licencia.setClaseLicencia(ClaseLicencia.C);
    Assertions.assertEquals(43, licenciaService.calcularCosto(licencia));
    }
    @Test
    public void calcularCostoLicenciaDVigencia5Anios(){
    titular.setFechaNacimiento(LocalDate.of(2000, 8, 12));
    licencia.setClaseLicencia(ClaseLicencia.D);
    Assertions.assertEquals(108, licenciaService.calcularCosto(licencia));
    }
    @Test
    public void calcularCostoLicenciaDVigencia1Anios(){
    titular.setFechaNacimiento(LocalDate.of(1950, 8, 12));
    licencia.setClaseLicencia(ClaseLicencia.D);
    Assertions.assertEquals(58, licenciaService.calcularCosto(licencia));
    }
    @Test
    public void calcularCostoLicenciaEVigencia5Anios(){
    titular.setFechaNacimiento(LocalDate.of(2000, 8, 12));
    licencia.setClaseLicencia(ClaseLicencia.E);
    Assertions.assertEquals(67, licenciaService.calcularCosto(licencia));
    }
    @Test
    public void calcularCostoLicenciaEVigencia1Anios(){
    titular.setFechaNacimiento(LocalDate.of(1951, 12, 12));
    licencia.setClaseLicencia(ClaseLicencia.E);
    Assertions.assertEquals(37, licenciaService.calcularCosto(licencia));
    }
    @Test
    public void calcularCostoLicenciaFVigencia5Anios(){
    titular.setFechaNacimiento(LocalDate.of(2002, 8, 12));
    licencia.setClaseLicencia(ClaseLicencia.F);
    Assertions.assertEquals(28, licenciaService.calcularCosto(licencia));
    }
    @Test
    public void calcularCostoLicenciaFVigencia1Anios(){
    titular.setFechaNacimiento(LocalDate.of(1952, 4, 12));
    licencia.setClaseLicencia(ClaseLicencia.F);
    Assertions.assertEquals(13, licenciaService.calcularCosto(licencia));
    }
    @Test
    public void calcularCostoLicenciaGVigencia5Anios(){
    titular.setFechaNacimiento(LocalDate.of(2002, 8, 12));
    licencia.setClaseLicencia(ClaseLicencia.G);
    Assertions.assertEquals(48, licenciaService.calcularCosto(licencia));
    }
    @Test
    public void calcularCostoLicenciaGVigencia1Anios(){
    titular.setFechaNacimiento(LocalDate.of(1950, 1, 1));
    licencia.setClaseLicencia(ClaseLicencia.G);
    Assertions.assertEquals(28, licenciaService.calcularCosto(licencia));
    }
}
