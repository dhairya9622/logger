package com.dhairya.logger.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
public class RequestLogContext {
    private String correlationId;
    private String controllerClass;
    private String controllerMethod;
    private String serviceClass;
    private String serviceMethod;
    private String repositoryClass;
    private String repositoryMethod;
    private String apiName;
    private String exception;

    // Getters and Setters
    // ...
}
