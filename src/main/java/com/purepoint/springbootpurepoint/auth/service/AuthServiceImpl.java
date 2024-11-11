package com.purepoint.springbootpurepoint.auth.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.purepoint.springbootpurepoint.user.dto.UserDto;
import com.purepoint.springbootpurepoint.user.dto.request.UserCreateDto;
import com.purepoint.springbootpurepoint.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// TODO : 나중에 Openfeign 으로 변경할 것.

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    
    private final UserService userService;

    @Override
    public UserDto handleGoogleCallback(String authorizationCode) {

//        // 받은 인증 코드를 통해 구글 OAuth 토큰으로 교환
//        String OauthTokenUrl = "https://oauth2.googleapis.com/token";
//        GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
//                new NetHttpTransport(),
//                JacksonFactory.getDefaultInstance(),
//                OauthTokenUrl,
//                "YOUR_CLIENT_ID",
//                "YOUR_CLIENT_SECRET",
//                authorizationCode,
//                "YOUR_REDIRECT_URI"
//        ).execute();
//
//        String accessToken = tokenResponse.getAccessToken();
//        String refreshToken = tokenResponse.getRefreshToken();
//
//        // OAuth 토큰으로 사용자 조회
//        String userUrl = "https://www.googleapis.com/oauth2/v1/userinfo";
//
//
//        // 아이디,  회원가입
//        UserCreateDto dto = UserCreateDto.builder()
//                .userId()
//                .email()
//                .nickname()
//                .build();
//
        return null;
    }

}