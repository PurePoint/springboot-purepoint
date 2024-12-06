package com.purepoint.springbootpurepoint.user.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_watch_history")
public class WatchHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "video_id", nullable = false)
    private String videoId;

    @Column(name = "watch_time", nullable = false)
    @CreationTimestamp
    private LocalDateTime watchTime;

    @Column(name = "finish_time")
    private LocalDateTime finishTime;

    @Column(name = "delete_time")
    private LocalDateTime deleteTime;
}
