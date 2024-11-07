package com.purepoint.springbootpurepoint.community.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommDetailPostResDto {

    @Schema(description = "게시글 제목", example = "제목", required = true)
    private String postTitle;
    @Schema(description = "게시글 내용", example = "내용", required = true)
    private String postContent;
    @Schema(description = "게시글 조회수", example = "123", required = true)
    private Integer postView;
    @Schema(description = "게시글 생성일", example = "24.11.06", required = true)
    private LocalDateTime postAt;
    @Schema(description = "게시글 수정일", example = "24.11.06", required = true)
    private LocalDateTime postUpdateAt;

}
