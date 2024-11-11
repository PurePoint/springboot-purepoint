package com.purepoint.springbootpurepoint.community.service;


import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.request.CommCreatePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.request.CommUpdatePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommCommentResDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommDetailPostResDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommReadPostResDto;

import java.util.List;
import java.util.UUID;

public interface CommunityService {

    /**
     * 새 게시글을 작성합니다.
     *
     * @param communityRequestDto 생성할 게시글 정보
     * @return 생성된 게시글 정보
     */
    Community createPost(CommCreatePostReqDto communityRequestDto);

    /**
     * @param communityRequestDto 수정할 게시글 정보
     * @return 수정된 게시글 정보
     */
    Community updatePost(CommUpdatePostReqDto communityRequestDto);

    /**
     * @return 조회된 게시글 정보
     */
    List<CommReadPostResDto> getPost();

    /**
     * @return 최신순으로 조회된 게시글 정보
     */
    List<CommReadPostResDto> getLatestPost();


    /**
     * @return 조회순(인기순)으로 조회할 게시글 정보
     */
    List<CommReadPostResDto> getPopularPost();

    /**
     * @param postId 삭제할 게시글 정보
     */
    void deletePost(UUID postId);

    /**
     * 게시글 ID로 상세 정보를 조회합니다.
     *
     * @param postId 게시글 ID
     * @return 조회된 게시글 상세 정보
     */
    CommDetailPostResDto getDetailPost(UUID postId);

    /**
     * 게시글 ID로 댓글 목록을 조회합니다.
     *
     * @param postId 게시글 ID
     * @return 조회된 댓글 목록
     */
    List<CommCommentResDto> getCommentsPost(UUID postId);

    // ToDo 새 댓글 생성 서비스 구현

    // ToDo 댓글 수정 서비스 구현

    // ToDo 댓글 삭제 서비스 구현
}
