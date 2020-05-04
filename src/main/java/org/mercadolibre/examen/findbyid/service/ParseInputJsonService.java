package org.mercadolibre.examen.findbyid.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.mercadolibre.examen.findbyid.enums.NodeEnum;
import org.mercadolibre.examen.findbyid.model.Coordenada;
import org.mercadolibre.examen.findbyid.model.DataByIp;
import org.mercadolibre.examen.findbyid.utils.TimeUtils;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Procesa el Json para producir la informacion a mostrar
 */
@Service
public class ParseInputJsonService {
    private ObjectMapper mapper;
    private JsonNode rootNode;

    public ParseInputJsonService() {
    }

    public ParseInputJsonService(String jsonData) throws JsonProcessingException {
        this.mapper = new ObjectMapper();
        rootNode = mapper.readTree(jsonData);
    }

    /**
     * Procesa la informacion Json relacionada con paises
     *
     * @return
     */
    public DataByIp procJsonCountry() {

        Map<String, Object> nodeMap = parseDataInput();
        DataByIp dataByIp = getDataByIP(nodeMap);
        dataByIp.setCoordenada(getCoordenada((JsonNode) nodeMap.get(NodeEnum.coordenada.name())));

        return dataByIp;

    }

    /**
     * Construye el objeto Coordenada
     *
     * @param ltlng
     * @return
     */
    private Coordenada getCoordenada(JsonNode ltlng) {

        Iterator it = ltlng.elements();

        List<String> coord = new ArrayList<>();
        while (it.hasNext()) {
            DoubleNode dn = (DoubleNode) it.next();
            coord.add(dn.asText());
        }


        return new Coordenada(coord.get(0), coord.get(1));
    }

    /**
     * Construye el DTO con los datos procesados a mostrar
     *
     * @param nodeMap
     * @return
     */
    private DataByIp getDataByIP(Map<String, Object> nodeMap) {
        DataByIp dataByIp = new DataByIp();
        dataByIp.setCodISOPais((String) nodeMap.get(NodeEnum.codISOPais.name()));
        dataByIp.setTimeZone((ArrayNode) nodeMap.get(NodeEnum.time_by_timezone.name()));
        dataByIp.setLanguajes((ArrayList<String>) nodeMap.get(NodeEnum.languages.name()));
        dataByIp.setMonedaLocal((ArrayList<String>) nodeMap.get(NodeEnum.currencies.name()));
        dataByIp.setNombrePais((String) nodeMap.get("name"));
        return dataByIp;
    }

    /**
     * Procesa el Json proveniente de los servicios externos
     *
     * @return
     */
    private Map<String, Object> parseDataInput() {
        Map<String, Object> nodeMap = new HashMap<>();
        nodeMap.put(NodeEnum.codISOPais.name(), getNodeByNodeNumeric(NodeEnum.codISOPais.getAttrNode()).asText());
        nodeMap.put(NodeEnum.languages.name(), getNodeArray(NodeEnum.languages.name(), NodeEnum.languages.getAttrNode()));
        nodeMap.put(NodeEnum.time_by_timezone.name(), getNodeByNodeNumeric(NodeEnum.time_by_timezone.getAttrNode()));
        nodeMap.put(NodeEnum.currencies.name(), getNodeArray(NodeEnum.currencies.name(), NodeEnum.currencies.getAttrNode()));
        nodeMap.put(NodeEnum.coordenada.name(), getNodeByNodeNumeric(NodeEnum.coordenada.getAttrNode()));
        nodeMap.put("name", getNodeByNodeNumeric("name").asText());
        return nodeMap;
    }

    /**
     * @param currency TipoDto conten
     * @return
     */
    public DataByIp procJsonCurrencyOld(DataByIp currency) {
        Map<String, Object> nodeMap = new HashMap<>();
        nodeMap.put(NodeEnum.moneda.name(), getNodeByNodeByName(NodeEnum.moneda.getAttrNode(), currency.getMonedaLocal().get(0)));
        currency.setCotizDolar(nodeMap.get(NodeEnum.moneda.name()));
        return currency;
    }

    /**
     * Obtiene la mondeda del Json parseado
     * @param currency
     * @return
     */
    public String procJsonCurrency(String currency) {
        return getNodeByNodeByName(NodeEnum.moneda.getAttrNode(), currency).asText();

    }

    /**
     * @param timeZone
     * @return
     */
    public String getTimeByTimezone(String timeZone) {
        return TimeUtils.getTimeByTimezone(timeZone);

    }

    /**
     * Dado un nombre de nodo y nombre de atributo devuelve el nodo correspondiente
     *
     * @param nodeName
     * @param atrName
     * @return
     */
    private ArrayList<String> getNodeArray(String nodeName, String atrName) {
        Iterator it = rootNode.get(0).get(nodeName).elements();
        ArrayList<String> data = new ArrayList<>();
        while (it.hasNext()) {
            ObjectNode obj = (ObjectNode) it.next();
            data.add(obj.get(atrName).asText());

        }
        return data;
    }

    /**
     * @param nodeName
     * @return
     */
    private JsonNode getNodeByNodeNumeric(String nodeName) {

        return rootNode.get(0).get(nodeName);
    }


    /**
     * @param nodeName
     * @param elementName
     * @return
     */
    private JsonNode getNodeByNodeByName(String nodeName, String elementName) {
        return rootNode.get(nodeName).get(elementName);

    }

}



