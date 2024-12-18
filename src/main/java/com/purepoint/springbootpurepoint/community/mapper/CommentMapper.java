package com.purepoint.springbootpurepoint.community.mapper;

import com.purepoint.springbootpurepoint.community.domain.Comment;
import com.purepoint.springbootpurepoint.community.dto.request.CommCreateCommentReqDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommCommentResDto;
import com.purepoint.springbootpurepoint.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 댓글(Comment) 엔티티와 DTO 간의 매핑을 담당하는 Mapper 인터페이스
 * <p>주요 기능:
 * <ul>
 *   <li>CommCreateCommentReqDto → Comment 엔티티 변환</li>
 *   <li>Comment 엔티티 → CommCommentResDto 변환</li>
 * </ul>
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {

    /**
     * Mapper 인스턴스 생성
     */
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    /**
     * 댓글 생성 요청 DTO를 Comment 엔티티로 변환
     *
     * @param commCreateCommentReqDto 댓글 생성 요청 데이터
     * @return Comment 엔티티 객체
     */
    @Mappings({
            @Mapping(target = "commentId", ignore = true), // commentId는 자동 생성됨
            @Mapping(source = "userId", target = "userId"),
            @Mapping(source = "postId", target = "postId"),
            @Mapping(source = "commentContents", target = "commentContents"),
            @Mapping(target = "commentAt", ignore = true), // 생성 시간은 자동 설정됨
            @Mapping(target = "commentUpdateAt", ignore = true),
            @Mapping(target = "commentDeleteAt", ignore = true),
            @Mapping(source = "parentCommentId", target = "parentCommentId")
    })
    Comment createPostToEntity(CommCreateCommentReqDto commCreateCommentReqDto);

    /**
     * Comment 엔티티를 CommCommentResDto로 변환
     *
     * @param comment 댓글 엔티티
     * @return 댓글 응답 DTO
     */
    default CommCommentResDto toCommCommentResDto(Comment comment) {
        User user = comment.getUser(); // 댓글 작성자 정보 가져오기
        return CommCommentResDto.builder()
                .commentId(comment.getCommentId())
                .parentCommentId(comment.getParentCommentId())
                .userId(comment.getUserId())
                .profileImage(user != null ? user.getProfileImage() : null) // 작성자 프로필 이미지
                .commentNickname(user != null ? user.getNickname() : null) // 작성자 닉네임
                .commentContents(comment.getCommentContents())
                .commentAt(comment.getCommentAt())
                .commentUpdateAt(comment.getCommentUpdateAt())
                .commentDeleteAt(comment.getCommentDeleteAt())
                .build();
    }

}
