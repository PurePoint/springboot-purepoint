package com.purepoint.springbootpurepoint.auth.controller;

import com.purepoint.springbootpurepoint.auth.dto.response.UserResponseDto;
import com.purepoint.springbootpurepoint.auth.service.AuthService;
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
public class AuthController {

    private final AuthService authService;
    
    // TODO 로직을 조금 바꿔야 겠어. 프로바이더를 받아서 처리하는 걸로

    @Operation(summary = "구글 로그인", description = "구글 로그인 페이지 URL을 요청합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 URL 반환")})
    @GetMapping("/login/google")
    public String googleLogin() throws IOException {
        // TODO : 서비스로 코드 옮기기
        log.info("구글 로그인 URL 요청");
        String redirectUrl = "https://accounts.google.com/o/oauth2/v2/auth"
                + "?scope=profile%20email"
                + "&access_type=offline"
                + "&include_granted_scopes=true"
                + "&response_type=code"
                + "&state=state_parameter_passthrough_value"
                + "&redirect_uri=" + "http://localhost:3000/redirect"
                + "&client_id=" + "69356984572-fhmvvd12bdhvaq33peiu1t0jl2vvmob1.apps.googleusercontent.com";

        return redirectUrl;
    }

    // 임시
    @Operation(summary = "구글 로그인 콜백", description = "구글 로그인 후 인증토큰을 가지고 사용자 정보를 콜백 처리합니다")
    @GetMapping("/login/google/callback")
    public ResponseEntity<UserResponseDto> googleCallback(@RequestParam("code") String authCode) throws IOException {

        // 요청 로그 확인
        log.info("Auth Ccod 요청 : " + authCode);

        return ResponseEntity.ok(authService.handleGoogleCallback(authCode));
    }
}