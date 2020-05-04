package org.mercadolibre.examen.findbyid.validators;

import org.mercadolibre.examen.findbyid.exceptions.InvalidIpException;
import org.mercadolibre.examen.findbyid.model.IpModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class HandlingFormValidator implements Validator {
    @Autowired
    ValidateIPAddress validateIPAddress;
    @Override
    public boolean supports(Class<?> aClass) {
        return IpModel.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        IpModel ipModel= (IpModel) o;
        try {
            validateIPAddress.validateIp(ipModel.getIp());
        } catch (InvalidIpException e) {
            errors.rejectValue("errorValidation",e.getMessage());
        }
    }
}
