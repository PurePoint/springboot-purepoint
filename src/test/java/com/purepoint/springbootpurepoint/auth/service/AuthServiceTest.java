package com.purepoint.springbootpurepoint.auth.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.purepoint.springbootpurepoint.auth.dto.response.UserResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
@Slf4j
public class AuthServiceTest {

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String googleRedirectUri;

    @Test
    @DisplayName( "Google OAuth Callback Test")
    public void handleGoogleCallbackTest() {
        String OauthTokenUrl = "https://oauth2.googleapis.com/token";
        String authCode = "4/0AeanS0ZTgBO720p4svVJ5KLIr-ArKqtr903YLOY72tgUgOKvp1nmlW-AcoBsF_Xz_tqIuA";

        try {
            GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    JacksonFactory.getDefaultInstance(),
                    OauthTokenUrl,
                    googleClientId,
                    googleClientSecret,
                    authCode,
                    googleRedirectUri
            ).execute();

            String accessToken = tokenResponse.getAccessToken();
            String refreshToken = tokenResponse.getRefreshToken();

            log.info("accessToken : {}", accessToken);
            log.info("refreshToken : {}", refreshToken);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
