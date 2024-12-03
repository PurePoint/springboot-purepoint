package com.purepoint.springbootpurepoint.video.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class VideoLikeStatusReqDto {

    @Schema(description = "유저 ID", example = "343a7d2a-d340-4e36-a8cb-c3785131a2cf", required = true)
    private UUID userId;

    @Schema(description = "영상 ID", example = "-HpnldGdgbY", required = true)
    private String videoId;

}
