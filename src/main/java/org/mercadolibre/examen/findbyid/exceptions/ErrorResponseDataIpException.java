package org.mercadolibre.examen.findbyid.exceptions;

/**
 * Maneja errores de peticion de datos por IP
 */
public class ErrorResponseDataIpException extends Exception{
    public ErrorResponseDataIpException(String message) {
        super(message);
    }
}
