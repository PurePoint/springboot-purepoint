package com.purepoint.springbootpurepoint.oauth.dto.response;

import com.purepoint.springbootpurepoint.user.dto.UserStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OAuthResponseDto {
    private String accessToken;
    private String refreshToken;
    private UserStatus loginStatus ;
}
