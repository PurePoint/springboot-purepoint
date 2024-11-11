package com.purepoint.springbootpurepoint.user.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

// 유저 생성을 위한 RequestDTO
@Builder
public class UserCreateDto {
    @Schema(description="사용자의 ID", example="1", required=true)
    private UUID userId;
    @Schema( description = "사용자의 닉네임", example = "park", required = true )
    private String nickname;
    @Schema( description = "사용자의 이메일", example = "park@gmail.com" )
    private String email;
}
