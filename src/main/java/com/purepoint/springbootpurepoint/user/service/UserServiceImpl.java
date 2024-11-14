package com.purepoint.springbootpurepoint.user.service;

import com.purepoint.springbootpurepoint.user.exception.UserNotFoundException;
import com.purepoint.springbootpurepoint.user.domain.User;
import com.purepoint.springbootpurepoint.user.dto.UserDto;
import com.purepoint.springbootpurepoint.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

// TODO 아이디를 통해 사용자 검색 중복 로직 재사용 처리
// TODO MapStruct 코드로 변경

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto loginUser(String providerName, String providerId, String email) {
        // TODO OAuth2를 통한 로그인 구현

        // 프로바이더와 providerName, providerId, email가 모두 일치하는 사용자가 있는지 확인
        Optional<User> user = userRepository.findByProviderNameAndProviderIdAndEmail(providerName, providerId, email);

        // 회원이 없으면
        if(user.isEmpty()){
            log.info("신규 회원입니다. 회원가입을 진행합니다.");
        }

        return null;
    }

    @Override
    public UserDto createUser(User user) {
        // TODO 시용자가 이미 존재하는지 검증

        // 사용자 정보가 들어오면, 해당 정보를 가지고 회원가입
        User savedUser = userRepository.save(user);

        //

        // 유저 정보 반환
        return UserDto.builder()
                .id(savedUser.getUserId())
                .nickname(savedUser.getNickname())
                .email(savedUser.getEmail())
                .build();
    }

    @Override
    public UserDto updateUserProfileImage(UUID userId, String profileImageUrl) {
        // 아이디를 통해 사용자 검색
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        // 프로필 URL 변경
        user.setProfileImage(profileImageUrl);
        userRepository.save(user);

        // 유저 정보 반환
        return UserDto.builder()
                .id(user.getUserId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserDto updateUserNickname(UUID userId, String nickname) {
        // 아이디를 통해 사용자 검색
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        user.setNickname(nickname);
        userRepository.save(user);

        // 유저 정보 반환
        return UserDto.builder()
                .id(user.getUserId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .build();
    }

    @Override
    public UserDto getUserById(UUID userId) {
        // 아이디를 통해 사용자 검색
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        // 유저 정보 반환
        return UserDto.builder()
                .id(user.getUserId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .build();
    }

    @Override
    public void deleteUser(UUID userId) {
        // 사용자를 조회하여 상태를 비활성화로 변경 (삭제 날짜 업데이트)
        userRepository.findById(userId).ifPresent(user -> {
            user.setDeletedAt(LocalDateTime.now());
            userRepository.save(user);
        });
    }

    @Override
    public UserDto findOrCreateUser(String userId, String email, String name) {
        return null;
    }
}
