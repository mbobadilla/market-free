package org.mercadolibre.examen.findbyid.exceptions;

/**
 * Excepcion para manejar Ip invalidas
 */
public class InvalidIpException extends Exception{
    public InvalidIpException(TipoIpI tipoIp){
        super(tipoIp.msg());
    }
}
