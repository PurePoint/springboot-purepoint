package com.purepoint.springbootpurepoint.playlist.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.UUID;

@RedisHash("playlistLike")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlaylistLike {

    @Id
    private String id;
    @Indexed
    private String playlistId;
    @Indexed
    private UUID userId;
    private String playlistLikeStatus;
}
