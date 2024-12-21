package com.dhairya.logger.controller;

import com.dhairya.logger.dto.RequestLogContext;
import com.dhairya.logger.repo.ApplicationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private RequestLogContext requestLogContext;

    @Autowired
    private ApplicationLogRepository repository;

    @PostMapping("api")
    public String hello(@RequestBody String request) {
        requestLogContext.setControllerClass("hello");
        repository.insert();
        try {
            return String.valueOf(request);
        } catch (Exception e) {
            requestLogContext.setException(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
