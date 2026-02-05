package com.municipalidad.licencias.appLicencias.service.serviceImpl;



import com.municipalidad.licencias.appLicencias.dto.LicenciaDTO;
import com.municipalidad.licencias.appLicencias.entities.Licencia;
import com.municipalidad.licencias.appLicencias.mapper.LicenciaMapper;
import com.municipalidad.licencias.appLicencias.modules.consultarlicencias.ConsultarLicenciasFiltroDTO;
import com.municipalidad.licencias.appLicencias.repository.LicenciaRepository;
import com.municipalidad.licencias.appLicencias.service.LicenciaConsultaService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LicenciaConsultaServiceImpl implements LicenciaConsultaService {

    private final LicenciaMapper licenciaMapper;

    @PersistenceContext
    private EntityManager em;

    public LicenciaConsultaServiceImpl(LicenciaRepository licenciaRepository,
                                       LicenciaMapper licenciaMapper) {
        this.licenciaMapper = licenciaMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LicenciaDTO> buscar(ConsultarLicenciasFiltroDTO f) {
        if (f == null) throw new IllegalArgumentException("Filtros requeridos.");

        LocalDate hoy = LocalDate.now();

        // JPQL dinámico (senior: 1 query, fetch join, sin N+1)
        StringBuilder jpql = new StringBuilder("""
            select distinct l
            from Licencia l
            left join fetch l.clases lc
            where 1=1
        """);

        List<Param> params = new ArrayList<>();

        // DNI
        if (f.getDniTitular() != null) {
            jpql.append(" and l.titular.dni = :dni ");
            params.add(new Param("dni", f.getDniTitular()));
        }

        // Estado
        if (f.getEstado() != null) {
            switch (f.getEstado()) {
                case VIGENTES -> {
                    jpql.append(" and l.fechaVencimiento > :hoy ");
                    params.add(new Param("hoy", hoy));
                }
                case EXPIRADAS -> {
                    jpql.append(" and l.fechaVencimiento <= :hoy ");
                    params.add(new Param("hoy", hoy));
                }
                case VENCEN_PRONTO -> {
                    // hoy < vencimiento <= hoy + N
                    if (f.getVencenEnDias() == null) {
                        throw new IllegalArgumentException("vencenEnDias requerido para 'VENCEN_PRONTO'.");
                    }
                    LocalDate hasta = hoy.plusDays(f.getVencenEnDias());
                    jpql.append(" and l.fechaVencimiento > :hoy and l.fechaVencimiento <= :hasta ");
                    params.add(new Param("hoy", hoy));
                    params.add(new Param("hasta", hasta));
                }
                case TODAS -> {
                    // sin filtro por estado
                }
            }
        }

        // Filtro por clase (si se eligió una)
        if (f.getClase() != null) {
            // Como ya tenemos join fetch l.clases lc, filtramos por lc
            jpql.append(" and lc.claseLicencia = :clase ");
            params.add(new Param("clase", f.getClase()));
        }

        // Rangos por emisión
        if (f.getEmisionDesde() != null) {
            jpql.append(" and l.fechaEmision >= :emisionDesde ");
            params.add(new Param("emisionDesde", f.getEmisionDesde()));
        }
        if (f.getEmisionHasta() != null) {
            jpql.append(" and l.fechaEmision <= :emisionHasta ");
            params.add(new Param("emisionHasta", f.getEmisionHasta()));
        }

        // Rangos por vencimiento
        if (f.getVencimientoDesde() != null) {
            jpql.append(" and l.fechaVencimiento >= :vencDesde ");
            params.add(new Param("vencDesde", f.getVencimientoDesde()));
        }
        if (f.getVencimientoHasta() != null) {
            jpql.append(" and l.fechaVencimiento <= :vencHasta ");
            params.add(new Param("vencHasta", f.getVencimientoHasta()));
        }

        jpql.append(" order by l.fechaVencimiento asc, l.fechaEmision desc ");

        TypedQuery<Licencia> q = em.createQuery(jpql.toString(), Licencia.class);
        for (Param p : params) q.setParameter(p.name, p.value);

        List<Licencia> result = q.getResultList();
        return result.stream().map(licenciaMapper::toDTO).toList();
    }

    private static class Param {
        final String name;
        final Object value;
        Param(String name, Object value) { this.name = name; this.value = value; }
    }
}