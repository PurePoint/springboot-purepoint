package com.purepoint.springbootpurepoint.youtube.mapper;

import com.purepoint.springbootpurepoint.youtube.domain.VideoLike;
import com.purepoint.springbootpurepoint.youtube.dto.VideoLikeStatusReqDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VideoLikeMapper {

    VideoLikeMapper INSTANCE = Mappers.getMapper(VideoLikeMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "videoId", source = "videoId"),
            @Mapping(target = "userId", source = "userId"),
            @Mapping(target = "videoLikeStatus", source = "videoLikeStatus")
    })
    VideoLike toEntity(VideoLikeStatusReqDto videoLikeStatusReqDto);
}
