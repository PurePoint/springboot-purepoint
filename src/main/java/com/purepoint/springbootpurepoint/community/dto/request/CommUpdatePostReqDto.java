package com.purepoint.springbootpurepoint.community.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.util.UUID;

@Getter
@Builder
public class CommUpdatePostReqDto {

    @Schema(description = "게시글의 ID", example = "54a9144e-9a94-4eb7-a044-0e7d9604b3b2")
    private UUID postId;
    @Schema(description = "사용자의 ID", example = "ce4bb5a1-5ffe-4137-bdfc-c4959c184a7e")
    private UUID userId;
    @Schema(description = "새 게시글의 제목", example = "글제목")
    private String postTitle;
    @Schema(description = "새 게시글의 내용", example = "글내용")
    private String postContent;

}
