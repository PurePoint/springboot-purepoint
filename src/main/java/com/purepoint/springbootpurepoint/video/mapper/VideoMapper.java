package com.purepoint.springbootpurepoint.video.mapper;

import com.purepoint.springbootpurepoint.video.domain.Video;
import com.purepoint.springbootpurepoint.video.dto.VideoDto;
import com.purepoint.springbootpurepoint.video.dto.VideoPagingDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class VideoMapper {

    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;

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
                .playlistId(video.getPlaylistId())
                .videoPosition(video.getVideoPosition())
                .build();
    }

    public Page<VideoDto> toDtoWithLikes(Page<Video> videos, List<Long> videoLikes) {
        return videos.map(video -> {
            int index = videos.getContent().indexOf(video);
            Long videoLikeCount = (index >= 0 && index < videoLikes.size()) ? videoLikes.get(index) : 0L;
            return toDtoWithLikes(video, videoLikeCount);
        });
    }

    public VideoPagingDto toVideoPagingDto(List<VideoDto> videoDtos, Page<Video> videos) {
        return VideoPagingDto.builder()
                .content(videoDtos)
                .totalPages(videos.getTotalPages())
                .totalElements(videos.getTotalElements())
                .currentPage(videos.getNumber())
                .pageSize(videos.getSize())
                .build();
    }

    public Long getVideoLikeCount(String videoId) {
        String redisKey = "video:" + videoId + ":likes";
        String count = (String) redisTemplate.opsForValue().get(redisKey);
        return count != null ? Long.parseLong(count) : 0L;
    }

    public List<VideoDto> toDtoWithLikes(List<Video> videos, List<Long> videoLikes) {
        return videos.stream()
                .map(video -> toDtoWithLikes(video, getVideoLikeCount(video.getVideoId())))
                .collect(Collectors.toList());
    }
}
