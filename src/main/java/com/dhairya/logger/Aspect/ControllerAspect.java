package com.dhairya.logger.Aspect;

// src/main/java/com/example/logging/aspect/ControllerAspect.java
import com.dhairya.logger.dto.RequestLogContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {

    @Autowired
    private RequestLogContext logContext;

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controller() {}

    @Before("controller() && execution(* *(..))")
    public void beforeController(JoinPoint joinPoint) {
        logContext.setControllerClass(joinPoint.getSignature().getDeclaringTypeName());
        logContext.setControllerMethod(joinPoint.getSignature().getName());
    }
}
