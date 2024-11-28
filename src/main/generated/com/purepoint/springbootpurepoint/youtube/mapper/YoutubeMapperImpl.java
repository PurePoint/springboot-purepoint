package com.purepoint.springbootpurepoint.youtube.mapper;

import com.purepoint.springbootpurepoint.youtube.domain.Youtube;
import com.purepoint.springbootpurepoint.youtube.dto.YoutubeDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T15:19:48+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.13 (Amazon.com Inc.)"
)
public class YoutubeMapperImpl implements YoutubeMapper {

    @Override
    public List<YoutubeDto> toDto(List<Youtube> youtube) {
        if ( youtube == null ) {
            return null;
        }

        List<YoutubeDto> list = new ArrayList<YoutubeDto>( youtube.size() );
        for ( Youtube youtube1 : youtube ) {
            list.add( youtubeToYoutubeDto( youtube1 ) );
        }

        return list;
    }

    protected YoutubeDto youtubeToYoutubeDto(Youtube youtube) {
        if ( youtube == null ) {
            return null;
        }

        YoutubeDto youtubeDto = new YoutubeDto();

        youtubeDto.setVideoId( youtube.getVideoId() );
        youtubeDto.setVideoTitle( youtube.getVideoTitle() );
        youtubeDto.setVideoDescription( youtube.getVideoDescription() );
        youtubeDto.setVideoPublishedAt( youtube.getVideoPublishedAt() );
        youtubeDto.setVideoThumbnail( youtube.getVideoThumbnail() );

        return youtubeDto;
    }
}
