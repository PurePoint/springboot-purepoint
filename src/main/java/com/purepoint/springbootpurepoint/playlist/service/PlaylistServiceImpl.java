package com.purepoint.springbootpurepoint.playlist.service;

import com.purepoint.springbootpurepoint.playlist.domain.Playlist;
import com.purepoint.springbootpurepoint.playlist.dto.PlaylistDto;
import com.purepoint.springbootpurepoint.playlist.mapper.PlaylistMapper;
import com.purepoint.springbootpurepoint.playlist.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final PlaylistMapper playlistMapper;


    @Override
    public List<PlaylistDto> searchYoutubePlaylist(String query) {
        List<Playlist> playlists = playlistRepository.findByPlaylistTitleContaining(query);

        // 각 영상의 좋아요 수를 Redis에서 검색하여 정렬
        List<Playlist> sortedPlaylists = playlists.stream()
                .sorted((p1, p2) -> {
                    Long likes1 = (Long) redisTemplate.opsForValue().get("video:likes:" + p1.getPlaylistId());
                    Long likes2 = (Long) redisTemplate.opsForValue().get("video:likes:" + p2.getPlaylistId());
                    likes1 = (likes1 == null) ? 0 : likes1;
                    likes2 = (likes2 == null) ? 0 : likes2;
                    return likes2.compareTo(likes1);
                })
                .limit(5) // 상위 5개 영상 선택
                .toList();

        return sortedPlaylists.stream()
                .map(playlistMapper::toDto)
                .collect(Collectors.toList());
    }
}
