package com.smartera.productservice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidateAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateAspect.class);

    @Around("execution(* com.smartera.productservice.service.*.findById(..)) && args(id)"+
            "|| execution(* com.smartera.productservice.service.*.deleteById(..)) && args(id)")
    public Object validateAndUpdate(ProceedingJoinPoint joinPoint,int id) throws Throwable {

        if(id<0){
            LOGGER.info("Negative id found, converting to positive : "+id);
            id = -id;
            LOGGER.info("Converted id : "+id);
        }

        Object obj = joinPoint.proceed(new Object[]{id});

        return obj;
    }

}
