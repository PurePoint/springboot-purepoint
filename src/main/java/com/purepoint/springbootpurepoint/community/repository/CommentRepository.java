package com.purepoint.springbootpurepoint.community.repository;

import com.purepoint.springbootpurepoint.community.domain.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * 댓글(Comment) 엔티티에 대한 데이터 액세스 레이어
 * <p>주요 기능:
 * <ul>
 *   <li>댓글 저장, 수정, 삭제</li>
 *   <li>게시글 ID를 기준으로 댓글 목록 조회</li>
 * </ul>
 */
public interface CommentRepository extends JpaRepository<Comment, UUID> {

    /**
     * 특정 게시글에 속한 모든 댓글을 조회
     *
     * @param postId 게시글 ID
     * @return 댓글 리스트
     */
    List<Comment> findByPostId(UUID postId, Sort sort);
}
