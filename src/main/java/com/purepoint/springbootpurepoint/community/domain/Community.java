package com.purepoint.springbootpurepoint.community.domain;

import com.purepoint.springbootpurepoint.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "community")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Community {

    @Id
    @GeneratedValue
    @Column(name = "post_id", nullable = false, unique = true)
    private UUID postId;

    @ManyToOne(fetch = FetchType.LAZY) //지연로딩
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "post_title", nullable = false)
    private String postTitle;

    @Column(name = "post_contents")
    private String postContent;

    @Column(name = "post_views")
    private Integer postView;

    @Column(name = "post_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime postAt;

    @Column(name = "post_update_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime postUpdateAt;

    @Column(name = "post_delete_at", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime postDeleteAt;

}
