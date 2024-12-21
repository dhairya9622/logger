package com.dhairya.logger.Logger.logQueue;

// src/main/java/com/example/logging/service/LogQueueService.java

import com.dhairya.logger.dto.ApplicationLog;
import com.dhairya.logger.repo.ApplicationLogRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class LogQueueService {

    @Autowired
    private ApplicationLogRepository logRepository;

    private final BlockingQueue<ApplicationLog> logQueue = new LinkedBlockingQueue<>();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @PostConstruct
    public void init() {
        executor.submit(() -> {
            List<ApplicationLog> batch = new ArrayList<>();
            while (true) {
                try {
                    ApplicationLog log = logQueue.take();
                    batch.add(log);
                    logQueue.drainTo(batch, 100); // Batch size of 100
                    if (!batch.isEmpty()) {
                        logRepository.saveAll(batch);
                        batch.clear();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
    }

    public void enqueueLog(ApplicationLog log) {
        logQueue.offer(log);
    }

    // Graceful shutdown
    public void shutdown() {
        executor.shutdownNow();
    }

    @PreDestroy
    public void preDestroy() {
        shutdown();
    }
}
