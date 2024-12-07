package com.purepoint.springbootpurepoint.playlist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PlaylistLikesResDto {

    @Schema(description = "유튜브 playlist ID", example = "PL-AoIAa-OgNm9Kr85xdK90iBgu0LAWd9X", required = true)
    private String playlistId;

    @Schema(description = "유튜브 playlist 좋아요 수", example = "1")
    private Long playlistLikes;
}
