package com.purepoint.springbootpurepoint.auth.dto.response;

import com.purepoint.springbootpurepoint.auth.dto.UserInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponseDto {

    private String Token;
    private String RefreshToken;
    private UserInfo UserInfo;
    private boolean loginStatus ;
}
