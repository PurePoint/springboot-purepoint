package com.purepoint.springbootpurepoint.video.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class VideoPagingDto {

    private List<VideoDto> content;
    private int totalPages;
    private long totalElements;
    private int currentPage;
    private int pageSize;
}
