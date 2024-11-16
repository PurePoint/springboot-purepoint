package com.purepoint.springbootpurepoint.user.service;

import com.purepoint.springbootpurepoint.user.domain.User;
import com.purepoint.springbootpurepoint.user.dto.UserDto;
import com.purepoint.springbootpurepoint.user.dto.UserStatus;
import com.purepoint.springbootpurepoint.user.dto.response.UserLoginResponseDto;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("사용자 로그인 기존 유저 테스트")
    @Transactional
    public void 사용자_로그인_테스트() {
        // 새 사용자를 생성합니다. (기존 유저가 있다고 가정)
        User user = mockUser(); // 임시 데이터를 생성
        UserDto createdUser = userService.createUser(user);
        log.info("유저 생성됨 {}", createdUser.getNickname());

        // 생성된 유저가 잘 저장되었는지 확인합니다.
        assertThat(createdUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(createdUser.getNickname()).isEqualTo(user.getNickname());

        // 정상적인 로그인 시도
        UserLoginResponseDto loggedInUser = userService.loginUser("google", "1234", "test@test.com");
        log.info("로그인 성공 : {}", loggedInUser.getUserInfo());
    }

    @Test
    @DisplayName("사용자 로그인 신규 유저 테스트")
    @Transactional
    public void 사용자_로그인_테스트_2(){
        // 신규 유저 로그인 시도
        try {
            UserLoginResponseDto loggedInUser = userService.loginUser("google", "12345", "newUser@test.com");
            assertThat(loggedInUser.getUserInfo()).isEqualTo(null);
            assertThat(loggedInUser.getUserStatus()).isEqualTo(UserStatus.NEW);
        } catch (Exception e) {
            log.info("신규 유저 로그인 테스트 중 에러가 발생하였습니다.");
        }

    }

    // 테스트할 목업 데이터
    private User mockUser() {
        return User.builder()
                .email("test@test.com")
                .nickname("박정현")
                .profileImage(null)
                .providerId("1234")
                .providerName("google")
                .build();
    }
}
