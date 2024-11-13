package com.purepoint.springbootpurepoint.auth.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.purepoint.springbootpurepoint.auth.dto.response.UserResponseDto;
import com.purepoint.springbootpurepoint.user.dto.request.UserCreateDto;
import com.purepoint.springbootpurepoint.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

// TODO : 나중에 Openfeign 으로 변경할 것.

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{
    
    private final UserService userService;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    @Override
    public UserResponseDto handleGoogleCallback(String authCode) {

        // 받은 인증 코드를 통해 구글 OAuth (엑세스, 리프레시)토큰으로 교환
        String OauthTokenUrl = "https://oauth2.googleapis.com/token";
        UserResponseDto userResponseDto = null;
        
        try {
            GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    JacksonFactory.getDefaultInstance(),
                    OauthTokenUrl,
                    googleClientId,
                    googleClientSecret,
                    authCode,
                    "YOUR_REDIRECT_URI"
            ).execute();

            String accessToken = tokenResponse.getAccessToken();
            String refreshToken = tokenResponse.getRefreshToken();

            // OAuth 토큰으로 사용자 조회

            // 아이디,  회원가입

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Map<String, Object> getUserInfo(String accessToken) {
        String userInfoEndpointUri = "https://www.googleapis.com/oauth2/v3/userinfo";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to fetch user info");
        }
    }
}