package org.mercadolibre.examen.findbyid.model.entities;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Objects;
import java.util.UUID;

@Table(value = "distancia")
public class Estadistica {
    @PrimaryKey(value = "id")
    private UUID id;

    @Column(value = "pais")
    private String pais;

    @Column(value = "distanciamayor")
    private Double distanciaMayor;

    @Column(value = "distanciamenor")
    private Double distanciaMenor;

    @Column(value = "invocaciones")
    private Long invocaciones;

    public Estadistica() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Double getDistanciaMayor() {
        return distanciaMayor;
    }

    public void setDistanciaMayor(Double distanciaMayor) {
        this.distanciaMayor = distanciaMayor;
    }

    public Double getDistanciaMenor() {
        return distanciaMenor;
    }

    public void setDistanciaMenor(Double distanciaMenor) {
        this.distanciaMenor = distanciaMenor;
    }

    public Long getInvocaciones() {
        return invocaciones;
    }

    public void setInvocaciones(Long invocaciones) {
        this.invocaciones = invocaciones;
    }
}

