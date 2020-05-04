package org.mercadolibre.examen.findbyid.exceptions;

/**
 * Excepcion en el contexto persistente
 */
public class PersistenceException extends Exception{
    public PersistenceException(String message) {
        super(message);
    }
}
