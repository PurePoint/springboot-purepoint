package com.purepoint.springbootpurepoint.user.controller;

import com.purepoint.springbootpurepoint.user.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Tag(name = "유저 서비스 API", description = "유저 관리를 위한 API를 제공합니다.")
public class UserController {

    @Operation(summary = "특정 ID를 가진 사용자를 조회합니다.", description = "주어진 ID에 해당하는 사용자를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 사용자 조회"),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")})
    @GetMapping("/{id}")
    public UserDto getUserById(
            @Parameter(description = "사용자 ID", example = "1", required = true)
            @PathVariable
            @Positive Long id) {
        return new UserDto();
    }


    @GetMapping("")
    public String test() {
        return "Hello User";
    }
}



