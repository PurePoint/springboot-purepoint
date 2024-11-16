package com.purepoint.springbootpurepoint.user.dto.response;

import com.purepoint.springbootpurepoint.user.dto.UserDto;
import com.purepoint.springbootpurepoint.user.dto.UserStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLoginResponseDto {
    private UserDto userInfo;
    private UserStatus userStatus;
}