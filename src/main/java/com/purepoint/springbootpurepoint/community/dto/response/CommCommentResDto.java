package com.purepoint.springbootpurepoint.community.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class CommCommentResDto {

    @Schema(description = "댓글의 ID", example = "54a9144e-9a94-4eb7-a044-0e7d9604b3b2")
    private UUID commentId;
    @Schema(description = "댓글의 parent 댓글의 ID", example = "54a9144e-9a94-4eb7-a044-0e7d9604b3b2")
    private UUID parentCommentId;
    @Schema(description = "댓글을 작성한 유저 ID", example = "54a9144e-9a94-4eb7-a044-0e7d9604b3b2")
    private UUID userId;
    @Schema(description = "댓글 작성자의 프로필 사진", example = "사진")
    private String profileImage;
    @Schema(description = "댓글 작성자의 닉네임", example = "닉네임")
    private String commentNickname;
    @Schema(description = "댓글 내용", example = "댓글내용")
    private String commentContents;
    @Schema(description = "댓글 생성일", example = "2024-11-08T11:44:30.327959")
    private LocalDateTime commentAt;
    @Schema(description = "댓글 수정일", example = "2024-11-08T11:44:30.327959")
    private LocalDateTime commentUpdateAt;
    @Schema(description = "댓글 삭제일", example = "2024-11-08T11:44:30.327959")
    private LocalDateTime commentDeleteAt;

}
