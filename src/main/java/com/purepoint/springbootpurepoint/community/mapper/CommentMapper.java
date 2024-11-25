package com.purepoint.springbootpurepoint.community.mapper;

import com.purepoint.springbootpurepoint.community.domain.Comment;
import com.purepoint.springbootpurepoint.community.dto.request.CommCreateCommentReqDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "commentId", ignore = true)
    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "postId", target = "community.postId")
    Comment createPostToEntity(CommCreateCommentReqDto commCreateCommentReqDto);

}
