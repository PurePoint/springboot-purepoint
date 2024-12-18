package com.purepoint.springbootpurepoint.community.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class CommReadPostResDto {

    @Schema(description = "게시글 Id", example = "54a9144e-9a94-4eb7-a044-0e7d9604b3b2")
    private UUID postId;
    @Schema(description = "게시글 제목", example = "제목")
    private String postTitle;
    @Schema(description = "게시글 내용", example = "내용")
    private String postContent;
    @Schema( description = "사용자의 Id", example = "ce4bb5a1-5ffe-4137-bdfc-c4959c184a7e")
    private UUID userId;
    @Schema( description = "사용자의 닉네임", example = "park")
    private String nickname;
    @Schema(description = "게시글 생성일", example = "2024-11-08T11:44:30.327959")
    private LocalDateTime postAt;
    @Schema(description = "게시글 수정일", example = "2024-11-08T11:44:30.327959")
    private LocalDateTime postUpdateAt;
    @Schema(description = "게시글 삭제일", example = "2024-11-08T11:44:30.327959")
    private LocalDateTime postDeleteAt;

}
