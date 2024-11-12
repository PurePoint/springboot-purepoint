package com.purepoint.springbootpurepoint.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfo {
    private String email;
    private String name;
    private String profilePicture;
}
