package com.purepoint.springbootpurepoint.community.domain;

import com.purepoint.springbootpurepoint.user.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "community")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Community {

    @Id
    @GeneratedValue
    @Column(name = "post_id", nullable = false, unique = true)
    private UUID postId;

    @ManyToOne(fetch = FetchType.LAZY) //지연로딩
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "post_title", nullable = false)
    private String postTitle;

    @Column(name = "post_contents")
    private String postContent;

    @Column(name = "post_views")
    private Integer postView = 0;

    @Column(name = "post_at")
    @CreationTimestamp
    private LocalDateTime postAt;

    @Column(name = "post_update_at")
    private LocalDateTime postUpdateAt;

    @Column(name = "post_delete_at")
    private LocalDateTime postDeleteAt;

    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

}