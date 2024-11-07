package com.purepoint.springbootpurepoint.community.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommCommentResDto {

    @Schema(description = "사용자의 닉네임", example = "닉네임", required = true)
    private String nickname;
    @Schema(description = "댓글 내용", example = "댓글내용", required = true)
    private String commentContents;
    @Schema(description = "댓글 수정일", example = "24.11.06", required = true)
    private LocalDateTime commentUpdateAt;
    @Schema(description = "댓글 작성자의 프로필 사진", example = "사진", required = true)
    private String profilePicture;

}
