package com.purepoint.springbootpurepoint.video.service;

import com.purepoint.springbootpurepoint.video.domain.Video;
import com.purepoint.springbootpurepoint.user.domain.User;
import com.purepoint.springbootpurepoint.user.repository.UserRepository;
import com.purepoint.springbootpurepoint.video.domain.VideoLike;
import com.purepoint.springbootpurepoint.video.dto.VideoDto;
import com.purepoint.springbootpurepoint.video.dto.VideoLikeStatus;
import com.purepoint.springbootpurepoint.video.dto.VideoLikeStatusReqDto;
import com.purepoint.springbootpurepoint.video.mapper.VideoLikeMapper;
import com.purepoint.springbootpurepoint.video.mapper.VideoMapper;
import com.purepoint.springbootpurepoint.video.repository.VideoLikeRepository;
import com.purepoint.springbootpurepoint.video.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
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

    // 유튜브 영상 리스트 가져오는 로직
    public List<VideoDto> getYoutubeVideo(String category) {
        List<Video> videos;

        if(category == null || category.equals("all")) { videos = videoRepository.findAll(); }
        else {videos = videoRepository.findByVideoTitleContaining(category); }

        List<Long> videoLikes = videos.stream()
                .map(video -> (long) videoLikeRepository.findAllByVideoId(video.getVideoId()).size())
                .collect(Collectors.toList());

        return videoMapper.toDtoWithLikes(videos, videoLikes);
    }

    @Override
    public List<VideoDto> searchYoutubeVideo(String query) {
        List<Video> videos = videoRepository.findByVideoTitleContaining(query);


//        return videos.stream()
//                .map(video -> videoMapper.toDto(video))
//                .collect(Collectors.toList());

        return videos.stream()
                .map(videoMapper::toDto)
                .collect(Collectors.toList());
    }


    public VideoLike updateVideoLike(VideoLikeStatusReqDto videoLikeStatusReqDto) {
        User user = userRepository.findById(videoLikeStatusReqDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Video video = videoRepository.findById(videoLikeStatusReqDto.getVideoId())
                .orElseThrow(() -> new RuntimeException("Video not found"));

        VideoLike videoLike = null;

        // Redis에 videoLike가 있는지 검증
        if(user != null && video != null) {
            videoLike = videoLikeRepository.findByVideoIdAndUserId(video.getVideoId(), user.getUserId());
        }

        // videoLike가 없고, 좋아요 상태가 true 요청이 오면 videoLike 저장
        if(videoLike == null && videoLikeStatusReqDto.getVideoLikeStatus() == VideoLikeStatus.LIKE) {
            videoLikeRepository.save(videoLikeMapper.toEntity(videoLikeStatusReqDto));
        }

        // videoLike가 있고, 좋아요 상태가 false 요청이 오면 videoLike 삭제
        else if(videoLike != null && videoLikeStatusReqDto.getVideoLikeStatus() == VideoLikeStatus.UNLIKE) {
            videoLikeRepository.deleteById(videoLike.getId());
        }

        return null;
    }

}
