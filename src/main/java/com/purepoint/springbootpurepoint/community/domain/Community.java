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

/**
 * 커뮤니티(Community) 엔티티 클래스
 * 이 클래스는 데이터베이스의 커뮤니티 게시글 정보를 나타냅니다.
 *
 * <p>주요 필드:
 * <ul>
 *   <li>postId: 게시글 고유 ID (UUID)</li>
 *   <li>userId: 작성자 ID (UUID)</li>
 *   <li>videoId: 관련된 동영상 ID</li>
 *   <li>playlistId: 관련된 재생목록 ID</li>
 *   <li>postTitle: 게시글 제목</li>
 *   <li>postContent: 게시글 내용</li>
 *   <li>postAt: 게시글 작성 시간 (자동 생성)</li>
 *   <li>postUpdateAt: 게시글 수정 시간</li>
 *   <li>postDeleteAt: 게시글 삭제 시간</li>
 * </ul>
 *
 * <p>연관 관계:
 * <ul>
 *   <li>comments: 게시글에 달린 댓글 리스트 (Comment 엔티티와 일대다 관계)</li>
 *   <li>user: 게시글 작성자 정보 (User 엔티티와 다대일 관계)</li>
 *   <li>video: 게시글에 관련된 동영상 정보 (Video 엔티티와 다대일 관계)</li>
 *   <li>playlist: 게시글에 관련된 재생목록 정보 (Playlist 엔티티와 다대일 관계)</li>
 * </ul>
 */
@Entity
@Table(name = "community")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Community {

    /**
     * 게시글 고유 ID
     */
    @Id
    @GeneratedValue
    @Column(name = "post_id", nullable = false)
    private UUID postId;

    /**
     * 작성자 ID
     */
    @Column(name = "user_id")
    private UUID userId;

    /**
     * 관련된 동영상 ID
     */
    @Column(name = "video_id")
    private String videoId;

    /**
     * 관련된 재생목록 ID
     */
    @Column(name = "playlist_id")
    private String playlistId;

    /**
     * 게시글 제목
     */
    @Column(name = "post_title")
    private String postTitle;

    /**
     * 게시글 내용
     */
    @Column(name = "post_contents")
    private String postContent;

    /**
     * 게시글 작성 시간 (자동 생성)
     */
    @Column(name = "post_at")
    @CreationTimestamp
    private LocalDateTime postAt;

    /**
     * 게시글 수정 시간
     */
    @Column(name = "post_update_at")
    private LocalDateTime postUpdateAt;

    /**
     * 게시글 삭제 시간
     */
    @Column(name = "post_delete_at")
    private LocalDateTime postDeleteAt;

    /**
     * 게시글에 달린 댓글 리스트 (Comment 엔티티와 일대다 관계)
     */
    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    /**
     * 게시글 작성자 정보 (User 엔티티와 다대일 관계)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    /**
     * 게시글에 관련된 동영상 정보 (Video 엔티티와 다대일 관계)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    private Video video;

    /**
     * 게시글에 관련된 재생목록 정보 (Playlist 엔티티와 다대일 관계)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id", insertable = false, updatable = false)
    private Playlist playlist;

}
