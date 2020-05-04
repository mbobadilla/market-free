package org.mercadolibre.examen.findbyid.model;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 *
 */
public class IpModel  {
    @NotBlank
    private  String ip;
    private String errorValidation;
    private String resultado;
    private DataByIp dataIp;


    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


    public String getErrorValidation() {
        return errorValidation;
    }

    public void setErrorValidation(String errorValidation) {
        this.errorValidation = errorValidation;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public DataByIp getDataIp() {
        return dataIp;
    }

    public void setDataIp(DataByIp dataIp) {
        this.dataIp = dataIp;
    }
}
