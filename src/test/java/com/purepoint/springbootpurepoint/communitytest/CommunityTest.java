package com.purepoint.springbootpurepoint.communitytest;

import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.CommunityRequestDto;
import com.purepoint.springbootpurepoint.community.dto.CommunityResponseDto;
import com.purepoint.springbootpurepoint.community.mapper.CommunityMapper;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CommunityTest {

    private final CommunityMapper communityMapper = CommunityMapper.INSTANCE;

    @Test
    @Transactional
    @Rollback
    public void testCreatePost() {
        // given
        CommunityRequestDto.createPost requestDto = new CommunityRequestDto.createPost();
        requestDto.setUserId(UUID.randomUUID());
        requestDto.setPostTitle("글제목");
        requestDto.setPostContent("글내용");

        // when
        Community communityEntity = communityMapper.createPost(requestDto);

        // then
        assertEquals(requestDto.getUserId(), communityEntity.getUserId());
        assertEquals(requestDto.getPostTitle(), communityEntity.getPostTitle());
        assertEquals(requestDto.getPostContent(), communityEntity.getPostContent());
    }

//    @Test
//    @Transactional
//    @Rollback
//    public void testToDto() {
//        // given
//        Community communityEntity = new Community();
//        communityEntity.setBoardId(UUID.randomUUID());
//        communityEntity.setUserId(UUID.randomUUID());
//        communityEntity.setPostTitle("글제목");
//        communityEntity.setPostContent("글내용");
//
//        // when
//        CommunityResponseDto responseDto = communityMapper.toDto(communityEntity);
//
//        // then
//        assertEquals(communityEntity.getBoardId(), responseDto.getId());
//        assertEquals(communityEntity.getUserId(), responseDto.getUserId());
//        assertEquals(communityEntity.getPostTitle(), responseDto.getPostTitle());
//        assertEquals(communityEntity.getPostContent(), responseDto.getPostContent());
//    }
}
