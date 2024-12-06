package com.purepoint.springbootpurepoint.user.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class WatchHistoryRequestDto {

    private UUID userId;
    private String videoId;
}
