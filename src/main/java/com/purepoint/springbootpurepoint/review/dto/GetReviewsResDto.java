package com.purepoint.springbootpurepoint.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class GetReviewsResDto {
    @Schema(description = "수강평 내용", example = "수강평 내용입니다.")
    private String reviewContent;
    @Schema(description = "수강평 평점", example = "3")
    private Integer reviewRating;
    @Schema(description = "수강평 생성일", example = "2024-11-08T11:44:30.327959")
    private LocalDateTime reviewAt;
    @Schema(description = "수강평 수정일", example = "2024-11-08T11:44:30.327959")
    private LocalDateTime reviewUpdateAt;
    @Schema(description = "유저 닉네임", example = "park")
    private String nickname;
}
