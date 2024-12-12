package com.purepoint.springbootpurepoint.community.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CommPagingResDto {

    private List<CommReadPostResDto> content;
    private int totalPages;
    private long totalElements;
    private int currentPage;
    private int pageSize;
}
