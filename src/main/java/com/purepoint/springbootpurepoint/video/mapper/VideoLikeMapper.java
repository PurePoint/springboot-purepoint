package com.purepoint.springbootpurepoint.video.mapper;

import com.purepoint.springbootpurepoint.video.domain.VideoLike;
import com.purepoint.springbootpurepoint.video.dto.UpdateVideoLikeStatusReqDto;
import com.purepoint.springbootpurepoint.video.dto.VideoLikeStatusReqDto;
import com.purepoint.springbootpurepoint.video.dto.VideoLikeStatusResDto;
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
    VideoLike toEntity(UpdateVideoLikeStatusReqDto updateVideoLikeStatusReqDto);

    @Mappings({
            @Mapping(target = "userId", source = "userId"),
            @Mapping(target = "videoId", source = "videoId"),
            @Mapping(target = "videoLikeStatus", source = "videoLikeStatus")
    })
    VideoLikeStatusResDto toDto(VideoLike videoLike);

}
