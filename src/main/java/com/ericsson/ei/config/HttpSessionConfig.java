package com.ericsson.ei.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.session.data.mongo.MongoOperationsSessionRepository;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

@EnableMongoHttpSession(collectionName="sessions")
public class HttpSessionConfig {
    
    @Value("${server.session-timeout}")
    private int maxInactiveIntervalInSeconds;
    
    @Primary
    @Bean
    public MongoOperationsSessionRepository mongoSessionRepository(MongoOperations mongoOperations) {
        MongoOperationsSessionRepository repository = new MongoOperationsSessionRepository(mongoOperations);
        repository.setMaxInactiveIntervalInSeconds(maxInactiveIntervalInSeconds);
        return repository;
    }
}