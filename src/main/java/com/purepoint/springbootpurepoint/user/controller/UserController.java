package com.purepoint.springbootpurepoint.user.controller;

import com.purepoint.springbootpurepoint.user.dto.UserDto;
import com.purepoint.springbootpurepoint.user.dto.request.UserCreateRequestDto;
import com.purepoint.springbootpurepoint.user.dto.request.WatchHistoryRequestDto;
import com.purepoint.springbootpurepoint.user.dto.response.WatchHistoryResponseDto;
import com.purepoint.springbootpurepoint.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "유저 서비스 API", description = "유저 관리를 위한 API를 제공합니다.")
@Slf4j
public class UserController {

    private final UserService userService;

    // 테스트
    @GetMapping("")
    public String test() {
        log.info("Hello User");
        return "Hello User";
    }

    // 유저 조회
    @Operation(summary = "특정 ID를 가진 사용자를 조회합니다.", description = "주어진 ID에 해당하는 사용자를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 사용자 조회"),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음")})
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(
            @Parameter(description = "사용자 ID", example = "b4b068fc-de8e-37c9-85b1-4222ec6425b9", required = true)
            @PathVariable UUID id) {
        UserDto userDto = userService.getUserById(id);
        log.info("UserDto: {}", userDto.toString());
        return ResponseEntity.ok(userDto);
    }

    // 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // 유저 생성
    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@RequestBody UserCreateRequestDto userDto) {
        log.info("Create User: {}", userDto);
        UserDto createdUser = userService.createUser(userDto);

        return ResponseEntity.ok(createdUser);
    }

    // 유저 시청기록 조회
    @GetMapping("/watch/all")
    public ResponseEntity<List<WatchHistoryResponseDto>> getUserWatch(@RequestParam UUID userId) {
        return ResponseEntity.ok(userService.getWatchHistory(userId));
    }

    // 유저 시청기록 생성
    @PostMapping("/watch")
    public ResponseEntity<WatchHistoryResponseDto> createUserWatch(@RequestBody WatchHistoryRequestDto dto) {
        log.info("Create User Watch: {}", dto);
        return ResponseEntity.ok(userService.createWatchHistory(dto));
    }

}