package com.purepoint.springbootpurepoint.community.domain;

import com.purepoint.springbootpurepoint.user.domain.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 댓글(Comment) 엔티티 클래스
 * 이 클래스는 데이터베이스의 댓글 정보를 나타냅니다.
 *
 * <p>주요 필드:
 * <ul>
 *   <li>commentId: 댓글 고유 ID (UUID)</li>
 *   <li>userId: 댓글 작성자 ID (UUID)</li>
 *   <li>postId: 댓글이 속한 게시글 ID (UUID)</li>
 *   <li>commentContents: 댓글 내용</li>
 *   <li>commentAt: 댓글 작성 시간 (자동 생성)</li>
 *   <li>commentUpdateAt: 댓글 수정 시간</li>
 *   <li>commentDeleteAt: 댓글 삭제 시간</li>
 *   <li>parentCommentId: 부모 댓글 ID (대댓글 용)</li>
 * </ul>
 *
 * <p>연관 관계:
 * <ul>
 *   <li>user: 댓글 작성자 정보 (User 엔티티와 다대일 관계)</li>
 *   <li>community: 댓글이 속한 게시글 정보 (Community 엔티티와 다대일 관계)</li>
 * </ul>
 */
@Entity
@Table(name = "comment")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {

    /**
     * 댓글 고유 ID
     */
    @Id
    @GeneratedValue
    @Column(name = "comment_id", nullable = false)
    private UUID commentId;

    /**
     * 댓글 작성자 ID
     */
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    /**
     * 댓글이 속한 게시글 ID
     */
    @Column(name = "post_id", nullable = false)
    private UUID postId;

    /**
     * 댓글 내용
     */
    @Column(name = "comment_contents", nullable = false)
    private String commentContents;

    /**
     * 댓글 작성 시간 (자동 생성)
     */
    @Column(name = "comment_at")
    @CreationTimestamp
    private LocalDateTime commentAt;

    /**
     * 댓글 수정 시간
     */
    @Column(name = "comment_update_at")
    private LocalDateTime commentUpdateAt;

    /**
     * 댓글 삭제 시간
     */
    @Column(name = "comment_delete_at")
    private LocalDateTime commentDeleteAt;

    /**
     * 부모 댓글 ID (대댓글 용)
     */
    @Column(name = "parent_comment_id")
    private UUID parentCommentId;

    /**
     * 댓글 작성자 정보 (User 엔티티와 다대일 관계)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    /**
     * 댓글이 속한 게시글 정보 (Community 엔티티와 다대일 관계)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", insertable = false, updatable = false)
    private Community community;

}
