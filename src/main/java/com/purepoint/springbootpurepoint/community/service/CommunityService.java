package com.purepoint.springbootpurepoint.community.service;

import com.purepoint.springbootpurepoint.community.domain.Comment;
import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.request.*;
import com.purepoint.springbootpurepoint.community.dto.response.CommPagingResDto;

/**
 * 커뮤니티 서비스 인터페이스로, 게시글과 댓글 관련 비즈니스 로직을 정의합니다.
 */
public interface CommunityService {

    /**
     * 새 게시글을 작성합니다.
     *
     * @param communityRequestDto 게시글 생성 요청 데이터 전송 객체
     * @return 생성된 게시글 객체
     */
    Community createPost(CommCreatePostReqDto communityRequestDto);

    /**
     * 게시글을 수정합니다.
     *
     * @param communityRequestDto 게시글 수정 요청 데이터 전송 객체
     * @return 수정된 게시글 객체
     */
    Community updatePost(CommUpdatePostReqDto communityRequestDto);

    /**
     * 특정 비디오 ID에 해당하는 게시글 목록을 페이징 처리하여 조회합니다.
     *
     * @param videoId 게시글이 속한 비디오 ID
     * @param page 조회할 페이지 번호 (0부터 시작)
     * @param size 한 페이지당 조회할 게시글 수
     * @return 조회된 게시글 목록 및 페이징 정보 데이터 전송 객체
     */
    CommPagingResDto getPost(String videoId, int page, int size);

    /**
     * 게시글을 삭제합니다.
     *
     * @param commDeletePostReqDto 게시글 삭제 요청 데이터 전송 객체
     * @return 삭제된 게시글 객체
     */
    Community deletePost(CommDeletePostReqDto commDeletePostReqDto);

    /**
     * 새 댓글을 작성합니다.
     *
     * @param commentRequestDto 댓글 생성 요청 데이터 전송 객체
     * @return 생성된 댓글 객체
     */
    Comment createComment(CommCreateCommentReqDto commentRequestDto);

    /**
     * 댓글을 수정합니다.
     *
     * @param commentRequestDto 댓글 수정 요청 데이터 전송 객체
     * @return 수정된 댓글 객체
     */
    Comment updateComment(CommUpdateCommentReqDto commentRequestDto);

    /**
     * 댓글을 삭제합니다.
     *
     * @param commDeleteCommentReqDto 댓글 삭제 요청 데이터 전송 객체
     * @return 삭제된 댓글 객체
     */
    Comment deleteComment(CommDeleteCommentReqDto commDeleteCommentReqDto);
}
