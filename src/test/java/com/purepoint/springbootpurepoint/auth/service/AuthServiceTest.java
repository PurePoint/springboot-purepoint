package com.purepoint.springbootpurepoint.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AuthServiceTest {

    @Autowired
    private AuthService authService;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String googleRedirectUri;

    @Test
    @DisplayName( "Google OAuth Callback Test")
    public void handleGoogleCallbackTest() {
        String authCode = "4/0AeanS0YrZiPCWGZAHyirt9B-EEMrDsf4olAfBq80meVG5kmVdXiLv9YhMNL0DfZzJEPvkg";

        authService.handleGoogleCallback(authCode);

    }
}
