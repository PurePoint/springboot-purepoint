package com.purepoint.springbootpurepoint.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class UserCreateSocialRequestDto {

    private String email;
    private String nickname;
    private String providerName;
    private String providerId;
    private String profileImage;
}
