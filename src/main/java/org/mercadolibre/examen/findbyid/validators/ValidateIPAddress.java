package org.mercadolibre.examen.findbyid.validators;

import org.apache.commons.validator.routines.InetAddressValidator;
import org.mercadolibre.examen.findbyid.exceptions.InvalidIpException;
import org.mercadolibre.examen.findbyid.exceptions.IpV4BadFormat;
import org.mercadolibre.examen.findbyid.exceptions.IpV6BadFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Valida integridad estructural de  IPV4 e IPV6
 */
@Component
public class ValidateIPAddress {

    @Autowired
    private InetAddressValidator validator;
    //@Autowired
    //private MessageSource messageSource;

    public void validateIp(String ip) throws InvalidIpException {
        if (ip.contains(".")) {
            if (!validator.isValidInet4Address(ip)) {
                throw new InvalidIpException(new IpV4BadFormat("ipv4.badFormat"));
            }
        }
        if (ip.contains(":")) {
            if (!validator.isValidInet6Address(ip)) {
                throw new InvalidIpException(new IpV6BadFormat("ipv6.badFormat"));
            }
        }


    }


}

