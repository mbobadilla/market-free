package org.mercadolibre.examen.findbyid.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mercadolibre.examen.findbyid.exceptions.ErrorResponseDataIpException;
import org.mercadolibre.examen.findbyid.exceptions.ParsingException;
import org.mercadolibre.examen.findbyid.exceptions.PersistenceException;
import org.mercadolibre.examen.findbyid.model.DataByIp;
import org.mercadolibre.examen.findbyid.model.IpModel;
import org.mercadolibre.examen.findbyid.service.FindDataByIpService;
import org.mercadolibre.examen.findbyid.validators.HandlingFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 *
 */


@Controller
public class FoundByIpController {



    @Autowired
    private HandlingFormValidator handlingFormValidator;

    @Autowired
    private FindDataByIpService dataIpService;



    /**
     * Carga el form de inicio de proceso
     *
     * @return
     */
    @GetMapping("/")
    public String getFormIp(Model model) {
        model.addAttribute("modelIp", new IpModel());
        return "formIp";
    }

    /**
     * Devuelve el resultado del proceso de busqueda
     * por IP
     *
     * @return
     */
    @PostMapping("/found")
    public String ipSubmit(@Valid @ModelAttribute("modelIp") IpModel modelIp, BindingResult bindingResult) throws ErrorResponseDataIpException {
        if (bindingResult.hasErrors()) {
            return "formIp";
        }

        DataByIp data=null;

        try {
            data= dataIpService.findDataIp(modelIp.getIp());
        } catch (ParsingException e) {
            return e.getMessage();
        } catch (PersistenceException e) {
            return e.getMessage();
        }
        ObjectMapper mapper = new ObjectMapper();
        //try {
            //modelIp.setResultado(//mapper.writeValueAsString(data));
            modelIp.setDataIp(data);
            return "responseDataIp";
        //} catch (JsonProcessingException e) {
         //   return e.getMessage();
        //}
    }

    @InitBinder("modelIp")
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(handlingFormValidator);
    }
}
