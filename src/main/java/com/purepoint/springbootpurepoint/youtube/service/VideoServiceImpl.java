package com.purepoint.springbootpurepoint.youtube.service;

import com.purepoint.springbootpurepoint.youtube.domain.Video;
import com.purepoint.springbootpurepoint.user.domain.User;
import com.purepoint.springbootpurepoint.user.repository.UserRepository;
import com.purepoint.springbootpurepoint.youtube.domain.VideoLike;
import com.purepoint.springbootpurepoint.youtube.dto.VideoDto;
import com.purepoint.springbootpurepoint.youtube.dto.VideoLikeStatus;
import com.purepoint.springbootpurepoint.youtube.dto.VideoLikeStatusReqDto;
import com.purepoint.springbootpurepoint.youtube.mapper.VideoLikeMapper;
import com.purepoint.springbootpurepoint.youtube.mapper.VideoMapper;
import com.purepoint.springbootpurepoint.youtube.repository.VideoLikeRepository;
import com.purepoint.springbootpurepoint.youtube.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;
    private final UserRepository userRepository;
    private final VideoLikeRepository videoLikeRepository;
    private final VideoLikeMapper videoLikeMapper = VideoLikeMapper.INSTANCE;
    private final RedisTemplate<String, String> redisTemplate;

    // 유튜브 영상 리스트 가져오는 로직
    public List<VideoDto> getYoutubeVideo() {
        List<Video> videos = videoRepository.findAll();
        List<Long> videoLikes = videos.stream()
                .map(video -> videoMapper.getVideoLikeCount(video.getVideoId()))
                .collect(Collectors.toList());

        return videoMapper.toDtoWithLikes(videos, videoLikes);
    }


    public VideoLike updateVideoLike(VideoLikeStatusReqDto videoLikeStatusReqDto) {
        User user = userRepository.findById(videoLikeStatusReqDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Video video = videoRepository.findById(videoLikeStatusReqDto.getVideoId())
                .orElseThrow(() -> new RuntimeException("Video not found"));

        VideoLike videoLike = null;

        String redisKey = null;

        // Redis에 videoLike가 있는지 검증
        if(user != null && video != null) {
            videoLike = videoLikeRepository.findByVideoIdAndUserId(video.getVideoId(), user.getUserId());
            redisKey = "video:" + video.getVideoId() + ":likes";
        }

        // videoLike가 없고, 좋아요 상태가 true 요청이 오면 videoLike 저장
        if(videoLike == null && videoLikeStatusReqDto.getVideoLikeStatus() == VideoLikeStatus.LIKE) {
            videoLikeRepository.save(videoLikeMapper.toEntity(videoLikeStatusReqDto));
            redisTemplate.opsForValue().increment(redisKey);
        }

        // videoLike가 있고, 좋아요 상태가 false 요청이 오면 videoLike 삭제
        else if(videoLike != null && videoLikeStatusReqDto.getVideoLikeStatus() == VideoLikeStatus.UNLIKE) {
            videoLikeRepository.deleteById(videoLike.getId());
            redisTemplate.opsForValue().decrement(redisKey);
        }

        return videoLike;
    }

}
