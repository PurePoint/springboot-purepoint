package com.purepoint.springbootpurepoint.community.controller;

import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.CommunityRequestDto;
import com.purepoint.springbootpurepoint.community.dto.CommunityResponseDto;
import com.purepoint.springbootpurepoint.community.service.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/community")
@AllArgsConstructor
@Tag(name = "커뮤니티 서비스 API", description = "커뮤니티 관리를 위한 API를 제공합니다.")
public class CommunityController {

    private final CommunityService communityService;

    @Operation(summary = "새 게시글을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 게시글 생성")})
    @PostMapping("/createPost")
    public ResponseEntity<Community> createPost(@RequestBody CommunityRequestDto.createPost communityRequestDto) {
        Community createPost = communityService.createPost(communityRequestDto);
        return ResponseEntity.ok(createPost);
    }

    @Operation(summary = "게시글을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 게시글 수정")})
    @PostMapping("/updatePost")
    public ResponseEntity<Community> updatePost(@RequestBody CommunityRequestDto.updatePost communityRequestDto) {
        Community updatePost = communityService.updatePost(communityRequestDto);
        return ResponseEntity.ok(updatePost);
    }

    @Operation(summary = "게시글을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 게시글 조회")})
    @GetMapping("/getPostById")
    public CommunityResponseDto getPostById(@RequestBody CommunityRequestDto.getBoardId communityRequestDto) {
        CommunityResponseDto getPostById = communityService.getPostById(communityRequestDto);
        return getPostById;
    }

    @Operation(summary = "최신순으로 게시글을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 게시글 조회")})
    @GetMapping("/getLatestPostById")
    public CommunityResponseDto getLatestPostById(@RequestBody CommunityRequestDto.getBoardId communityRequestDto) {
        CommunityResponseDto getLatestPostById = communityService.getPostById(communityRequestDto);
        return getLatestPostById;
    }

    @Operation(summary = "조회순으로 게시글을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 게시글 조회")})
    @GetMapping("/getLatestPostById")
    public CommunityResponseDto getPopularPostById(@RequestBody CommunityRequestDto.getBoardId communityRequestDto) {
        CommunityResponseDto getPopularPostById = communityService.getPostById(communityRequestDto);
        return getPopularPostById;
    }

    @Operation(summary = "게시글을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 게시글 삭제")})
    @PostMapping("/deletePost")
    public void deletePost(@RequestBody CommunityRequestDto.getBoardId communityRequestDto) {
        communityService.getPostById(communityRequestDto);
    }
}
