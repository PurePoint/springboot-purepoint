package com.purepoint.springbootpurepoint.community.mapper;

import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.request.CommCreatePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.request.CommUpdatePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommDetailPostResDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommPagingResDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommReadPostResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CommunityMapper {
    CommunityMapper INSTANCE = Mappers.getMapper(CommunityMapper.class);

    @Mappings({
            @Mapping(target = "postId", ignore = true),
            @Mapping(source = "userId", target = "userId"),
            @Mapping(source = "videoId", target = "videoId"),
            @Mapping(source = "playlistId", target = "playlistId"),
            @Mapping(source = "postTitle", target = "postTitle"),
            @Mapping(source = "postContent", target = "postContent"),
            @Mapping(target = "postAt", ignore = true),
            @Mapping(target = "postUpdateAt", ignore = true),
            @Mapping(target = "postDeleteAt", ignore = true)
    })
    Community createPostToEntity(CommCreatePostReqDto commCreatePostReqDto);

    default Community updatePostToEntity(Community community, CommUpdatePostReqDto commUpdatePostReqDto) {
        return Community.builder()
                .postId(community.getPostId())
                .postTitle(commUpdatePostReqDto.getPostTitle())
                .postContent(commUpdatePostReqDto.getPostContent())
                .postUpdateAt(LocalDateTime.now())
                .postAt(community.getPostAt())
                .postDeleteAt(community.getPostDeleteAt())
                .playlistId(community.getPlaylistId())
                .videoId(community.getVideoId())
                .userId(community.getUserId())
                .build();
    }

    @Mapping(target = "postId", ignore = true)
    @Mapping(target = "postDeleteAt", expression = "java(java.time.LocalDateTime.now())")
    Community deletePostToEntity(Community community);

    @Mappings({
            @Mapping(source = "community.postId", target = "postId"),
            @Mapping(source = "community.userId", target = "userId"),
            @Mapping(source = "community.postTitle", target = "postTitle"),
            @Mapping(source = "community.postContent", target = "postContent"),
            @Mapping(source = "community.postAt", target = "postAt"),
            @Mapping(source = "community.postUpdateAt", target = "postUpdateAt")
    })
    CommReadPostResDto toDto(Community community, String nickname);

    // 기본적으로 List<Community>와 List<String> 매핑 지원
    default List<CommReadPostResDto> toDto(List<Community> communities, List<String> nicknames) {
        if (communities == null || nicknames == null || communities.size() != nicknames.size()) {
            throw new IllegalArgumentException("Communities and nicknames must have the same size.");
        }

        List<CommReadPostResDto> result = new ArrayList<>();
        for (int i = 0; i < communities.size(); i++) {
            result.add(toDto(communities.get(i), nicknames.get(i)));
        }
        return result;
    }

    default CommPagingResDto toCommPagingDto(List<CommReadPostResDto> commReadPostResDtos, Page<Community> communities) {
        return CommPagingResDto.builder()
                .content(commReadPostResDtos)
                .totalPages(communities.getTotalPages())
                .totalElements(communities.getTotalElements())
                .currentPage(communities.getNumber())
                .pageSize(communities.getSize())
                .build();
    }


    List<CommReadPostResDto> toDto(List<Community> community);


    @Mapping(source = "user.nickname", target = "postNickname")
    @Mapping(target = "comments", ignore = true)
    CommDetailPostResDto detailPostToDto(Community community);

//    @Mapping(source = "user.profileImage", target = "profileImage")
//    @Mapping(source = "user.nickname", target = "commentNickname")
//    CommDetailCommentResDto commentToDto(Comment comment);

}
