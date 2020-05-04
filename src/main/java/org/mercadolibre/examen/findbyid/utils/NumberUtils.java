package org.mercadolibre.examen.findbyid.utils;


import java.text.DecimalFormat;

/**
 * Clase de utilidad para tratar situaciones relacionadas  con números
 */
public class NumberUtils {



    public static String fomatear(Double value){

        int pos=value.toString().indexOf(".");
        return value.toString().substring(0,pos);
       // double d=Double.parseDouble(value.toString());
        //String pattern = "#,###.00";
       // DecimalFormat decimalFormat = new DecimalFormat(pattern);

        //return decimalFormat.format(d);

    }
}
