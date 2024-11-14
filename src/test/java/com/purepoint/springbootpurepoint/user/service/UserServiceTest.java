package com.purepoint.springbootpurepoint.user.service;

import com.purepoint.springbootpurepoint.user.domain.User;
import com.purepoint.springbootpurepoint.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("사용자 로그인 테스트")
    @Transactional
    public void 사용자_로그인_테스트() {
        // 목업 데이터를 생성
        User user = User.builder()
                .email("test@test.com")
                .nickname("박정현")
                .profileImage(null)
                .providerId("1234")
                .providerName("google")
                .createdAt(LocalDateTime.now())
                .build();

        // 정상적인 로그인 시도
        UserDto loggedInUser = userService.loginUser("google","1234","test@test.com");


    }
    
    // 닉네임 중복 검사 테스트
    //

}
