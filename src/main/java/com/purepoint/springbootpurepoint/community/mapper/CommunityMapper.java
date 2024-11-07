package com.purepoint.springbootpurepoint.community.mapper;

import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommunityMapper {
    CommunityMapper INSTANCE = Mappers.getMapper(CommunityMapper.class);

    @Mapping(target = "postId", ignore = true)
    @Mapping(target = "postView", constant = "0")
    @Mapping(target = "postAt", ignore = true)
    @Mapping(target = "postUpdateAt", ignore = true)
    @Mapping(target = "postDeleteAt", ignore = true)
    Community createPostToEntity(CommCreatePostReqDto commCreatePostReqDto);


    @Mapping(target = "postId", ignore = true)
    @Mapping(target = "postAt", ignore = true)
    @Mapping(target = "postView", ignore = true)
    @Mapping(target = "postUpdateAt", ignore = true)
    @Mapping(target = "postDeleteAt", ignore = true)
    void updatePostToEntity(CommUpdatePostReqDto commUpdatePostReqDto, @MappingTarget Community community);

    CommReadPostResDto map(Community community);

    List<CommReadPostResDto> toDto(List<Community> community);

    CommDetailPostResDto detailPostToDto(Optional<Community> community);

}
