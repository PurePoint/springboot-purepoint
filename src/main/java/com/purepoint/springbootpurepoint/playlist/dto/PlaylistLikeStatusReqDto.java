package com.purepoint.springbootpurepoint.playlist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class PlaylistLikeStatusReqDto {

    @Schema(description = "유저 ID", example = "343a7d2a-d340-4e36-a8cb-c3785131a2cf", required = true)
    private UUID userId;

    @Schema(description = "플레이리스트 ID", example = "PLC51MBz7PMyyJCw07eXgURHFn-on7Lnmg", required = true)
    private String playlistId;
}
