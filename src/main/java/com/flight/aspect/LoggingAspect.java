package com.flight.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(com.flight.aspect.ServiceAnnotation)")
    public Object logRequestAndResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.info("Method Name: " + joinPoint.getSignature().getName());
        if (joinPoint.getArgs().length > 0 && joinPoint.getArgs() != null) {
            logger.info("Request Body: " + joinPoint.getArgs()[0].toString());
        }
        Object response = joinPoint.proceed();
        logger.info("Response: " + response.toString());
        return response;
    }
}
