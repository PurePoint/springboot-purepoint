package com.purepoint.springbootpurepoint.community.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class CommDetailPostResDto {

    // 게시글 부분
    @Schema(description = "게시글 제목", example = "제목", required = true)
    private String postTitle;
    @Schema(description = "게시글 작성자의 닉네임", example = "닉네임", required = true)
    private String postNickname;
    @Schema(description = "게시글 내용", example = "내용", required = true)
    private String postContent;
    @Schema(description = "게시글 조회수", example = "123", required = true)
    private Integer postView;
    @Schema(description = "게시글 생성일", example = "2024-11-08T11:44:30.327959", required = true)
    private LocalDateTime postAt;
    @Schema(description = "게시글 수정일", example = "2024-11-08T11:44:30.327959", required = true)
    private LocalDateTime postUpdateAt;

    // 댓글 부분
//    @Schema(description = "댓글의 depth", example = "0", required = true)
//    private Integer depth;
//    @Schema(description = "댓글 작성자의 프로필 사진", example = "사진", required = true)
//    private String profileImage;
//    @Schema(description = "댓글 작성자의 닉네임", example = "닉네임", required = true)
//    private String commentNickname;
//    @Schema(description = "댓글 내용", example = "댓글내용", required = true)
//    private String commentContents;
//    @Schema(description = "댓글 생성일", example = "2024-11-08T11:44:30.327959", required = true)
//    private LocalDateTime commentAt;
//    @Schema(description = "댓글 수정일", example = "2024-11-08T11:44:30.327959", required = true)
//    private LocalDateTime commentUpdateAt;
    @Schema(description = "댓글", required = true)
    private List<CommDetailCommentResDto> comments;

}
