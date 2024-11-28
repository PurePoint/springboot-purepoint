package com.purepoint.springbootpurepoint.youtube.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.UUID;

@RedisHash("videoLike")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VideoLike {

    @Id
    private String id;
    @Indexed
    private String videoId;
    @Indexed
    private UUID userId;
    private String videoLikeStatus;
}
