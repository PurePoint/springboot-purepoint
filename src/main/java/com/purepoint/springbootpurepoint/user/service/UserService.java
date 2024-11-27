package com.purepoint.springbootpurepoint.user.service;

import java.util.UUID;

import com.purepoint.springbootpurepoint.user.domain.User;
import com.purepoint.springbootpurepoint.user.dto.UserDto;
import com.purepoint.springbootpurepoint.user.dto.request.UserCreateRequestDto;
import com.purepoint.springbootpurepoint.user.dto.request.UserCreateSocialRequestDto;
import com.purepoint.springbootpurepoint.user.dto.response.UserLoginResponseDto;

/**
 * 사용자 관리에 대한 서비스 인터페이스를 제공합니다.
 */
public interface UserService{

    // 사용자의 소셜 로그인 정보를 받아 로그인을 진행합니다.
    UserLoginResponseDto loginUser(String providerName, String providerId, String email);

    // 사용자의 정보를 조회합니다.
    UserDto getUserById(UUID userId);
    UserDto getUserByEmail(String email);

    UserDto createUser(UserCreateRequestDto userDto);
    UserDto createSocialUser(UserCreateSocialRequestDto userDto);

    UserDto updateUserProfileImage(UUID userId, String profileImageUrl);
    UserDto updateUserNickname(UUID userId, String nickname);

    void deleteUser(UUID userId);

}
