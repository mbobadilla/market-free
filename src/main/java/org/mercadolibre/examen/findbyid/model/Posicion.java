package org.mercadolibre.examen.findbyid.model;

/**
 * Representa la posicion de una posicion geolocada
 */
public class Posicion {

    private float latitud;
    private float longitud;

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }
}
