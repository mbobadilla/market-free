package org.mercadolibre.examen.findbyid.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Representa el Json devuelto por el servicio ip2country
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class IpModelData {
    private String countryCode;
    private String countryCode3;
    private String countryName;
    private String countryEmoji;


    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode3() {
        return countryCode3;
    }

    public void setCountryCode3(String countryCode3) {
        this.countryCode3 = countryCode3;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryEmoji() {
        return countryEmoji;
    }

    public void setCountryEmoji(String countryEmoji) {
        this.countryEmoji = countryEmoji;
    }

    @Override
    public String toString() {
        return "IpModelData{" +
                "countryCode='" + countryCode + '\'' +
                ", countryCode3='" + countryCode3 + '\'' +
                ", countryName='" + countryName + '\'' +
                ", countryEmoji='" + countryEmoji + '\'' +
                '}';
    }
}
