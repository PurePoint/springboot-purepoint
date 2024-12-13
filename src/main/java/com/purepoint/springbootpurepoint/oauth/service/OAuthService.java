package com.purepoint.springbootpurepoint.oauth.service;

import com.purepoint.springbootpurepoint.oauth.dto.response.OAuthResponseDto;
import com.purepoint.springbootpurepoint.user.dto.UserDto;
import com.purepoint.springbootpurepoint.user.dto.request.UserCreateRequestDto;

import java.util.Map;

public interface OAuthService {

    String getGoogleOAuth2Url();

    OAuthResponseDto handleGoogleCallback(String authCode);

    OAuthResponseDto purePointLogin(String email, String password);

    void purePointSignup(UserCreateRequestDto userInfo);
}
