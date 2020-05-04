package org.mercadolibre.examen.findbyid.utils;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.time.Instant;
import java.time.LocalDateTime;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase de utilidad para manejo de Tiempo
 */
public class TimeUtils {

    private static final String DATE_FORMAT="dd-M-yyyy hh:mm:ss a";

    public static String getTimeByTimezone(String codeTimezone){
        Instant timeStamp= Instant.now();
        ZonedDateTime husoNoLocal=timeStamp.atZone(ZoneId.of(codeTimezone));
        return husoNoLocal.toString();

    }


    /**
     * Obtiene la hora actual y la devuelve como
     * un String dd-M-yyyy hh:mm:ss a
     * @return
     */
    public static String getActualTimeToString(){
        //Obtiene la hora actual
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.format(getTimeFormatter());
    }

    /**
     * Dada un hora lo devuelve como String
     * @param time
     * @return
     */
    public static String getTimeToString(LocalDateTime time){
        return time.format(getTimeFormatter());
    }

    /**
     * Devuelve el formato de la hora
     * @return
     */
    private static DateTimeFormatter getTimeFormatter(){
        return DateTimeFormatter.ofPattern(DATE_FORMAT);
    }


    private static String parseTimeZoneData(String timeZone){
        String[] elemData=timeZone.split("-");
        return elemData[0];
    }
}
