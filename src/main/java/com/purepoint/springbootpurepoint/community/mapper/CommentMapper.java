package com.purepoint.springbootpurepoint.community.mapper;

import com.purepoint.springbootpurepoint.community.domain.Comment;
import com.purepoint.springbootpurepoint.community.dto.CommCommentResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "user.nickname", target = "nickname")
    @Mapping(source = "user.profilePicture", target = "profilePicture")
    CommCommentResDto toDto(Comment comment);  // Comment 하나를 변환하는 메서드

    List<CommCommentResDto> toDto(List<Comment> comment);  // List<Comment>를 List<CommCommentResDto>로 변환하는 메서드
}
