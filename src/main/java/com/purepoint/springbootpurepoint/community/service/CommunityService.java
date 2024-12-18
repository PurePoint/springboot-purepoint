package com.purepoint.springbootpurepoint.community.service;


import com.purepoint.springbootpurepoint.community.domain.Comment;
import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.request.CommCreateCommentReqDto;
import com.purepoint.springbootpurepoint.community.dto.request.CommCreatePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.request.CommDeletePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.request.CommUpdatePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommDetailPostResDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommPagingResDto;
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
    CommPagingResDto getPost(String videoId, int page, int size);

    /**
     * @return 최신순으로 조회된 게시글 정보
     */
    List<CommReadPostResDto> getLatestPost();


    /**
     * @return 조회순(인기순)으로 조회할 게시글 정보
     */
    List<CommReadPostResDto> getPopularPost();

    /**
     * @param commDeletePostReqDto 삭제할 게시글 정보
     */
    void deletePost(CommDeletePostReqDto commDeletePostReqDto);

    /**
     * @param commentRequestDto 생성할 댓글 정보
     * @return 생성된 댓글 정보
     */
    // ToDo 새 댓글 생성 서비스 구현
//    Comment createComment(CommCreateCommentReqDto commentRequestDto);

    // ToDo 댓글 수정 서비스 구현

    // ToDo 댓글 삭제 서비스 구현
}
