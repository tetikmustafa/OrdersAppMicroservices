package com.smartera.customerservice.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceMonitorAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceMonitorAspect.class);

    @Around("execution(* com.smartera.customerservice.service.*.*(..))")
    public Object monitorTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        Object obj = joinPoint.proceed();

        long endTime = System.currentTimeMillis();

        LOGGER.info("Method executed in "+(endTime-startTime)+" ms : "+joinPoint.getSignature().getName());

        return obj;
    }
}
