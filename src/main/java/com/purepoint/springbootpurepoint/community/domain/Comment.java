package com.purepoint.springbootpurepoint.community.domain;

import com.purepoint.springbootpurepoint.user.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comment")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id", nullable = false, unique = true)
    private UUID commentId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "post_id", nullable = false)
    private UUID postId;

    @Column(name = "comment_contents", nullable = false)
    private String commentContents;

    @Column(name = "comment_at")
    @CreationTimestamp
    private LocalDateTime commentAt;

    @Column(name = "comment_update_at")
    private LocalDateTime commentUpdateAt;

    @Column(name = "comment_delete_at")
    private LocalDateTime commentDeleteAt;

    @Column(name = "depth", nullable = false)
    private Integer depth = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", insertable = false, updatable = false)
    private Community community;

}