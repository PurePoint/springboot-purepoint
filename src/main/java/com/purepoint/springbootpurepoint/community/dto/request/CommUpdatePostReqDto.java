package com.purepoint.springbootpurepoint.community.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.util.UUID;

@Getter
@Builder
public class CommUpdatePostReqDto {

    @Schema(description = "게시글의 ID", example = "550e8400-e29b-41d4-a716-446655440000", required = true)
    private UUID postId;
    @Schema(description = "사용자의 ID", example = "550e8400-e29b-41d4-a716-446655440000", required = true)
    private UUID userId;
    @Schema(description = "새 게시글의 제목", example = "글제목", required = true)
    private String postTitle;
    @Schema(description = "새 게시글의 내용", example = "글내용", required = true)
    private String postContent;

}
