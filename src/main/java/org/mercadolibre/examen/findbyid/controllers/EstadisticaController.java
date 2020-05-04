package org.mercadolibre.examen.findbyid.controllers;

import org.mercadolibre.examen.findbyid.service.ConsultaEstadisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controller Rest Api consultas
 */
@Controller
public class EstadisticaController {

    @Autowired
    ConsultaEstadisticaService consultaEstadisticaService;

    @GetMapping("/distancia/bsas/lejana")
    public String getIPDistanciaLejanas(Model model) {
        model.addAttribute("distancia","La mayor distancia del servicio fué :"+consultaEstadisticaService.distancaLejana().toString());
        return "responseEstadistica";
    }

    @GetMapping("/distancia/bsas/cercana")
    public String getIPDistanciaCercanas( Model model) {
        model.addAttribute("distancia","La menor distancia del servicio fué :"+consultaEstadisticaService.distanciaCercana().toString());
        return "responseEstadistica";
    }

    @GetMapping("/distancia/promedio/all")
    public String getGetDistanciaPromedio(Model model) {
        model.addAttribute("distancia","La distancia promedio del servicio fué :"+consultaEstadisticaService.distanciaPromedio().toString());
        return "responseEstadistica";
    }
}
