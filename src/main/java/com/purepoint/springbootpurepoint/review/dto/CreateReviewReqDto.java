package com.purepoint.springbootpurepoint.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CreateReviewReqDto {

    @Schema(description = "영상 ID", example = "-HpnldGdgbY")
    private String videoId;
    @Schema(description = "수강평 내용", example = "수강평 내용입니다.")
    private String reviewContent;
    @Schema(description = "수강평 평점", example = "3")
    private Integer reviewRating;
    @Schema(description = "유저 ID", example = "343a7d2a-d340-4e36-a8cb-c3785131a2cf")
    private UUID userId;
}
