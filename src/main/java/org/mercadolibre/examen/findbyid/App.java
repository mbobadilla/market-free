package org.mercadolibre.examen.findbyid;

import org.mercadolibre.examen.findbyid.configuration.ConfigurationConstant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        //ConfigurationConstant.IP_LOCAL=args[0];
        SpringApplication.run(App.class, args);
    }
}

