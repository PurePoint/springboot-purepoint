package com.purepoint.springbootpurepoint.community.mapper;

import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.request.CommCreatePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.request.CommUpdatePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommDetailPostResDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommReadPostResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CommunityMapper {
    CommunityMapper INSTANCE = Mappers.getMapper(CommunityMapper.class);

    @Mapping(target = "postId", ignore = true)
    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "postTitle", target = "postTitle")
    @Mapping(source = "postContent", target = "postContent")
    @Mapping(target = "postAt", ignore = true)
    @Mapping(target = "postUpdateAt", ignore = true)
    @Mapping(target = "postDeleteAt", ignore = true)
    Community createPostToEntity(CommCreatePostReqDto commCreatePostReqDto);

    @Mapping(target = "postId", ignore = true)
    @Mapping(source = "postTitle", target = "postTitle")
    @Mapping(source = "postContent", target = "postContent")
    @Mapping(target = "postUpdateAt", expression = "java(java.time.LocalDateTime.now())")
    Community updatePostToEntity(CommUpdatePostReqDto commUpdatePostReqDto);

    @Mapping(target = "postId", ignore = true)
    @Mapping(target = "postDeleteAt", expression = "java(java.time.LocalDateTime.now())")
    Community deletePostToEntity(Community community);

    List<CommReadPostResDto> toDto(List<Community> community);


    @Mapping(source = "user.nickname", target = "postNickname")
    @Mapping(target = "comments", ignore = true)
    CommDetailPostResDto detailPostToDto(Community community);

//    @Mapping(source = "user.profileImage", target = "profileImage")
//    @Mapping(source = "user.nickname", target = "commentNickname")
//    CommDetailCommentResDto commentToDto(Comment comment);

}
