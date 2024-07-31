package com.smartera.productservice.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

//                   "return-type package.class.methodName(args)"
    @Before("execution(* com.smartera.productservice.service.*.*(..))")
    public void logMethodCalled(JoinPoint joinPoint){
        LOGGER.info("Method called : "+joinPoint.getSignature().getName());
    }
    @After("execution(* com.smartera.productservice.service.*.*(..))")
    public void logMethodExecuted(JoinPoint joinPoint){
        LOGGER.info("Method executed : "+joinPoint.getSignature().getName());
    }
    @AfterThrowing("execution(* com.smartera.productservice.service.*.*(..))")
    public void logMethodCrashed(JoinPoint joinPoint){
        LOGGER.info("Method has some issues : "+joinPoint.getSignature().getName());
    }
    @AfterReturning("execution(* com.smartera.productservice.service.*.*(..))")
    public void logMethodExecutedSuccess(JoinPoint joinPoint){
        LOGGER.info("Method executed successfully : "+joinPoint.getSignature().getName());
    }
}
