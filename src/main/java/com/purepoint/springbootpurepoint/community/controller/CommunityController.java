package com.purepoint.springbootpurepoint.community.controller;

import com.purepoint.springbootpurepoint.community.domain.Comment;
import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.request.CommCreateCommentReqDto;
import com.purepoint.springbootpurepoint.community.dto.request.CommCreatePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.request.CommDeletePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.request.CommUpdatePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommPagingResDto;
import com.purepoint.springbootpurepoint.community.service.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/community")
@RequiredArgsConstructor
@Tag(name = "커뮤니티 서비스 API", description = "커뮤니티 관리를 위한 API를 제공합니다.")
public class CommunityController {

    private final CommunityService communityService;

    @Operation(summary = "새 게시글을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 게시글 생성")})
    @PostMapping("/post")
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
    @GetMapping("/posts")
    public ResponseEntity<CommPagingResDto> getPost(@RequestParam String videoId, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(communityService.getPost(videoId, page, size));
    }

    @Operation(summary = "게시글을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 게시글 삭제")})
    @DeleteMapping("/posts/{postId}")
    public void deletePost(@RequestBody CommDeletePostReqDto commDeletePostReqDto) {
        communityService.deletePost(commDeletePostReqDto);
    }

    @Operation(summary = "새 댓글을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 댓글 생성")})
    @PostMapping("/{postId}/createComments")
    public ResponseEntity<Comment> createCommentsPost(CommCreateCommentReqDto commCreateCommentReqDto) {
        Comment comment = communityService.createComment(commCreateCommentReqDto);
        return ResponseEntity.ok(comment);
    }

//    @Operation(summary = "댓글을 수정합니다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "성공적으로 댓글 수정")})
//    @PutMapping("/updateComments/{commentId}")
//    public ResponseEntity<Comment> updateCommentsPost(@PathVariable UUID commentId) {
//        // ToDo 댓글 수정 서비스 호출
//        return null;
//    }

//    @Operation(summary = "댓글을 삭제합니다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "성공적으로 댓글 삭제")})
//    @DeleteMapping("/deleteComments/{commentId}")
//    public ResponseEntity<Comment> deleteCommentsPost(@PathVariable UUID commentId) {
//        // ToDo 댓글 삭제 서비스 호출
//        return null;
//    }
}
