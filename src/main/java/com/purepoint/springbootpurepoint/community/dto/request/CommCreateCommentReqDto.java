package com.purepoint.springbootpurepoint.community.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CommCreateCommentReqDto {

    @Schema(description = "게시글의 ID", example = "1550e8400-e29b-41d4-a716-446655440000")
    private UUID postId;

    @Schema(description = "사용자의 ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID userId;

    @Schema(description = "댓글의 내용", example = "내용")
    private String commentContents;

    @Schema(description = "댓글의 parent 댓글의 ID", example = "1550e8400-e29b-41d4-a716-446655440000")
    private UUID parentCommentId;

}
