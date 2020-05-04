package org.mercadolibre.examen.findbyid.service;

import com.datastax.driver.core.exceptions.InvalidQueryException;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.mercadolibre.examen.findbyid.exceptions.PersistenceException;
import org.mercadolibre.examen.findbyid.model.DataByIp;
import org.mercadolibre.examen.findbyid.model.entities.Estadistica;
import org.mercadolibre.examen.findbyid.repositories.EstadisticaRepository;

import org.mercadolibre.examen.findbyid.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;

import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Da servicio a la consulta de estadisticas de utilizacion de servicios
 */
@Service
public class ConsultaEstadisticaService {

    @Autowired
    private EstadisticaRepository estadisticaRepository;

    private long invocaciones;

    @Autowired
    private CassandraOperations cassandraTemplate;

    /**
     * Distancia más lejana a Buenos Aires desde la cual se haya consultado el servicio
     *
     * @return
     */
    public Double distancaLejana() {
        List<Estadistica> es = this.estadisticaRepository.findAllBy().sorted((o1, o2) -> o1.getDistanciaMayor().compareTo(o2.getDistanciaMayor()))
                .collect(Collectors.toList());

        return Double.valueOf(NumberUtils.fomatear(es.get(es.size() - 1).getDistanciaMayor()));

    }

    public Double distanciaCercana() {
        List<Estadistica> es = this.estadisticaRepository.findAllBy().sorted((o1, o2) -> o1.getDistanciaMenor().compareTo(o2.getDistanciaMenor()))
                .collect(Collectors.toList());

        return Double.valueOf(NumberUtils.fomatear(es.get(0).getDistanciaMenor()));

    }

    public Long distanciaPromedio() {
        long promedioDistancia = 0;
        long totalInvocaciones = 0;
        int countPaises = 0;
        for (Iterator it = estadisticaRepository.findAll().iterator(); it.hasNext(); ) {
            countPaises++;
            Estadistica estadistica = (Estadistica) it.next();
            promedioDistancia += ((estadistica.getDistanciaMayor().longValue() + estadistica.getDistanciaMenor().longValue()) / 2) * estadistica.getInvocaciones().longValue();
            totalInvocaciones += estadistica.getInvocaciones().longValue();
        }

        return Long.valueOf((promedioDistancia / totalInvocaciones));
    }

    private Estadistica getPais(String pais) {
        Select.Where select = QueryBuilder.select().from("distancia").where(QueryBuilder.eq("pais", pais));
        return cassandraTemplate.selectOne(select.allowFiltering(), Estadistica.class);

    }

    /**
     * @param data
     */
    public void setDataToStore(DataByIp data) throws PersistenceException {

        Estadistica previa = this.getPais(data.getNombrePais());


        try {
            if (previa != null) {
                previa.setInvocaciones(previa.getInvocaciones().longValue() + 1);
                this.evaluarDistancias(previa, data.getDistanciaIps());
                estadisticaRepository.save(previa);
            } else {
                Estadistica estadistica = new Estadistica();
                estadistica.setId(UUID.randomUUID());
                estadistica.setPais(data.getNombrePais());
                estadistica.setDistanciaMayor(data.getDistanciaIps());
                estadistica.setDistanciaMenor(data.getDistanciaIps());
                estadistica.setInvocaciones(new Long(1));
                estadisticaRepository.save(estadistica);
            }
        } catch (InvalidQueryException e) {
            throw new PersistenceException(e.getMessage());
        }
    }

    /**
     * @return
     */

    private long getInvocaciones() {

        return this.invocaciones++;
    }

    /**
     * @param estadistica
     * @param distanciaIpActual
     */
    private void evaluarDistancias(Estadistica estadistica, Double distanciaIpActual) {
        Double mayor = estadistica.getDistanciaMayor();
        Double menor = estadistica.getDistanciaMenor();
        Double ipActual = distanciaIpActual;

        if (ipActual.longValue() > mayor) {
            estadistica.setDistanciaMayor(ipActual);
        }

        if (ipActual.longValue() < menor) {
            estadistica.setDistanciaMenor(ipActual);
        }
    }
}

