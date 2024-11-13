package com.purepoint.springbootpurepoint.youtube.controller;

import com.purepoint.springbootpurepoint.youtube.dto.YoutubeDto;
import com.purepoint.springbootpurepoint.youtube.service.YoutubeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/video")
@RequiredArgsConstructor
@Tag(name = "유튜브 영상 서비스 API", description = "유튜브 영상 관리를 위한 API를 제공합니다.")
public class YoutubeController {

    private final YoutubeService youtubeService;

    @Operation(summary = "유튜브 영상을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 유튜브 영상 조회")})
    @GetMapping("/")
    public List<YoutubeDto> getVideo() {
        return youtubeService.getYoutubeVideo();
    }
}
