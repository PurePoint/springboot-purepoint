package com.purepoint.springbootpurepoint.community.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class CommUpdateCommentReqDto {

    @Schema(description = "댓글 ID", example = "")
    private UUID commentId;
    @Schema(description = "댓글을 작성한 유저 ID", example = "")
    private UUID userId;
    @Schema(description = "수정할 댓글 내용", example = "")
    private String commentContents;

}
