package com.dhairya.logger.interceptor;

// src/main/java/com/example/logging/interceptor/LoggingInterceptor.java

import com.dhairya.logger.Logger.AsyncLogService;
import com.dhairya.logger.dto.LogDTO;
import com.dhairya.logger.dto.RequestLogContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Autowired
    private RequestLogContext logContext;

    @Autowired
    private AsyncLogService asyncLogService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logContext.setCorrelationId(UUID.randomUUID().toString());
        logContext.setApiName(request.getMethod() + " " + request.getRequestURI());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex != null) {
            logContext.setException(ex.toString());
        }

        // Create a DTO with the current log context data
        LogDTO logDTO = new LogDTO(
                logContext.getCorrelationId(),
                logContext.getApiName(),
                logContext.getException(),
                logContext.getControllerClass(),
                logContext.getControllerMethod(),
                logContext.getServiceClass(),
                logContext.getServiceMethod(),
                logContext.getRepositoryClass(),
                logContext.getRepositoryMethod()
                // Add other necessary fields
        );

        // Pass the DTO to the async method
        asyncLogService.persistLog(logDTO);
    }
}
