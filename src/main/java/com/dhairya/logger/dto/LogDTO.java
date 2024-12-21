package com.dhairya.logger.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LogDTO {
    private String correlationId;
    private String apiName;
    private String exception;
    private String controllerClass;
    private String controllerMethod;
    private String serviceClass;
    private String serviceMethod;
    private String repositoryClass;
    private String repositoryMethod;
    // Add other necessary fields
}