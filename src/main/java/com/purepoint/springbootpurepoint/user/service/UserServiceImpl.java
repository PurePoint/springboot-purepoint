package com.purepoint.springbootpurepoint.user.service;

import com.purepoint.springbootpurepoint.user.domain.User;
import com.purepoint.springbootpurepoint.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> loginUser(String username, String password) {
        // 소셜로그인 인지 일반 로그인인지 검증해야함.
        return Optional.empty();
    }

    @Override
    public User createUser(User user) {
        // 사용자 정보가 들어오면, 해당 정보를 가지고 회원가입
        return userRepository.save(user);
    }

    @Override
    public User updateUserProfilePicture(Long userId, String profilePictureUrl) {
        // 사용자 프로필 사진을 수정하는 로직
        return null;
    }

    @Override
    public User updateUserNickname(Long userId, String nickname) {
        // 사용자 닉네임을 수정하는 로직
        return null;
    }

    @Override
    public User updateUserPassword(Long userId, String newPassword) {
        // 사용자의 비밀번호를 수정하는 로직
        return null;
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        // 사용자의 정보를 조회하는 로직
        return Optional.empty();
    }

    @Override
    public void deleteUser(Long userId) {
        // 사용자를 조회하여 상태를 비활성화로 변경합니다. (삭제 날짜 업데이트)
        userRepository.findById(userId).ifPresent(user -> {
            user.setDeletedAt(LocalDateTime.now());
            userRepository.save(user);
        });
    }
}
