package com.purepoint.springbootpurepoint.community.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class CommReadPostResDto {

    @Schema(description = "게시글 제목", example = "제목")
    private String postTitle;
    @Schema(description = "게시글 내용", example = "내용")
    private String postContent;
    @Schema( description = "사용자의 닉네임", example = "park")
    private String nickname;
    @Schema(description = "게시글 생성일", example = "2024-11-08T11:44:30.327959")
    private LocalDateTime postAt;
    @Schema(description = "게시글 수정일", example = "2024-11-08T11:44:30.327959")
    private LocalDateTime postUpdateAt;

}
