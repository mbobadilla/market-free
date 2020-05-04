package org.mercadolibre.examen.findbyid.configuration;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 *
 */

//@Configuration
//@Aspect
public class ExceptionLoggerPointcut {
    private final Logger logger=LoggerFactory.getLogger(ExceptionLoggerPointcut.class);
    //@AfterThrowing(pointcut = "execution(* org.mercadolibre.examen.findbyid.*.*.*(..))", throwing = "ex")
    public void logError(Exception ex){
        ex.printStackTrace();

        logger.error(ex.getMessage());
    }
}
