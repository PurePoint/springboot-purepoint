package com.purepoint.springbootpurepoint.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetReviewsPagingResDto {

    private List<GetReviewsResDto> content;
    private int totalPages;
    private long totalElements;
    private int currentPage;
    private int pageSize;
}
