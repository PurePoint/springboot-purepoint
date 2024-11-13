package com.purepoint.springbootpurepoint.auth.service;

import com.purepoint.springbootpurepoint.auth.dto.response.UserResponseDto;

public interface AuthService {

    // 구글 OAuth2 프로세스를 처리하고 사용자 정보를 반환합니다.
    UserResponseDto handleGoogleCallback(String authCode);
}
