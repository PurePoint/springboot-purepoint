package com.purepoint.springbootpurepoint.oauth.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class OOAuthServiceTest {

    @Autowired
    private OAuthService authService;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String googleRedirectUri;

    // 작성중
    @Test
    @DisplayName( "Google OAuth Callback Test")
    public void handleGoogleCallbackTest() {
        // 임의의 AuthCode
        String authCode = "4/0AeanS0YFgAdWtJYtdaFdBZwUMGnfrBozXQVhBVuQk5s8Pglo2-8PUSNhuTsMEZdwgF50JA";

        // 소션 로그인 서비스 확인
        authService.handleGoogleCallback(authCode);
        
        // 작성중
    }
}
