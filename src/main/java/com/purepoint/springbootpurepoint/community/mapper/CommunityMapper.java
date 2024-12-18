package com.purepoint.springbootpurepoint.community.mapper;

import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.request.CommCreatePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.request.CommUpdatePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommCommentResDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommPagingResDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommReadPostResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * CommunityMapper는 Community 엔티티와 관련된 다양한 DTO 변환을 담당하는 인터페이스입니다.
 * MapStruct를 사용하여 매핑 로직을 생성합니다.
 */
@Mapper(componentModel = "spring")
public interface CommunityMapper {
    CommunityMapper INSTANCE = Mappers.getMapper(CommunityMapper.class);

    /**
     * CommCreatePostReqDto를 Community 엔티티로 변환합니다.
     *
     * @param commCreatePostReqDto 글 작성에 필요한 정보
     * @return 저장된 글 정보를 포함하는 Community 엔티티
     */
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

    /**
     * Community 엔티티와 CommUpdatePostReqDto를 사용하여 수정된 Community 엔티티를 반환합니다.
     *
     * @param community 기존 글 정보
     * @param commUpdatePostReqDto 수정할 글 정보
     * @return 수정된 글 정보를 포함하는 Community 엔티티
     */
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
                .user(community.getUser())
                .video(community.getVideo())
                .playlist(community.getPlaylist())
                .build();
    }

    /**
     * Community 엔티티를 삭제 상태로 변환합니다.
     *
     * @param community 삭제할 글 정보
     * @return 삭제된 상태의 Community 엔티티
     */
    default Community deletePostToEntity(Community community) {
        return Community.builder()
                .postId(community.getPostId())
                .postTitle(community.getPostTitle())
                .postContent(community.getPostContent())
                .postAt(community.getPostAt())
                .postUpdateAt(community.getPostUpdateAt())
                .postDeleteAt(LocalDateTime.now())
                .playlistId(community.getPlaylistId())
                .videoId(community.getVideoId())
                .userId(community.getUserId())
                .user(community.getUser())
                .video(community.getVideo())
                .playlist(community.getPlaylist())
                .build();
    }

    /**
     * Community 엔티티와 추가 정보를 사용하여 CommReadPostResDto를 생성합니다.
     *
     * @param community 글 정보
     * @param nickname 닉네임 정보
     * @param comments 댓글 정보
     * @return CommReadPostResDto에 담긴 글 정보
     */
    @Mappings({
            @Mapping(source = "community.postId", target = "postId"),
            @Mapping(source = "community.userId", target = "userId"),
            @Mapping(source = "community.postTitle", target = "postTitle"),
            @Mapping(source = "community.postContent", target = "postContent"),
            @Mapping(source = "community.postAt", target = "postAt"),
            @Mapping(source = "community.postUpdateAt", target = "postUpdateAt"),
            @Mapping(source = "community.postDeleteAt", target = "postDeleteAt"),
            @Mapping(source = "nickname", target = "nickname"),
            @Mapping(source = "comments", target = "comments")
    })
    CommReadPostResDto toCommReadPostResDto(Community community, String nickname, List<CommCommentResDto> comments);

    /**
     * Community 리스트, 닉네임 리스트, 댓글 정보를 사용하여 CommReadPostResDto 리스트를 생성합니다.
     *
     * @param communities 글 리스트 정보
     * @param nicknames 닉네임 리스트 정보
     * @param commCommentResDtos 댓글 리스트 정보
     * @return CommReadPostResDto 리스트
     * @throws IllegalArgumentException 리스트 크기가 일치하지 않을 경우
     */
    default List<CommReadPostResDto> toCommReadPostResDto(List<Community> communities, List<String> nicknames, Map<UUID, List<CommCommentResDto>> commCommentResDtos) {
        if (communities == null || nicknames == null || commCommentResDtos == null || communities.size() != nicknames.size() || commCommentResDtos.size() != communities.size()) {
            throw new IllegalArgumentException("Communities and nicknames and comments must have the same size.");
        }

        List<CommReadPostResDto> result = new ArrayList<>();
        for (int i = 0; i < communities.size(); i++) {
            Community community = communities.get(i);
            List<CommCommentResDto> comments = commCommentResDtos.get(community.getPostId());
            result.add(toCommReadPostResDto(communities.get(i), nicknames.get(i), comments));
        }
        return result;
    }

    /**
     * CommReadPostResDto 리스트와 페이지 정보를 사용하여 CommPagingResDto를 생성합니다.
     *
     * @param commReadPostResDtos 글 리스트 정보
     * @param communities 글 페이징 정보
     * @return 페이징 처리된 CommPagingResDto
     */
    default CommPagingResDto toCommPagingDto(List<CommReadPostResDto> commReadPostResDtos, Page<Community> communities) {
        return CommPagingResDto.builder()
                .content(commReadPostResDtos)
                .totalPages(communities.getTotalPages())
                .totalElements(communities.getTotalElements())
                .currentPage(communities.getNumber())
                .pageSize(communities.getSize())
                .build();
    }

}
