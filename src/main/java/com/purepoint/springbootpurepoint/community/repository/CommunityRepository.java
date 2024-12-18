package com.purepoint.springbootpurepoint.community.repository;

import com.purepoint.springbootpurepoint.community.domain.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * 커뮤니티(Community) 엔티티에 대한 데이터 액세스 레이어
 * <p>주요 기능:
 * <ul>
 *   <li>게시글 저장, 수정, 삭제</li>
 *   <li>특정 비디오 ID와 삭제되지 않은 게시글을 페이징 처리하여 조회</li>
 * </ul>
 */
public interface CommunityRepository extends JpaRepository<Community, UUID> {

    /**
     * 특정 비디오 ID와 관련된 삭제되지 않은 게시글을 페이징 처리하여 조회
     *
     * @param videoId 비디오 ID
     * @param pageable 페이징 정보
     * @return 페이징 처리된 커뮤니티 게시글
     */
    Page<Community> findAllByVideoIdAndPostDeleteAtIsNull(String videoId, Pageable pageable);
}
