package com.purepoint.springbootpurepoint.community.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CommPagingResDto {

    @Schema(description = "게시글")
    private List<CommReadPostResDto> content;
    @Schema(description = "글 전체 페이지 수", example = "10")
    private int totalPages;
    @Schema(description = "글 전체 요소 수", example = "111")
    private long totalElements;
    @Schema(description = "현재 페이지", example = "1")
    private int currentPage;
    @Schema(description = "각 페이지별 요소 수", example = "10")
    private int pageSize;
}
