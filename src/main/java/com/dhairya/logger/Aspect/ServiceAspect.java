package com.dhairya.logger.Aspect;

// src/main/java/com/example/logging/aspect/ServiceAspect.java

import com.dhairya.logger.dto.RequestLogContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

    @Autowired
    private RequestLogContext logContext;

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void service() {}

    @Before("service() && execution(* *(..))")
    public void beforeService(JoinPoint joinPoint) {
        logContext.setServiceClass(joinPoint.getSignature().getDeclaringTypeName());
        logContext.setServiceMethod(joinPoint.getSignature().getName());
    }
}
