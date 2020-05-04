package org.mercadolibre.examen.findbyid.exceptions;
/**
 * Implementa el mensaje especifico de IPV4
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class IpV4BadFormat implements TipoIpI {

    private final String message;

    public IpV4BadFormat(String msg){
        this.message=msg;
    }


    @Override
    public String msg() {
        return message;
    }
}
