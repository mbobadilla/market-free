package org.mercadolibre.examen.findbyid.exceptions;

import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Generaliza el tipo de IP para sacar un mensaje referiendo su tipo
 */


public interface TipoIpI {
    String msg();
}
