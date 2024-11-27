package com.purepoint.springbootpurepoint.common.jwt;

import com.purepoint.springbootpurepoint.common.jwt.JwtTokenProvider;
import com.purepoint.springbootpurepoint.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class JwtTokenProviderTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    private final long validityInMilliseconds = 3600000; // 1시간

    @Value("${purepoint.jwt.secret}")
    private String secretKey;

    @Test
    public void testCreateToken() {
        UserDto user = UserDto.builder()
                .userId(UUID.randomUUID())
                .email("test@example.com")
                .nickname("testnickname")
                .profileImage("testImage.png")
                .build();

        String token = jwtTokenProvider.createToken(user, "ROLE_USER");

        assertNotNull(token, "토큰이 생성되어야 합니다.");
    }

    @Test
    public void testGetUserId() {
        UserDto user = UserDto.builder()
                .userId(UUID.randomUUID())
                .email("test@example.com")
                .nickname("testnickname")
                .profileImage("testImage.png")
                .build();

        String token = jwtTokenProvider.createToken(user, "ROLE_USER");
        String userId = jwtTokenProvider.getUserId(token);

        assertEquals(user.getUserId().toString(), userId, "생성한 토큰에서 유저 ID가 동일해야 합니다.");
    }

    @Test
    public void testValidateToken_validToken() {
        UserDto user = UserDto.builder()
                .userId(UUID.randomUUID())
                .email("test@example.com")
                .nickname("testnickname")
                .profileImage("testImage.png")
                .build();

        String token = jwtTokenProvider.createToken(user, "ROLE_USER");

        assertTrue(jwtTokenProvider.validateToken(token), "유효한 토큰이어야 합니다.");
    }

    @Test
    public void testValidateToken_invalidToken() {
        String invalidToken = "invalidToken";

        Exception exception = assertThrows(RuntimeException.class, () -> {
            jwtTokenProvider.validateToken(invalidToken);
        });

        String expectedMessage = "JWT 토큰이 유효하지 않습니다.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "예외 메시지가 일치해야 합니다.");
    }
}