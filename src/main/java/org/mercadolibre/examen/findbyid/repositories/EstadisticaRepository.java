package org.mercadolibre.examen.findbyid.repositories;

import org.mercadolibre.examen.findbyid.model.entities.Estadistica;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public interface EstadisticaRepository extends CrudRepository<Estadistica, UUID> {

        Stream<Estadistica> findAllBy();

}
