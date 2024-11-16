package com.purepoint.springbootpurepoint.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UserDto {
    @Schema(description="사용자의 ID", example="1", required=true)
    private UUID userId;
    @Schema( description = "사용자의 닉네임", example = "park", required = true )
    private String nickname;
    @Schema( description = "사용자의 이메일", example = "park@gmail.com" )
    private String email;
    @Schema( description = "사용자의 프로필 이미지", example = "https://images.com/profileimage.png" )
    private String profileImage;
}
