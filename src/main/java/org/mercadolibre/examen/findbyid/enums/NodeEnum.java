package org.mercadolibre.examen.findbyid.enums;

public enum NodeEnum {
    codISOPais("alpha3Code"), languages("nativeName"), time_by_timezone("timezones"), currencies("code"), moneda("rates"), coordenada("latlng");

    private final String attrNode;

    NodeEnum(String attr) {
        this.attrNode = attr;
    }

    public String getAttrNode() {
        return attrNode;
    }
}
