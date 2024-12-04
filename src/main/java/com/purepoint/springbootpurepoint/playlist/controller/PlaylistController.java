package com.purepoint.springbootpurepoint.playlist.controller;

import com.purepoint.springbootpurepoint.playlist.service.PlaylistService;
import com.purepoint.springbootpurepoint.video.dto.VideoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
