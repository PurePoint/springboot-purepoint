package com.purepoint.springbootpurepoint.user.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

// 유저 생성을 위한 RequestDTO
@Builder
@Getter
public class UserCreateRequestDto {
    @Schema( description = "사용자의 닉네임", example = "park", required = true )
    private String nickname;
    @Schema( description = "사용자의 이메일", example = "park@gmail.com" )
    private String email;
    @Schema(description = "사용자의 패스워드", example = "password123", required = true)
    private String password;
}
