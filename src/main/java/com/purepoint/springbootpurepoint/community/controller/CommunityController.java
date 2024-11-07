package com.purepoint.springbootpurepoint.community.controller;

import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.*;
import com.purepoint.springbootpurepoint.community.service.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
@Tag(name = "커뮤니티 서비스 API", description = "커뮤니티 관리를 위한 API를 제공합니다.")
public class CommunityController {

    private final CommunityService communityService;

    @Operation(summary = "새 게시글을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 게시글 생성")})
    @PostMapping("/posts")
    public ResponseEntity<Community> createPost(@RequestBody CommCreatePostReqDto communityRequestDto) {
        Community createPost = communityService.createPost(communityRequestDto);
        return ResponseEntity.ok(createPost);
    }

    @Operation(summary = "게시글을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 게시글 수정")})
    @PutMapping("/posts/{postId}")
    public ResponseEntity<Community> updatePost(@RequestBody CommUpdatePostReqDto communityRequestDto) {
        Community updatePost = communityService.updatePost(communityRequestDto);
        return ResponseEntity.ok(updatePost);
    }

    @Operation(summary = "게시글을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 게시글 조회")})
    @GetMapping("/")
    public List<CommReadPostResDto> getPost() {
        List<CommReadPostResDto> getPost = communityService.getPost();
        return getPost;
    }

    @Operation(summary = "최신순으로 게시글을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 게시글 조회")})
    @GetMapping("/latest")
    public List<CommReadPostResDto> getLatestPost() {
        List<CommReadPostResDto> getLatestPost = communityService.getLatestPost();
        return getLatestPost;
    }

    @Operation(summary = "조회순으로 게시글을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 게시글 조회")})
    @GetMapping("/favorites")
    public List<CommReadPostResDto> getPopularPost() {
        List<CommReadPostResDto> getPopularPost = communityService.getPopularPost();
        return getPopularPost;
    }

    @Operation(summary = "게시글을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 게시글 삭제")})
    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable UUID postId) {
        communityService.deletePost(postId);
    }

    @Operation(summary = "게시글 디테일을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 게시글 디테일 조회")})
    @GetMapping("/{postId}")
    public CommDetailPostResDto getDetailPost(@PathVariable UUID postId) {
        return communityService.getDetailPost(postId);
    }

    @Operation(summary = "게시글 댓글을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 게시글 댓글 조회")})
    @GetMapping("/{postId}/comments")
    public List<CommCommentResDto> getCommentsPost(@PathVariable UUID postId) {
        return communityService.getCommentsPost(postId);
    }

}
