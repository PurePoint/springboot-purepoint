package com.purepoint.springbootpurepoint.user.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserLoginRequestDto {
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 반드시 입력해야 합니다.")
    private String email;

    @NotBlank(message = "비밀번호는 반드시 입력해야 합니다.")
    private String password;
}
