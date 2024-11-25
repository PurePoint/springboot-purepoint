package com.purepoint.springbootpurepoint.community.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class CommReadPostResDto {

    @Schema(description = "게시글 제목", example = "제목", required = true)
    private String postTitle;
    @Schema(description = "게시글 조회수", example = "123", required = true)
    private Integer postView;
    @Schema(description = "게시글 생성일", example = "2024-11-08T11:44:30.327959", required = true)
    private LocalDateTime postAt;

}
