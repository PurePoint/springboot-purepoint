package com.purepoint.springbootpurepoint.video.service;

import com.purepoint.springbootpurepoint.video.domain.Video;
import com.purepoint.springbootpurepoint.user.domain.User;
import com.purepoint.springbootpurepoint.user.repository.UserRepository;
import com.purepoint.springbootpurepoint.video.domain.VideoLike;
import com.purepoint.springbootpurepoint.video.dto.*;
import com.purepoint.springbootpurepoint.video.mapper.VideoLikeMapper;
import com.purepoint.springbootpurepoint.video.mapper.VideoMapper;
import com.purepoint.springbootpurepoint.video.repository.VideoLikeRepository;
import com.purepoint.springbootpurepoint.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;
    private final UserRepository userRepository;
    private final VideoLikeRepository videoLikeRepository;
    private final VideoLikeMapper videoLikeMapper = VideoLikeMapper.INSTANCE;
    private final RedisTemplate<String, Object> redisTemplate;

    // 유튜브 영상 리스트 가져오는 로직
    public VideoPagingDto getYoutubeVideo(String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Video> videos;
        if (category == null || category.equalsIgnoreCase("all")) {
            videos = videoRepository.findAll(pageable);
        } else {
            videos = videoRepository.findByVideoTitleContaining(category, pageable);
        }

        // Video -> VideoDto 변환
        List<VideoDto> videoDtos = videos.stream()
                .map(video -> {
                    Long videoLikes = videoMapper.getVideoLikeCount(video.getVideoId());
                    return videoMapper.toDtoWithLikes(video, videoLikes);
                })
                .collect(Collectors.toList());

        // 페이지 정보와 함께 반환
        return videoMapper.toVideoPagingDto(videoDtos, videos);
    }

    // 유튜브 영상을 검색하는 로직
    @Override
    public List<VideoDto> searchYoutubeVideo(String query) {
        List<Video> videos = videoRepository.findByVideoTitleContaining(query);

        // 각 영상의 좋아요 수를 Redis에서 검색하여 정렬
        List<Video> sortedVideos = videos.stream()
                .sorted((v1, v2) -> {
                    Long likes1 = (Long) redisTemplate.opsForValue().get("video:likes:" + v1.getVideoId());
                    Long likes2 = (Long) redisTemplate.opsForValue().get("video:likes:" + v2.getVideoId());
                    likes1 = (likes1 == null) ? 0 : likes1;
                    likes2 = (likes2 == null) ? 0 : likes2;
                    return likes2.compareTo(likes1);
                })
                .limit(5) // 상위 5개 영상 선택
                .toList();


//        return videos.stream()
//                .map(video -> videoMapper.toDto(video))
//                .collect(Collectors.toList());

        return sortedVideos.stream()
                .map(videoMapper::toDto)
                .collect(Collectors.toList());
    }

    // 추천 영상
    public List<VideoDto> getRecommendVideo(String query, String playlistId) {
        List<Video> videos = videoRepository.findByVideoTitleContaining(query);

        // 선택된 playlist의 영상 제외
        List<Video> remainingVideos = videos.stream()
                .filter(video -> !Objects.equals(video.getPlaylistId(), playlistId))
                .toList();

        // 선택된 영상을 제외한 각 영상의 좋아요 수를 Redis에서 검색하여 정렬
        List<Video> sortedVideos = remainingVideos.stream()
                .sorted((v1, v2) -> {
                    Long likes1 = (Long) redisTemplate.opsForValue().get("video:likes:" + v1.getVideoId());
                    Long likes2 = (Long) redisTemplate.opsForValue().get("video:likes:" + v2.getVideoId());
                    likes1 = (likes1 == null) ? 0 : likes1;
                    likes2 = (likes2 == null) ? 0 : likes2;
                    return likes2.compareTo(likes1);
                })
                .limit(12) // 상위 12개 영상 선택
                .toList();

        return sortedVideos.stream()
                .map(video -> {
                    Long videoLikes = videoMapper.getVideoLikeCount(video.getVideoId());
                    return videoMapper.toDtoWithLikes(video, videoLikes);
                })
                .collect(Collectors.toList());
    }

    // 영상에 대한 좋아요 상태를 조회하는 로직
    public VideoLikeStatusResDto getVideoLikeStatus(VideoLikeStatusReqDto videoLikeStatusReqDto) {
        User user = userRepository.findById(videoLikeStatusReqDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Video video = videoRepository.findById(videoLikeStatusReqDto.getVideoId())
                .orElseThrow(() -> new RuntimeException("Video not found"));

        VideoLike videoLike = null;
        VideoLikeStatusResDto videoLikeStatusResDto;

        if(user != null && video != null) {
            videoLike = videoLikeRepository.findByVideoIdAndUserId(video.getVideoId(), user.getUserId());
        }

        if(videoLike != null) {
            videoLikeStatusResDto = videoLikeMapper.toDto(videoLike);
        } else {
            return null;
        }

        return videoLikeStatusResDto;
    }

    // 좋아요 수를 업데이트하는 로직
    public VideoLike updateVideoLike(UpdateVideoLikeStatusReqDto updateVideoLikeStatusReqDto) {
        User user = userRepository.findById(updateVideoLikeStatusReqDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Video video = videoRepository.findById(updateVideoLikeStatusReqDto.getVideoId())
                .orElseThrow(() -> new RuntimeException("Video not found"));

        VideoLike videoLike = null;

        String redisKey = null;

        // Redis에 videoLike가 있는지 검증
        if(user != null && video != null) {
            videoLike = videoLikeRepository.findByVideoIdAndUserId(video.getVideoId(), user.getUserId());
            redisKey = "video:" + video.getVideoId() + ":likes";
        }

        // videoLike가 없고, 좋아요 상태가 true 요청이 오면 videoLike 저장
        if(videoLike == null && updateVideoLikeStatusReqDto.getVideoLikeStatus() == VideoLikeStatus.LIKE) {
            videoLikeRepository.save(videoLikeMapper.toEntity(updateVideoLikeStatusReqDto));
            redisTemplate.opsForValue().increment(redisKey);
        }

        // videoLike가 있고, 좋아요 상태가 false 요청이 오면 videoLike 삭제
        else if(videoLike != null && updateVideoLikeStatusReqDto.getVideoLikeStatus() == VideoLikeStatus.UNLIKE) {
            videoLikeRepository.deleteById(videoLike.getId());
            redisTemplate.opsForValue().decrement(redisKey);
        }

        return videoLike;
    }

}
