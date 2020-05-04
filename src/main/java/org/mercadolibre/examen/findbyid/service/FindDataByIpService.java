package org.mercadolibre.examen.findbyid.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mercadolibre.examen.findbyid.exceptions.ErrorResponseDataIpException;
import org.mercadolibre.examen.findbyid.exceptions.ParsingException;
import org.mercadolibre.examen.findbyid.exceptions.PersistenceException;
import org.mercadolibre.examen.findbyid.model.DataByIp;
import org.mercadolibre.examen.findbyid.model.IpModelData;
import org.mercadolibre.examen.findbyid.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Clase de servicio para manejar el proceso de construir  la informacion provenientes de los servicios externos
 */

@Service
public class FindDataByIpService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CalculaDistanciaService calculaDistanciaService;

    @Autowired
    private ConsultaEstadisticaService consultaEstadisticaService;



    @Value("${url.ip2info}")
    private String urlIpService;
    @Value("${url.restcountries.eu}")
    private String urlDataCountry;

    @Value("${url.fixer.io}")
    private String currency;

    /**
     * Invoca el servicio de paises
     * @param ip la ip a preocesar
     * @return retorna el Json de servicio invocado
     */
    private String countriesResponse(String ip) throws ErrorResponseDataIpException {
        IpModelData dataIp=null;
        try{
            dataIp = restTemplate.getForObject(urlIpService.concat(ip), IpModelData.class);
            return restTemplate.getForObject(urlDataCountry.concat(dataIp.getCountryCode3()), String.class);
        }catch (Exception e) {
            if (e.getCause() == null) {
                return restTemplate.getForObject(urlDataCountry.concat(dataIp.getCountryCode()), String.class);
            } else {
                throw new ErrorResponseDataIpException("${lang.dataIp.bad.response}");
            }

        }
    }


    /**
     * Inicia el proceso de obtener datos dada una IP
     * @param ip ip a procesar
     * @return
     * @throws ParsingException
     * @throws PersistenceException
     */
    public DataByIp findDataIp(String ip) throws ParsingException, ErrorResponseDataIpException, PersistenceException {
        //Invoca los servicios externos de informacion
        String dataCurrency = restTemplate.getForObject(currency, String.class);
        String jsonCountry = "".concat(countriesResponse(ip));


        assert dataCurrency != null;
        String jsonMoneda = "".concat(dataCurrency);
        DataByIp country=null;
        try {
            //Inicia el proceso de parsear la información relacionada con los paises.
            ParseInputJsonService parseInputCountryJson=new ParseInputJsonService(jsonCountry);
            country=parseInputCountryJson.procJsonCountry();

            //Inicia el proceso de parsear la informacion relacionada con las monedas
            ParseInputJsonService parseInputCurrencyJson=new ParseInputJsonService(jsonMoneda);
            country.setCotizDolar(parseInputCurrencyJson.procJsonCurrency(country.getMonedaLocal().get(0)));


            //Inicia el proceso de construir los TimeZones
            List<String> timeZones= new ArrayList<>();
            Iterator it=country.getTimeZone().elements();
            while(it.hasNext()){
                JsonNode timeZone= (JsonNode) it.next();
                timeZones.add(TimeUtils.getTimeByTimezone(timeZone.asText()));
            }

            country.setHorariosTime(timeZones);

            //inicia el proceso de calcular las distancias en función a la lat y long
            country.setDistanciaIps(calculaDistanciaService.procesarDistanciaHaversine(country.getCoordenada()));
            try {
                consultaEstadisticaService.setDataToStore(country);
            } catch (PersistenceException e) {
                throw new PersistenceException(e.getMessage());
            }

        } catch (JsonProcessingException e) {
           throw new ParsingException(e.getMessage());
        }

        // Contiene los elementos para mostrar los datos
        return country;

    }


    /**
     *Busca el nodo de los lenguajes en el JSON de los servicios
     * @param json
     * @return
     */
    private JsonNode findLanguages(String json) {
        ObjectMapper mapper =new ObjectMapper();

        try {
            JsonNode rootNode =
                    mapper.readTree(json);
            JsonNode language=rootNode.get("latlng");
            rootNode.get(0).get("languages").elements().next().get("name");
            return language.get("name");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }



}
