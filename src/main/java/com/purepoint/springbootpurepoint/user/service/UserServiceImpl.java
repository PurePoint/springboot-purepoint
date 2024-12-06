package com.purepoint.springbootpurepoint.user.service;

import com.purepoint.springbootpurepoint.user.domain.WatchHistory;
import com.purepoint.springbootpurepoint.user.dto.UserStatus;
import com.purepoint.springbootpurepoint.user.dto.request.UserCreateRequestDto;
import com.purepoint.springbootpurepoint.user.dto.request.UserCreateSocialRequestDto;
import com.purepoint.springbootpurepoint.user.dto.request.WatchHistoryRequestDto;
import com.purepoint.springbootpurepoint.user.dto.response.UserLoginResponseDto;
import com.purepoint.springbootpurepoint.user.dto.response.WatchHistoryResponseDto;
import com.purepoint.springbootpurepoint.user.exception.UserNotFoundException;
import com.purepoint.springbootpurepoint.user.domain.User;
import com.purepoint.springbootpurepoint.user.dto.UserDto;
import com.purepoint.springbootpurepoint.user.mapper.UserMapper;
import com.purepoint.springbootpurepoint.user.mapper.WatchHistoryMapper;
import com.purepoint.springbootpurepoint.user.repository.UserRepository;
import com.purepoint.springbootpurepoint.user.repository.WatchHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final WatchHistoryRepository watchHistoryRepository;

    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final WatchHistoryMapper watchHistoryMapper = WatchHistoryMapper.INSTANCE;

    @Override
    public UserLoginResponseDto loginUser(String providerName, String providerId, String email) {

        log.info("로그인 요청 들어옴: providerName={}, providerId={}, email={}", providerName, providerId, email);

        try {
            return userRepository.findByEmail(email)
                    .map(user -> {
                        log.info("기존 유저 로그인 확인 : email={}", user.getEmail());
                        UserDto userDto = userMapper.toDto(user);
                        return createUserLoginResponse(userDto, UserStatus.ACTIVE);
                    })
                    .orElseGet(() -> {
                        log.info("신규 유저임: email={}", email);
                        return createUserLoginResponse(null, UserStatus.NEW);
                    });
        } catch (Exception e) {
            log.error("로그인 중 예외 발생: ", e);
            throw new RuntimeException("로그인 중 문제가 발생했습니다.", e);
        }
    }

    private UserLoginResponseDto createUserLoginResponse(UserDto userDto, UserStatus status) {
        return UserLoginResponseDto.builder()
                .userInfo(userDto)
                .userStatus(status)
                .build();
    }

    @Override
    public UserDto createSocialUser(UserCreateSocialRequestDto userDto) {

        log.info("소셜 아이디 생성 시작!");

        User createUser = userMapper.toUserEntity(userDto);

        User userEntity = userRepository.save(createUser);

        return userMapper.toDto(userEntity);
    }

    @Override
    public UserDto createUser(UserCreateRequestDto userDto) {

        log.info("유저 정보 들어옴 {}", userDto.toString() );

        User createUser = userMapper.toUserEntity(userDto);

        User savedUser = userRepository.save(createUser);

        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto updateUserProfileImage(UUID userId, String profileImageUrl) {
        // 아이디를 통해 사용자 검색
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        user.setProfileImage(profileImageUrl);

        userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Override
    public UserDto updateUserNickname(UUID userId, String nickname) {
        // 아이디를 통해 사용자 검색
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        user.setNickname(nickname);
        userRepository.save(user);

        // 유저 정보 반환
        return userMapper.toDto(user);
    }

    @Override
    public UserDto getUserById(UUID userId) {
        // 아이디를 통해 사용자 검색
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        return userMapper.toDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));

        return userMapper.toDto(user);
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
    public WatchHistoryResponseDto createWatchHistory(WatchHistoryRequestDto dto) {
        WatchHistory entity = watchHistoryRepository.save(watchHistoryMapper.toEntity(dto));
        return watchHistoryMapper.toDto(entity);
    }

    @Override
    public List<WatchHistoryResponseDto> getWatchHistory(UUID userId) {
        List<WatchHistory> entity = watchHistoryRepository.findAllByUserId(userId);

        return entity.stream()
                .map(watchHistoryMapper::toDto)
                .collect(Collectors.toList());
    }
}
