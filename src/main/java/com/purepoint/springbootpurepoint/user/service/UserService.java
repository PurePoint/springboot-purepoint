package com.purepoint.springbootpurepoint.user.service;

import java.util.UUID;

import com.purepoint.springbootpurepoint.user.domain.User;
import com.purepoint.springbootpurepoint.user.dto.UserDto;
import com.purepoint.springbootpurepoint.user.dto.response.UserLoginResponseDto;

/**
 * 사용자 관리에 대한 서비스 인터페이스를 제공합니다.
 */
public interface UserService {

    // 사용자의 소셜 로그인 정보를 받아 로그인을 진행합니다.
    UserLoginResponseDto loginUser(String providerName, String providerId, String email);

    /**
     * 새로운 사용자를 생성합니다. (회원가입)
     *
     * @param user 생성할 사용자 정보
     * @return 생성된 사용자 정보
     */
    UserDto createUser(User userDto);

    /**
     * 사용자의 프로필 사진을 수정합니다.
     *
     * @param userId 프로필 사진을 수정할 사용자의 ID
     * @param profileImageUrl 새로운 프로필 사진 URL
     * @return 수정된 사용자 정보
     */
    UserDto updateUserProfileImage(UUID userId, String profileImageUrl);

    /**
     * 사용자의 닉네임을 수정합니다.
     *
     * @param userId 닉네임을 수정할 사용자의 ID
     * @param nickname 새로운 닉네임
     * @return 수정된 사용자 정보
     */
    UserDto updateUserNickname(UUID userId, String nickname);

    /**
     * 특정 ID를 가진 사용자를 조회합니다.
     *
     * @param userId 조회할 사용자 ID
     * @return 조회된 사용자 정보를 담은 UserDto 객체
     */
    UserDto getUserById(UUID userId);

    /**
     * 사용자를 삭제합니다. (회원탈퇴)
     *
     * @param userId 삭제할 사용자의 ID
     */
    void deleteUser(UUID userId);

    // 유저 조회 및 생성
    UserDto findOrCreateUser(String userId, String email, String name);
}
