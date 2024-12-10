package com.purepoint.springbootpurepoint.playlist.controller;

import com.purepoint.springbootpurepoint.playlist.domain.PlaylistLike;
import com.purepoint.springbootpurepoint.playlist.dto.*;
import com.purepoint.springbootpurepoint.playlist.service.PlaylistService;
import com.purepoint.springbootpurepoint.video.dto.VideoDto;
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
@RequestMapping("/youtube/playlist")
@RequiredArgsConstructor
@Tag(name = "유튜브 플레이리스트 서비스 API", description = "유튜브 플레이리스트 관리를 위한 API를 제공합니다.")
@Slf4j
public class PlaylistController {

    private final PlaylistService playlistService;

    @Operation(summary = "유튜브 playlist 영상들을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 유튜브 playlist 영상 조회")})
    @GetMapping("/videos")
    public ResponseEntity<List<VideoDto>> getPlaylistVideos(@RequestParam String playlistId) {
        return ResponseEntity.ok(playlistService.getPlaylistVideos(playlistId));
    }

    @Operation(summary = "유튜브 playlist 존재하는지 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 유튜브 playlist 존재여부 조회")})
    @GetMapping
    public ResponseEntity<PlaylistIdResDto> getPlaylistId(@RequestParam String videoId) {
        return ResponseEntity.ok(playlistService.getPlaylistId(videoId));
    }

    @Operation(summary = "추천 playlist를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 추천 playlist 조회")})
    @GetMapping("/recommendPlaylist")
    public ResponseEntity<List<PlaylistDto>> getRecommendPlaylist(String query, @RequestParam String playlistId) {
        return ResponseEntity.ok(playlistService.getRecommendPlaylist(query, playlistId));
    }

    @Operation(summary = "유튜브 playlist 좋아요 수를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 유튜브 playlist 좋아요 수 조회")})
    @GetMapping("/countLike")
    public ResponseEntity<PlaylistLikesResDto> getPlaylistLikes(@RequestParam String playlistId) {
        return ResponseEntity.ok(playlistService.getPlaylistLikes(playlistId));
    }

    @Operation(summary = "유튜브 playlist 좋아요 상태를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 유튜브 playlist 좋아요 상태 조회")})
    @PostMapping("/playlistLikeStatus")
    public ResponseEntity<PlaylistLikeStatusResDto> getPlaylistLikeStatus(@RequestBody PlaylistLikeStatusReqDto playlistLikeStatusReqDto) {
        return ResponseEntity.ok(playlistService.getPlaylistLikeStatus(playlistLikeStatusReqDto));
    }

    @Operation(summary = "유튜브 playlist 좋아요 수를 업데이트합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 유튜브 playlist 좋아요 수 업데이트")})
    @PostMapping("/playlistLikes")
    public ResponseEntity<PlaylistLike> updatePlaylistLikes(@RequestBody UpdatePlaylistLikeStatusReqDto updatePlaylistLikeStatusReqDto) {
        return ResponseEntity.ok(playlistService.updatePlaylistLike(updatePlaylistLikeStatusReqDto));
    }
}
