package org.mercadolibre.examen.findbyid.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
/**
 * Implementa el mensaje especifico de IPV6
 */
import java.util.Locale;

public class IpV6BadFormat implements TipoIpI {

    private final String message;

    public IpV6BadFormat(String msg){
        this.message=msg;
    }


    @Override
    public String msg() {
        return message;
    }
}
