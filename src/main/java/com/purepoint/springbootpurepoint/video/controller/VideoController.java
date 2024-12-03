package com.purepoint.springbootpurepoint.video.controller;

import com.purepoint.springbootpurepoint.video.domain.VideoLike;
import com.purepoint.springbootpurepoint.video.dto.UpdateVideoLikeStatusReqDto;
import com.purepoint.springbootpurepoint.video.dto.VideoDto;
import com.purepoint.springbootpurepoint.video.dto.VideoLikeStatusReqDto;
import com.purepoint.springbootpurepoint.video.dto.VideoLikeStatusResDto;
import com.purepoint.springbootpurepoint.video.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/youtube/video")
@RequiredArgsConstructor
@Tag(name = "유튜브 영상 서비스 API", description = "유튜브 영상 관리를 위한 API를 제공합니다.")
@Slf4j
public class VideoController {

    private final VideoService videoService;

    @Operation(summary = "유튜브 영상을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 유튜브 영상 조회")})
    @GetMapping
    public ResponseEntity<List<VideoDto>> getVideo(String category) {
        return ResponseEntity.ok(videoService.getYoutubeVideo(category));
    }

    @Operation(summary = "유튜브 영상 좋아요 상태를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 유튜브 영상 좋아요 상태 조회")})
    @PostMapping("/videoLikeStatus")
    public ResponseEntity<VideoLikeStatusResDto> getVideoLikeStatus(@RequestBody VideoLikeStatusReqDto videoLikeStatusReqDto) {
        log.info("videoLikeStatus");
        return ResponseEntity.ok(videoService.getVideoLikeStatus(videoLikeStatusReqDto));
    }

    @Operation(summary = "유튜브 영상 좋아요 수를 업데이트합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 유튜브 영상 좋아요 수 업데이트")})
    @PostMapping("/videoLikes")
    public ResponseEntity<VideoLike> updateVideoLikes(@RequestBody UpdateVideoLikeStatusReqDto updateVideoLikeStatusReqDto) {
        return ResponseEntity.ok(videoService.updateVideoLike(updateVideoLikeStatusReqDto));
    }
}