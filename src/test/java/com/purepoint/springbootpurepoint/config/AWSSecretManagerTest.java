package com.purepoint.springbootpurepoint.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class AWSSecretManagerTest {

    @Value("${youtube.api.key}")
    private String youtubeApiKey;

    @Value("${spring.datasource.driver-class-name}")
    private String dataSourceDriverClassName;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUsername;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    @Test
    void secretManagerTest() {
        // Test logic using the injected secret values
        log.info("YouTube API Key: {}", youtubeApiKey);
        log.info("Database Driver: {}", dataSourceDriverClassName);
        log.info("Database URL: {}", dataSourceUrl);
        log.info("Database Username: {}", dataSourceUsername);
        log.info("Database Password: {}", dataSourcePassword);
    }
}
