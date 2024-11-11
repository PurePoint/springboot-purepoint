package com.purepoint.springbootpurepoint.auth.service;

import com.purepoint.springbootpurepoint.user.dto.UserDto;

public interface AuthService {

    UserDto handleGoogleCallback(String code);
}
