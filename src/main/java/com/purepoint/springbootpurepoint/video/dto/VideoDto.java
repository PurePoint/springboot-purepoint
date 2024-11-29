package com.purepoint.springbootpurepoint.video.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VideoDto {

    @Schema(description = "유튜브 영상 ID", example = "7TtIEAx2FYg", required = true)
    private String videoId;

    @Schema(description = "유튜브 영상 제목", example = "Java 강의", required = true)
    private String videoTitle;

    @Schema(description = "유튜브 영상 설명", example = "Java 강의입니다.")
    private String videoDescription;

    @Schema(description = "유튜브 영상 게시시간", example = "2023-05-07T06:23:47Z")
    private String videoPublishedAt;

    @Schema(description = "유튜브 영상 썸네일", example = "https://i.ytimg.com/vi/7TtIEAx2FYg/default.jpg")
    private String videoThumbnail;

    @Schema(description = "유튜브 영상 좋아요 수", example = "1")
    private Long videoLikes;
}
