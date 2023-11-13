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
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
    private static final String BEFORE_NAME_TEXT = "Method Name: ";
    private static final String BEFORE_BODY_TEXT = "Request Body: ";
    private static final String BEFORE_RESPONSE_TEXT = "Response: ";

    @Around("@annotation(com.flight.aspect.ServiceLoggingAspect)")
    public Object logRequestAndResponse(ProceedingJoinPoint joinPoint) throws Throwable {

        LOGGER.info("\n" + BEFORE_NAME_TEXT + joinPoint.getSignature().getName());
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0 ) {
            LOGGER.info("\n" + BEFORE_BODY_TEXT + joinPoint.getArgs()[0].toString());
        } else {
            LOGGER.info("\n" + BEFORE_BODY_TEXT + "[]");
        }
        Object response = joinPoint.proceed();
        if (response != null) {
            LOGGER.info("\n" + BEFORE_RESPONSE_TEXT + response);
        } else {
            LOGGER.info(BEFORE_RESPONSE_TEXT + "[]");
        }
        return response;
    }
}
