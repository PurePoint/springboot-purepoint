package com.purepoint.springbootpurepoint.community.mapper;

import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.CommunityRequestDto;
import com.purepoint.springbootpurepoint.community.dto.CommunityResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper
public interface CommunityMapper {
    CommunityMapper INSTANCE = Mappers.getMapper(CommunityMapper.class);

    @Mapping(target = "boardId", ignore = true)
    @Mapping(target = "postView", constant = "0")
    @Mapping(target = "postAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "postUpdateAt", ignore = true)
    @Mapping(target = "postDeleteAt", ignore = true)
    Community createPost(CommunityRequestDto.createPost communityRequestDto);


    @Mapping(target = "boardId", ignore = true)
    @Mapping(target = "postAt", ignore = true)
    @Mapping(target = "postView", ignore = true)
    @Mapping(target = "postUpdateAt", ignore = true)
    @Mapping(target = "postDeleteAt", ignore = true)
    Community updatePost(CommunityRequestDto.updatePost updatePostDto);

    CommunityResponseDto getPost(Optional<Community> community);
}
