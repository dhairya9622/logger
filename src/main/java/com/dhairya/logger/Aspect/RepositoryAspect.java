package com.dhairya.logger.Aspect;

// src/main/java/com/example/logging/aspect/RepositoryAspect.java

import com.dhairya.logger.dto.RequestLogContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepositoryAspect {

    @Autowired
    private RequestLogContext logContext;

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void repository() {}

    @Before("repository() && execution(* *(..))")
    public void beforeRepository(JoinPoint joinPoint) {
        logContext.setRepositoryClass(joinPoint.getSignature().getDeclaringTypeName());
        logContext.setRepositoryMethod(joinPoint.getSignature().getName());
    }
}

