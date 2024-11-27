package com.purepoint.springbootpurepoint.oauth.service;

import com.purepoint.springbootpurepoint.oauth.dto.response.OAuthResponseDto;
import com.purepoint.springbootpurepoint.user.dto.UserDto;

public interface OAuthService {

    String getGoogleOAuth2Url();

    OAuthResponseDto handleGoogleCallback(String authCode);

    UserDto purePointLogin(String accessToken);
}
