package com.purepoint.springbootpurepoint.auth.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.purepoint.springbootpurepoint.auth.dto.response.UserResponseDto;
import com.purepoint.springbootpurepoint.auth.jwt.JwtTokenProvider;
import com.purepoint.springbootpurepoint.user.domain.User;
import com.purepoint.springbootpurepoint.user.dto.UserStatus;
import com.purepoint.springbootpurepoint.user.dto.request.UserCreateRequestDto;
import com.purepoint.springbootpurepoint.user.dto.response.UserLoginResponseDto;
import com.purepoint.springbootpurepoint.user.repository.UserRepository;
import com.purepoint.springbootpurepoint.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// TODO : 나중에 Openfeign 으로 변경할 것.

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String googleRedirectUri;

    @Override
    public UserResponseDto handleGoogleCallback(String authCode) {
        try {
            // 받은 인증 코드를 통해 구글 OAuth (엑세스, 리프레시)토큰으로 교환
            Map<String, Object> tokenData = getOauthToken(authCode);

            // 엑세스 토큰을 통해 사용자 정보 조회
            Map<String, Object> userInfo = getUserInfo(tokenData.get("accessToken").toString());

            // 이 프로바이더 OAuth 를 통해 가입한 유저인지 검증
            UserLoginResponseDto loginUser = userService.loginUser("google", userInfo.get("sub").toString(), userInfo.get("email").toString());

            // 만약에 사용자의 상태가 UserStatus.NEW 이면
            if (loginUser.getUserStatus().equals(UserStatus.NEW)) {
                log.info("신규 회원이 로그인을 시도했습니다.");

                // 회원가입 진행
                User createUser = User.builder()
                        .nickname(userInfo.get("name").toString())
                        .email(userInfo.get("email").toString())
                        .providerName("google")
                        .providerId(userInfo.get("sub").toString())
                        .profileImage(userInfo.get("picture").toString())
                        .build();
                userRepository.save(createUser);

                // 사용자 로그인
                UserLoginResponseDto newUser = userService.loginUser("google", userInfo.get("sub").toString(), userInfo.get("email").toString());

                // 사용자 정보로 애플리케이션의 인증 토큰을 생성합니다.
                String jwtToken = jwtTokenProvider.createToken(newUser.getUserInfo(), "user");

                return UserResponseDto.builder()
                        .Token(jwtToken)
                        .RefreshToken(tokenData.get("refreshToken").toString())
                        .loginStatus(UserStatus.ACTIVE)
                        .build();

            } else {
                // 사용자 정보로 애플리케이션의 인증 토큰을 생성합니다.
                String jwtToken = jwtTokenProvider.createToken(loginUser.getUserInfo(), "user");

                return UserResponseDto.builder()
                        .Token(jwtToken)
                        .RefreshToken(tokenData.get("refreshToken").toString())
                        .loginStatus(UserStatus.ACTIVE)
                        .build();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, Object> getOauthToken(String authCode) throws IOException {
        String OauthTokenUrl = "https://oauth2.googleapis.com/token";

        // GoogleTokenResponse 객체를 이용하여 엑세스, 리프레시 토큰을 가져옵니다.
        GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                new NetHttpTransport(),
                JacksonFactory.getDefaultInstance(),
                OauthTokenUrl,
                googleClientId,
                googleClientSecret,
                authCode,
                googleRedirectUri
        ).execute();

        // 토큰 정보를 맵에 저장합니다.
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("accessToken", tokenResponse.getAccessToken());
        tokenData.put("refreshToken", tokenResponse.getRefreshToken());
        tokenData.put("expiresIn", tokenResponse.getExpiresInSeconds());
        tokenData.put("scope", tokenResponse.getScope());

        return tokenData;
    }

    // 엑세스 토큰을 가지고 사용자 정보를 조회하고 맵 형태로 반환합니다.
    private Map<String, Object> getUserInfo(String accessToken) {
        String userInfoEndpointUri = "https://www.googleapis.com/oauth2/v3/userinfo"; // 유저 조회 URL

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