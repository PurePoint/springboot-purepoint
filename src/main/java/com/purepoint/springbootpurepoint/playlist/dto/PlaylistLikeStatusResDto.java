package com.purepoint.springbootpurepoint.playlist.dto;

import com.purepoint.springbootpurepoint.video.dto.VideoLikeStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class PlaylistLikeStatusResDto {

    @Schema(description = "유저 ID", example = "343a7d2a-d340-4e36-a8cb-c3785131a2cf", required = true)
    private UUID userId;

    @Schema(description = "playlist ID", example = "PLC51MBz7PMyyJCw07eXgURHFn-on7Lnmg", required = true)
    private String playlistId;

    @Schema(description = "playlist 좋아요 상태", example = "LIKE", required = true)
    private PlaylistLikeStatus playlistLikeStatus;

}
