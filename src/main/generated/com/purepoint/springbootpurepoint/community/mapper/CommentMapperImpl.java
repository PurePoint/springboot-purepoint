package com.purepoint.springbootpurepoint.community.mapper;

import com.purepoint.springbootpurepoint.community.domain.Comment;
import com.purepoint.springbootpurepoint.community.dto.response.CommCommentResDto;
import com.purepoint.springbootpurepoint.user.domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T15:19:49+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.13 (Amazon.com Inc.)"
)
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommCommentResDto toDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommCommentResDto commCommentResDto = new CommCommentResDto();

        commCommentResDto.setNickname( commentUserNickname( comment ) );
        commCommentResDto.setProfileImage( commentUserProfileImage( comment ) );
        commCommentResDto.setCommentContents( comment.getCommentContents() );
        commCommentResDto.setCommentUpdateAt( comment.getCommentUpdateAt() );

        return commCommentResDto;
    }

    @Override
    public List<CommCommentResDto> toDto(List<Comment> comment) {
        if ( comment == null ) {
            return null;
        }

        List<CommCommentResDto> list = new ArrayList<CommCommentResDto>( comment.size() );
        for ( Comment comment1 : comment ) {
            list.add( toDto( comment1 ) );
        }

        return list;
    }

    private String commentUserNickname(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        User user = comment.getUser();
        if ( user == null ) {
            return null;
        }
        String nickname = user.getNickname();
        if ( nickname == null ) {
            return null;
        }
        return nickname;
    }

    private String commentUserProfileImage(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        User user = comment.getUser();
        if ( user == null ) {
            return null;
        }
        String profileImage = user.getProfileImage();
        if ( profileImage == null ) {
            return null;
        }
        return profileImage;
    }
}
