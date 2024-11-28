package com.purepoint.springbootpurepoint.community.dto.response;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class CommDetailCommentResDto {
    private Integer depth;
    private String profileImage;
    private String commentNickname;
    private String commentContents;
    private LocalDateTime commentAt;
    private LocalDateTime commentUpdateAt;
}
