package com.purepoint.springbootpurepoint.oauth.controller;

import com.purepoint.springbootpurepoint.oauth.dto.response.OAuthResponseDto;
import com.purepoint.springbootpurepoint.oauth.service.OAuthService;
import com.purepoint.springbootpurepoint.user.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
@Tag(name = "인증 서비스 API", description = "사용자 인증을 위한 API를 제공합니다.")
@Slf4j
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService authService;

    @Operation(summary = "구글 로그인", description = "구글 로그인 페이지 URL을 요청합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 URL 반환")})
    @GetMapping("/login/google")
    public ResponseEntity<String> googleLogin() throws IOException {
        log.info("구글 로그인 URL 요청");
        return ResponseEntity.ok(authService.getGoogleOAuth2Url());
    }

    @Operation(summary = "구글 로그인 콜백", description = "구글 로그인 후 인증토큰을 가지고 사용자 정보를 콜백 처리합니다")
    @GetMapping("/login/google/callback")
    public ResponseEntity<OAuthResponseDto> googleCallback(@RequestParam("code") String authCode) throws IOException {
        log.info("Auth Code 요청 : " + authCode);
        return ResponseEntity.ok(authService.handleGoogleCallback(authCode));
    }

    // 퓨어포인트 로그인 ⭐
    @GetMapping("/login/purepoint")
    public ResponseEntity<UserDto> purePointLogin(@RequestParam("token") String token) {
        return ResponseEntity.ok(authService.purePointLogin(token));
    }
}