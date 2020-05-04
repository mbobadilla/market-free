package org.mercadolibre.examen.findbyid.utils;


import java.text.DecimalFormat;

/**
 * Clase de utilidad para tratar situaciones relacionadas  con números
 */
public class NumberUtils {



    public static String fomatear(Double value){
        String pattern = "###,###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);

        return decimalFormat.format(value);

    }
}
