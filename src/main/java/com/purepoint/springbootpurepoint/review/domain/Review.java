package com.purepoint.springbootpurepoint.review.domain;

import com.purepoint.springbootpurepoint.user.domain.User;
import com.purepoint.springbootpurepoint.video.domain.Video;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "review")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review {

    @Id
    @GeneratedValue
    @Column(name = "review_id", nullable = false)
    private UUID reviewId;

    @Column(name = "video_id", nullable = false)
    private String videoId;

    @Column(name = "review_content", nullable = false)
    private String reviewContent;

    @Column(name = "review_rating")
    private Integer reviewRating;

    @Column(name = "review_at")
    @CreationTimestamp
    private LocalDateTime reviewAt;

    @Column(name = "review_update_at")
    private LocalDateTime reviewUpdateAt;

    @Column(name = "review_delete_at")
    private LocalDateTime reviewDeleteAt;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    private Video video;

}
