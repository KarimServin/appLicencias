package com.municipalidad.licencias.appLicencias.service.serviceImpl;

import com.municipalidad.licencias.appLicencias.dto.ComprobanteDTO;
import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import com.municipalidad.licencias.appLicencias.entities.Comprobante;
import com.municipalidad.licencias.appLicencias.entities.Licencia;
import com.municipalidad.licencias.appLicencias.exception.ServiceException;
import com.municipalidad.licencias.appLicencias.repository.ComprobanteRepository;
import com.municipalidad.licencias.appLicencias.repository.LicenciaRepository;
import com.municipalidad.licencias.appLicencias.service.ComprobanteService;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ComprobanteServiceImpl implements ComprobanteService {

    private final ComprobanteRepository comprobanteRepository;
    private final LicenciaRepository licenciaRepository;

    public ComprobanteServiceImpl(ComprobanteRepository comprobanteRepository,
                                  LicenciaRepository licenciaRepository) {
        this.comprobanteRepository = comprobanteRepository;
        this.licenciaRepository = licenciaRepository;
    }

    @Override
    @Transactional
    public ComprobanteDTO generarComprobante(LicenciaDTO licenciaDTO, int monto, String concepto) {
        Licencia licencia = licenciaRepository.findById(licenciaDTO.getId())
                .orElseThrow(() -> new ServiceException("Licencia no encontrada."));

        Comprobante comprobante = Comprobante.builder()
                .fecha(LocalDate.now())
                .monto(monto)
                .concepto(concepto)
                .licencia(licencia)
                .build();

        Comprobante guardado = comprobanteRepository.save(comprobante);

        return ComprobanteDTO.builder()
                .id(guardado.getId())           // ✅ número de comprobante real
                .fecha(guardado.getFecha())
                .monto(guardado.getMonto())
                .concepto(guardado.getConcepto())
                .licenciaId(licenciaDTO.getId())
                .build();
    }
}
