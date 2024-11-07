package com.purepoint.springbootpurepoint.community.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class CommUpdatePostReqDto {

    @Schema(description = "게시글의 ID", example = "1", required = true)
    private UUID postId;
    @Schema(description = "사용자의 ID", example = "1", required = true)
    private UUID userId;
    @Schema(description = "새 게시글의 제목", example = "글제목", required = true)
    private String postTitle;
    @Schema(description = "새 게시글의 내용", example = "글내용", required = true)
    private String postContent;

}
