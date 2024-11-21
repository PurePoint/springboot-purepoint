package com.purepoint.springbootpurepoint.auth.dto.response;

import com.purepoint.springbootpurepoint.auth.dto.UserInfo;
import com.purepoint.springbootpurepoint.user.dto.UserStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDto {
    private String Token;
    private String RefreshToken;
    private UserStatus loginStatus ;
}
