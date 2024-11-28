package com.purepoint.springbootpurepoint.youtube.mapper;

import com.purepoint.springbootpurepoint.youtube.domain.Video;
import com.purepoint.springbootpurepoint.youtube.dto.VideoDto;
import com.purepoint.springbootpurepoint.youtube.repository.VideoLikeRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class VideoMapper {

    @Autowired
    protected VideoLikeRepository videoLikeRepository;

    @Mapping(target = "videoLikes", ignore = true)
    public abstract VideoDto toDto(Video video);

    public abstract List<VideoDto> toDto(List<Video> videos);

    public VideoDto toDtoWithLikes(Video video, Long videoLikes) {
        return VideoDto.builder()
                .videoId(video.getVideoId())
                .videoTitle(video.getVideoTitle())
                .videoDescription(video.getVideoDescription())
                .videoPublishedAt(video.getVideoPublishedAt())
                .videoThumbnail(video.getVideoThumbnail())
                .videoLikes(videoLikes)
                .build();
    }

    public List<VideoDto> toDtoWithLikes(List<Video> videos, List<Long> videoLikes) {
        return videos.stream()
                .map(video -> toDtoWithLikes(video, (long) videoLikeRepository.findAllByVideoId(video.getVideoId()).size()))
                .collect(Collectors.toList());
    }
}
