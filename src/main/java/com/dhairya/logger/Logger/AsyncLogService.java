package com.dhairya.logger.Logger;

// src/main/java/com/example/logging/service/AsyncLogService.java


import com.dhairya.logger.dto.ApplicationLog;
import com.dhairya.logger.dto.LogDTO;
import com.dhairya.logger.dto.RequestLogContext;
import com.dhairya.logger.repo.ApplicationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncLogService {

    @Autowired
    private ApplicationLogRepository logRepository;

    @Async
    public void persistLog(LogDTO context) {
        ApplicationLog log = new ApplicationLog();
        log.setCorrelationId(context.getCorrelationId());
        log.setTimestamp(java.time.LocalDateTime.now());
        log.setLevel(context.getException() == null ? "INFO" : "ERROR");
        log.setLogger("LifecycleLogger");
        log.setMessage(context.getException() == null ? "Request processed successfully" : "Request processing failed");
        log.setException(context.getException());
        log.setThread(Thread.currentThread().getName());
        log.setApiName(context.getApiName());
        log.setControllerClass(context.getControllerClass());
        log.setControllerMethod(context.getControllerMethod());
        log.setServiceClass(context.getServiceClass());
        log.setServiceMethod(context.getServiceMethod());
        log.setRepositoryClass(context.getRepositoryClass());
        log.setRepositoryMethod(context.getRepositoryMethod());

        logRepository.save(log);
    }
}
