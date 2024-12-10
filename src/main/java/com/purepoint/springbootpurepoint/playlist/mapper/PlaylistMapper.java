package com.purepoint.springbootpurepoint.playlist.mapper;

import com.purepoint.springbootpurepoint.playlist.domain.Playlist;
import com.purepoint.springbootpurepoint.playlist.dto.PlaylistDto;
import com.purepoint.springbootpurepoint.playlist.dto.PlaylistIdResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class PlaylistMapper {

    @Autowired
    protected RedisTemplate<String, Object> redisTemplate;

    public PlaylistIdResDto toPlaylistResDto(String playlistId) {
        return PlaylistIdResDto.builder()
                .playlistId(playlistId)
                .build();
    }

    @Mapping(target = "playlistLikes", ignore = true)
    public abstract PlaylistDto toDto(Playlist playlist);

    public abstract List<PlaylistDto> toDto(List<Playlist> playlists);

    public PlaylistDto toDtoWithLikes(Playlist playlist, Long playlistLikes) {
        return PlaylistDto.builder()
                .playlistId(playlist.getPlaylistId())
                .playlistTitle(playlist.getPlaylistTitle())
                .playlistDescription(playlist.getPlaylistDescription())
                .playlistPublishedAt(playlist.getPlaylistPublishedAt())
                .playlistThumbnail(playlist.getPlaylistThumbnail())
                .playlistLikes(playlistLikes)
                .channelId(playlist.getChannelId())
                .channelTitle(playlist.getChannelTitle())
                .build();
    }

    public List<PlaylistDto> toDtoWithLikes(List<Playlist> playlists, List<Long> playlistLikes) {
        return playlists.stream()
                .map(playlist -> toDtoWithLikes(playlist, getPlaylistLikeCount(playlist.getPlaylistId())))
                .collect(Collectors.toList());
    }

    public Long getPlaylistLikeCount(String playlistId) {
        String redisKey = "playlist:" + playlistId + ":likes";
        String count = (String) redisTemplate.opsForValue().get(redisKey);
        return count != null ? Long.parseLong(count) : 0L;
    }
}
