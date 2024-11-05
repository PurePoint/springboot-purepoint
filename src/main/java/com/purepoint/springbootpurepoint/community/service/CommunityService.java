package com.purepoint.springbootpurepoint.community.service;


import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.CommunityRequestDto;
import com.purepoint.springbootpurepoint.community.dto.CommunityResponseDto;

import java.util.Optional;
import java.util.UUID;

public interface CommunityService {

    /**
     * 새 게시글을 작성합니다.
     *
     * @param communityRequestDto 생성할 게시글 정보
     * @return 생성된 게시글 정보
     */
    Community createPost(CommunityRequestDto.createPost communityRequestDto);

    /**
     * 작성된 게시글을 수정합니다.
     * 
     * @param boardId 수정할 게시글의 ID
     * @param newTitle 새로운 게시글 제목
     * @param newContent 새로운 게시글 내용
     * @return 수정된 게시글 정보
     */
    Community updatePost(CommunityRequestDto.updatePost communityRequestDto);

    /**
     * 게시글을 조회합니다.
     *
     * @param boardId 조회할 게시글의 ID
     * @return 조회된 게시글의 정보를 담은 Optional 객체
     */
    CommunityResponseDto getPostById(CommunityRequestDto.getBoardId communityRequestDto);

    /**
     * 최신순으로 게시글을 조회합니다.
     *
     * @param boardId 조회할 게시글의 ID
     * @return 최신순으로 조회된 게시글의 정보를 담은 Optional 객체
     */
    CommunityResponseDto getLatestPostById(CommunityRequestDto.getBoardId communityRequestDto);

    /**
     * 조회순으로 게시글을 조회합니다.
     *
     * @param boardId 조회할 게시글의 ID
     * @return 조회순으로 조회된 게시글의 정보를 담은 Optional 객체
     */
    CommunityResponseDto getPopularPostById(CommunityRequestDto.getBoardId communityRequestDto);

    /**
     * @param boardId 삭제할 게시글의 ID
     */
    void deletePost(CommunityRequestDto.getBoardId communityRequestDto);
    
}
