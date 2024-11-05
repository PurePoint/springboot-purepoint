package com.purepoint.springbootpurepoint.user.service;

import com.purepoint.springbootpurepoint.user.exception.UserNotFoundException;
import com.purepoint.springbootpurepoint.user.domain.User;
import com.purepoint.springbootpurepoint.user.dto.UserDto;
import com.purepoint.springbootpurepoint.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto loginUser(String nickname, String password) {
        // 소셜로그인 인지 일반 로그인인지 검증해야함.
        return null;
    }

    @Override
    public UserDto createUser(User user) {
        // 사용자 정보가 들어오면, 해당 정보를 가지고 회원가입
        User savedUser = userRepository.save(user);

        return UserDto.builder()
                .id(savedUser.getUserId())
                .nickname(savedUser.getNickname())
                .email(savedUser.getEmail())
                .build();
    }

    @Override
    public UserDto updateUserProfilePicture(UUID userId, String profilePictureUrl) {
        // 사용자 프로필 사진을 수정하는 로직
        return null;
    }

    @Override
    public UserDto updateUserNickname(UUID userId, String nickname) {
        // 사용자 닉네임을 수정하는 로직
        return null;
    }

    @Override
    public UserDto updateUserPassword(UUID userId, String newPassword) {
        // 사용자의 비밀번호를 수정하는 로직
        return null;
    }

    @Override
    public UserDto getUserById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        return UserDto.builder()
                .id(user.getUserId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .build();
    }

    @Override
    public void deleteUser(UUID userId) {
        // 사용자를 조회하여 상태를 비활성화로 변경합니다. (삭제 날짜 업데이트)
        userRepository.findById(userId).ifPresent(user -> {
            user.setDeletedAt(LocalDateTime.now());
            userRepository.save(user);
        });
    }
}
