package com.purepoint.springbootpurepoint.community.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.util.UUID;

@Getter
@Builder
public class CommCreatePostReqDto {

    @Schema(description = "영상 ID", example = "7TtIEAx2FYg")
    private String videoId;
    @Schema(description = "유튜브 playlist 영상 Id", example = "1")
    private String playlistId;
    @Schema(description = "사용자의 ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID userId;
    @Schema(description = "새 게시글의 제목", example = "글제목")
    private String postTitle;
    @Schema(description = "새 게시글의 내용", example = "글내용")
    private String postContent;

}
