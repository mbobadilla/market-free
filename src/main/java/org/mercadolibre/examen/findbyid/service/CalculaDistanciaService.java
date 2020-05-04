package org.mercadolibre.examen.findbyid.service;

import org.mercadolibre.examen.findbyid.model.Coordenada;
import org.mercadolibre.examen.findbyid.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

/**
 * Procesa la distancia de las distancias basadas en sus coordenadaas
 */
@Service
public class CalculaDistanciaService {


    @Value("${lat.origen}")
    private String latitudOrigen;

    @Value("${long.origen}")
    private String longitudOrigen;

    @Value("${calc.radio.ecuatorial}")
    private String radioEcuatorial;


    /**
     * Calcula la distancia entre dos puntos Geodesicos
     * usando la Formula de Haverine
     *
     * @return
     */
    public Double procesarDistanciaHaversine(Coordenada destino) {
        double prodCosenos = Math.cos(radianLatitud(Double.valueOf(latitudOrigen))) * Math.cos(radianLatitud(Double.valueOf(destino.getLatitud())));
        double prodSenos=Math.sin(radianLatitud(Double.valueOf(latitudOrigen)))*Math.sin(radianLatitud(Double.valueOf(destino.getLatitud())));
        double prodCosenoLongitud=Math.cos(radianLongitud(Double.valueOf(longitudOrigen),Double.valueOf(destino.getLongitud())));
        double distKms =Double.valueOf(radioEcuatorial)*Math.acos(prodCosenos+prodSenos*prodCosenoLongitud);
        return Double.valueOf(NumberUtils.fomatear(Double.valueOf(distKms)));//NumberUtils.formatNumber().format(distKms);
    }


    /**
     * Dada la latitud devuelve su radian
     *
     * @param lat
     * @return
     */
    private double radianLatitud(double lat) {
        return Math.toRadians(90 - lat);
    }

    /**
     * Dada longitud origen y latitud destino
     * devuelve su radian
     *
     * @param long1
     * @param long2
     * @return
     */
    private double radianLongitud(double long1, double long2) {
        return Math.toRadians(long1 - long2);
    }

    /**
     * Formatea el numero producido por el calculo a una mascara #.##
     * @return
     */
    private DecimalFormat formatNumber(){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setGroupingSize(3);
        decimalFormat.setGroupingUsed(true);
        return decimalFormat;
    }
}
