package com.purepoint.springbootpurepoint.user.service;

import com.purepoint.springbootpurepoint.user.dto.UserStatus;
import com.purepoint.springbootpurepoint.user.dto.response.UserLoginResponseDto;
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
    public UserLoginResponseDto loginUser(String providerName, String providerId, String email) {
        // TODO OAuth2를 통한 로그인 구현

        // 프로바이더와 providerName, providerId, email가 모두 일치하는 사용자가 있는지 확인
        try {
            Optional<User> userOptional = userRepository.findByProviderNameAndProviderIdAndEmail(providerName, providerId, email);

            // 유저 정보가 있는 경우
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                UserDto userDto = UserDto.builder()
                        .userId(user.getUserId())
                        .nickname(user.getNickname())
                        .email(user.getEmail())
                        .build();

                return UserLoginResponseDto.builder()
                        .userInfo(userDto)
                        .userStatus(UserStatus.ACTIVE)
                        .build();
            } else {
                // 유저 정보가 없는 경우 신규 유저라는 표시
                return UserLoginResponseDto.builder()
                        .userInfo(null)
                        .userStatus(UserStatus.NEW)
                        .build();
            }
        } catch (Exception e) {
            log.error("로그인 중 예외 발생: ", e);
            throw new RuntimeException("로그인 중 문제가 발생했습니다.");
        }
    }

    @Override
    public UserDto createUser(User user) {
        // TODO 시용자가 이미 존재하는지 검증

        // 사용자 정보가 들어오면, 해당 정보를 가지고 회원가입
        User savedUser = userRepository.save(user);

        //

        // 유저 정보 반환
        return UserDto.builder()
                .userId(savedUser.getUserId())
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
                .userId(user.getUserId())
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
                .userId(user.getUserId())
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
                .userId(user.getUserId())
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
