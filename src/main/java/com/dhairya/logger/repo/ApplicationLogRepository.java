package com.dhairya.logger.repo;

// src/main/java/com/example/logging/repository/ApplicationLogRepository.java

import com.dhairya.logger.dto.ApplicationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class ApplicationLogRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_LOG_SQL = "INSERT INTO application_logs (correlation_id, timestamp, level, logger, message, exception, thread, api_name, controller_class, controller_method, service_class, service_method, repository_class, repository_method) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public void insert() {
        String sql = """
                       insert into sample (name) values ('d');
                """;
        jdbcTemplate.update(sql);
    }
    public void save(ApplicationLog log) {
        jdbcTemplate.update(INSERT_LOG_SQL,
                log.getCorrelationId(),
                Timestamp.valueOf(log.getTimestamp()),
                log.getLevel(),
                log.getLogger(),
                log.getMessage(),
                log.getException(),
                log.getThread(),
                log.getApiName(),
                log.getControllerClass(),
                log.getControllerMethod(),
                log.getServiceClass(),
                log.getServiceMethod(),
                log.getRepositoryClass(),
                log.getRepositoryMethod()
        );
    }

    // Optional: Batch Insert Method
    public void saveAll(List<ApplicationLog> logs) {
        jdbcTemplate.batchUpdate(INSERT_LOG_SQL, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ApplicationLog log = logs.get(i);
                ps.setString(1, log.getCorrelationId());
                ps.setTimestamp(2, Timestamp.valueOf(log.getTimestamp()));
                ps.setString(3, log.getLevel());
                ps.setString(4, log.getLogger());
                ps.setString(5, log.getMessage());
                ps.setString(6, log.getException());
                ps.setString(7, log.getThread());
                ps.setString(8, log.getApiName());
                ps.setString(9, log.getControllerClass());
                ps.setString(10, log.getControllerMethod());
                ps.setString(11, log.getServiceClass());
                ps.setString(12, log.getServiceMethod());
                ps.setString(13, log.getRepositoryClass());
                ps.setString(14, log.getRepositoryMethod());
            }

            @Override
            public int getBatchSize() {
                return logs.size();
            }
        });
    }
}
