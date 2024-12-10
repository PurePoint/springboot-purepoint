package com.purepoint.springbootpurepoint.playlist.service;

import com.purepoint.springbootpurepoint.playlist.domain.Playlist;
import com.purepoint.springbootpurepoint.playlist.domain.PlaylistLike;
import com.purepoint.springbootpurepoint.playlist.dto.*;
import com.purepoint.springbootpurepoint.playlist.mapper.PlaylistLikeMapper;
import com.purepoint.springbootpurepoint.playlist.mapper.PlaylistMapper;
import com.purepoint.springbootpurepoint.playlist.repository.PlaylistLikeRepository;
import com.purepoint.springbootpurepoint.playlist.repository.PlaylistRepository;
import com.purepoint.springbootpurepoint.user.domain.User;
import com.purepoint.springbootpurepoint.user.repository.UserRepository;
import com.purepoint.springbootpurepoint.video.domain.Video;
import com.purepoint.springbootpurepoint.video.dto.VideoDto;
import com.purepoint.springbootpurepoint.video.mapper.VideoMapper;
import com.purepoint.springbootpurepoint.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final RedisTemplate<String, Object> redisTemplate;
    private final PlaylistMapper playlistMapper;
    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;
    private final UserRepository userRepository;
    private final PlaylistLikeRepository playlistLikeRepository;
    private final PlaylistLikeMapper playlistLikeMapper = PlaylistLikeMapper.INSTANCE;

    // 유튜브 playlistLikes 조회
    public PlaylistLikesResDto getPlaylistLikes(String playlistId) {
        String redisKey = "playlist:" + playlistId + ":likes";
        String count = (String) redisTemplate.opsForValue().get(redisKey);
        Long playlistLikes = count != null ? Long.parseLong(count) : 0L;

        return playlistLikeMapper.toPlaylistLikesResDtoWithLikes(playlistId, playlistLikes);
    }

    // 유튜브 playlist 검색
    @Override
    public List<PlaylistDto> searchYoutubePlaylist(String query) {
        List<Playlist> playlists = playlistRepository.findByPlaylistTitleContaining(query);

        // 각 영상의 좋아요 수를 Redis에서 검색하여 정렬
        List<Playlist> sortedPlaylists = playlists.stream()
                .sorted((p1, p2) -> {
                    Long likes1 = (Long) redisTemplate.opsForValue().get("playlist:likes:" + p1.getPlaylistId());
                    Long likes2 = (Long) redisTemplate.opsForValue().get("playlist:likes:" + p2.getPlaylistId());
                    likes1 = (likes1 == null) ? 0 : likes1;
                    likes2 = (likes2 == null) ? 0 : likes2;
                    return likes2.compareTo(likes1);
                })
                .limit(5) // 상위 5개 영상 선택
                .toList();


        List<Long> playlistLikes = sortedPlaylists.stream()
                .map(playlist -> playlistMapper.getPlaylistLikeCount(playlist.getPlaylistId()))
                .toList();

        return playlistMapper.toDtoWithLikes(sortedPlaylists, playlistLikes);
    }

    // 각 playlist의 영상 조회
    @Override
    public List<VideoDto> getPlaylistVideos(String playlistId) {
        List<Video> playlistVideos = videoRepository.findByPlaylistIdOrderByVideoPositionAsc(playlistId);

        List<Long> videoLikes = playlistVideos.stream()
                .map(video -> videoMapper.getVideoLikeCount(video.getVideoId()))
                .toList();

        return videoMapper.toDtoWithLikes(playlistVideos, videoLikes);
    }

    // 영상의 playlist가 존재하는지 조회하는 로직
    public PlaylistIdResDto getPlaylistId(String videoId) {
        Video video = videoRepository.findByVideoId(videoId);
        PlaylistIdResDto playlistIdResDto = null;

        if(video.getPlaylistId() != null) {
            playlistIdResDto = playlistMapper.toPlaylistResDto(video.getPlaylistId());
        }

        return playlistIdResDto;
    }

    // 추천 playlist
    public List<PlaylistDto> getRecommendPlaylist(String query, String playlistId) {
        List<Playlist> playlists = playlistRepository.findByPlaylistTitleContaining(query);

        // 선택된 playlist 제외
        List<Playlist> remainingPlaylists = playlists.stream()
                .filter(playlist -> !Objects.equals(playlist.getPlaylistId(), playlistId))
                .toList();

        // 선택된 playlist를 제외한 각 영상의 좋아요 수를 Redis에서 검색하여 정렬
        List<Playlist> sortedPlaylists = remainingPlaylists.stream()
                .sorted((p1, p2) -> {
                    Long likes1 = (Long) redisTemplate.opsForValue().get("playlist:likes:" + p1.getPlaylistId());
                    Long likes2 = (Long) redisTemplate.opsForValue().get("playlist:likes:" + p2.getPlaylistId());
                    likes1 = (likes1 == null) ? 0 : likes1;
                    likes2 = (likes2 == null) ? 0 : likes2;
                    return likes2.compareTo(likes1);
                })
                .limit(12) // 상위 12개 영상 선택
                .toList();

        List<Long> playlistLikes = sortedPlaylists.stream()
                .map(playlist -> playlistMapper.getPlaylistLikeCount(playlist.getPlaylistId()))
                .toList();

        return playlistMapper.toDtoWithLikes(sortedPlaylists, playlistLikes);
    }


    // playlist에 대한 좋아요 상태를 조회하는 로직
    public PlaylistLikeStatusResDto getPlaylistLikeStatus(PlaylistLikeStatusReqDto playlistLikeStatusReqDto) {
        User user = userRepository.findById(playlistLikeStatusReqDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Playlist playlist = playlistRepository.findById(playlistLikeStatusReqDto.getPlaylistId())
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        PlaylistLike playlistLike = null;
        PlaylistLikeStatusResDto playlistLikeStatusResDto;

        if(user != null && playlist != null) {
            playlistLike = playlistLikeRepository.findByPlaylistIdAndUserId(playlist.getPlaylistId(), user.getUserId());
        }

        if(playlistLike != null) {
            playlistLikeStatusResDto = playlistLikeMapper.toDto(playlistLike);
        } else { return null; }

        return playlistLikeStatusResDto;
    }

    // 좋아요 수를 업데이트하는 로직
    public PlaylistLike updatePlaylistLike(UpdatePlaylistLikeStatusReqDto updatePlaylistLikeStatusReqDto) {
        User user = userRepository.findById(updatePlaylistLikeStatusReqDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Playlist playlist = playlistRepository.findById(updatePlaylistLikeStatusReqDto.getPlaylistId())
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        PlaylistLike playlistLike = null;

        String redisKey = null;

        if(user != null && playlist != null) {
            playlistLike = playlistLikeRepository.findByPlaylistIdAndUserId(playlist.getPlaylistId(), user.getUserId());
            redisKey = "playlist:" + playlist.getPlaylistId() + ":likes";
        }

        if(playlistLike == null && updatePlaylistLikeStatusReqDto.getPlaylistLikeStatus() == PlaylistLikeStatus.LIKE) {
            playlistLikeRepository.save(playlistLikeMapper.toEntity(updatePlaylistLikeStatusReqDto));
            redisTemplate.opsForValue().increment(redisKey);
        }

        else if(playlistLike != null && updatePlaylistLikeStatusReqDto.getPlaylistLikeStatus() == PlaylistLikeStatus.UNLIKE) {
            playlistLikeRepository.deleteById(playlistLike.getId());
            redisTemplate.opsForValue().decrement(redisKey);
        }

        return playlistLike;
    }

}
