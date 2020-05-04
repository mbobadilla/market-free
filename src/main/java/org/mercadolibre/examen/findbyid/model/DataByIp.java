package org.mercadolibre.examen.findbyid.model;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Contiene los datos asociados a la IP requerida
 */
public class DataByIp {

    /**
     * Idiomas oficiales
     */
    private ArrayList<String> languajes;

    private String codISOPais;
    private List<String> monedaLocal;
    private Object cotizDolar;
    private ArrayNode timeZone;
    private Coordenada coordenada;
    private List<String> horariosTime;
    private Double distanciaIps;
    private String nombrePais;

    public ArrayList<String> getLanguajes() {
        return languajes;
    }

    public void setLanguajes(ArrayList<String> languajes) {
        this.languajes = languajes;
    }

    public String getCodISOPais() {
        return codISOPais;
    }

    public void setCodISOPais(String codISOPais) {
        this.codISOPais = codISOPais;
    }

    public List<String> getMonedaLocal() {
        return monedaLocal;
    }

    public void setMonedaLocal(List<String> monedaLocal) {
        this.monedaLocal = monedaLocal;
    }

    public Object getCotizDolar() {
        if(cotizDolar==null){
            return "0";
        }
        return cotizDolar;
    }

    public void setCotizDolar(Object cotizDolar) {
        this.cotizDolar = cotizDolar;
    }

    public ArrayNode getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(ArrayNode timeZone) {
        this.timeZone = timeZone;
    }



    public List<String> getHorariosTime() {
        return horariosTime;
    }

    public void setHorariosTime(List<String> horariosTime) {
        this.horariosTime = horariosTime;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public Double getDistanciaIps() {
        return distanciaIps;
    }

    public void setDistanciaIps(Double distanciaIps) {
        this.distanciaIps = distanciaIps;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }
}
