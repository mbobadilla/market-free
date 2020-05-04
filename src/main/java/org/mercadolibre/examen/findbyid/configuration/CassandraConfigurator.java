package org.mercadolibre.examen.findbyid.configuration;

import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

/**
 * Configuracion global de Cassandra
 */
@EnableCassandraRepositories(basePackages = "org.mercadolibre.examen.findbyid.repositories")
public class CassandraConfigurator extends AbstractCassandraConfiguration {
    @Override
    protected String getKeyspaceName() {
        return null;
    }
}
