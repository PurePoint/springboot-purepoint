package com.purepoint.springbootpurepoint.community.mapper;

import com.purepoint.springbootpurepoint.community.domain.Community;
import com.purepoint.springbootpurepoint.community.dto.request.CommCreatePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.request.CommUpdatePostReqDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommDetailPostResDto;
import com.purepoint.springbootpurepoint.community.dto.response.CommReadPostResDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T15:19:49+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.13 (Amazon.com Inc.)"
)
public class CommunityMapperImpl implements CommunityMapper {

    @Override
    public Community createPostToEntity(CommCreatePostReqDto commCreatePostReqDto) {
        if ( commCreatePostReqDto == null ) {
            return null;
        }

        Community community = new Community();

        community.setPostTitle( commCreatePostReqDto.getPostTitle() );
        community.setPostContent( commCreatePostReqDto.getPostContent() );

        community.setPostView( 0 );

        return community;
    }

    @Override
    public void updatePostToEntity(CommUpdatePostReqDto commUpdatePostReqDto, Community community) {
        if ( commUpdatePostReqDto == null ) {
            return;
        }

        community.setPostTitle( commUpdatePostReqDto.getPostTitle() );
        community.setPostContent( commUpdatePostReqDto.getPostContent() );
    }

    @Override
    public CommReadPostResDto map(Community community) {
        if ( community == null ) {
            return null;
        }

        CommReadPostResDto commReadPostResDto = new CommReadPostResDto();

        commReadPostResDto.setPostTitle( community.getPostTitle() );
        commReadPostResDto.setPostView( community.getPostView() );
        commReadPostResDto.setPostAt( community.getPostAt() );

        return commReadPostResDto;
    }

    @Override
    public List<CommReadPostResDto> toDto(List<Community> community) {
        if ( community == null ) {
            return null;
        }

        List<CommReadPostResDto> list = new ArrayList<CommReadPostResDto>( community.size() );
        for ( Community community1 : community ) {
            list.add( map( community1 ) );
        }

        return list;
    }

    @Override
    public CommDetailPostResDto detailPostToDto(Optional<Community> community) {
        if ( community == null ) {
            return null;
        }

        CommDetailPostResDto commDetailPostResDto = new CommDetailPostResDto();

        return commDetailPostResDto;
    }
}
