package com.purepoint.springbootpurepoint.playlist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PlaylistDto {

    @Schema(description = "유튜브 playlist ID", example = "PL-AoIAa-OgNm9Kr85xdK90iBgu0LAWd9X", required = true)
    private String playlistId;

    @Schema(description = "유튜브 playlist 제목", example = "Java 강의", required = true)
    private String playlistTitle;

    @Schema(description = "유튜브 playlist 설명", example = "Java 강의입니다.")
    private String playlistDescription;

    @Schema(description = "유튜브 playlist 게시시간", example = "2024-02-29 04:12:22.000000")
    private LocalDateTime playlistPublishedAt;

    @Schema(description = "유튜브 playlist 썸네일", example = "https://i.ytimg.com/vi/7TtIEAx2FYg/default.jpg")
    private String playlistThumbnail;

    @Schema(description = "유튜브 playlist 좋아요 수", example = "1")
    private Long playlistLikes;

    @Schema(description = "유튜브 playlist 채널 Id", example = "UCenbLmVazutdwQrVXUFVlNg")
    private String channelId;

    @Schema(description = "유튜브 playlist 채널 타이틀", example = "코딩문")
    private String channelTitle;
}
