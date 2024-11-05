package com.purepoint.springbootpurepoint.user.dto;

public class UserLoginDto {
    private String username;
    private String password;
    private SocialInfo social;

    public static class SocialInfo {
        private String provider;
        private String providerUserId;
    }
}


