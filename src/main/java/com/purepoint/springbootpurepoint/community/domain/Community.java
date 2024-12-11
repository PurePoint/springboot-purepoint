package com.purepoint.springbootpurepoint.community.domain;

import com.purepoint.springbootpurepoint.playlist.domain.Playlist;
import com.purepoint.springbootpurepoint.user.domain.User;
import com.purepoint.springbootpurepoint.video.domain.Video;
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

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "video_id")
    private String videoId;

    @Column(name = "playlist_id")
    private String playlistId;

    @Column(name = "post_title", nullable = false)
    private String postTitle;

    @Column(name = "post_contents")
    private String postContent;

    @Column(name = "post_at")
    @CreationTimestamp
    private LocalDateTime postAt;

    @Column(name = "post_update_at")
    private LocalDateTime postUpdateAt;

    @Column(name = "post_delete_at")
    private LocalDateTime postDeleteAt;

    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    private Video video;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", insertable = false, updatable = false)
    private Playlist playlist;

}