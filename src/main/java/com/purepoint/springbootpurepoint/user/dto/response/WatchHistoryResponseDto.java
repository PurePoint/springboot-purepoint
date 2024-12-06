package com.purepoint.springbootpurepoint.user.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class WatchHistoryResponseDto {

    private Long id;

    private String userId;

    private String videoId;

    private LocalDateTime watchTime;

    private LocalDateTime finishTime;

    private LocalDateTime deleteTime;
}
