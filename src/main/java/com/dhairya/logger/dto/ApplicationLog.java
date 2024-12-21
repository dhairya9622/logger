package com.dhairya.logger.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApplicationLog {
    private Long id;
    private String correlationId;
    private LocalDateTime timestamp;
    private String level;
    private String logger;
    private String message;
    private String exception;
    private String thread;
    private String apiName;
    private String controllerClass;
    private String controllerMethod;
    private String serviceClass;
    private String serviceMethod;
    private String repositoryClass;
    private String repositoryMethod;

    // Getters and Setters
    // ...
}