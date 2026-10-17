package com.mayank.ai.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig {

    @Bean("documentExecutor")
    public Executor documentExecutor() {

        ThreadPoolTaskExecutor executor =
                new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(2);

        executor.setMaxPoolSize(5);

        executor.setQueueCapacity(20);

        executor.setThreadNamePrefix(
                "document-worker-"
        );

        executor.initialize();

        return executor;
    }
}