package com.purepoint.springbootpurepoint.community.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import java.util.UUID;

@Getter
@Builder
public class CommDeletePostReqDto {

    @Schema(description = "게시글의 ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID postId;
    @Schema(description = "사용자의 ID", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID userId;

}
