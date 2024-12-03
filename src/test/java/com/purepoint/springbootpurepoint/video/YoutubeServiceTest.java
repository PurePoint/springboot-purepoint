package com.purepoint.springbootpurepoint.video;

import com.purepoint.springbootpurepoint.video.dto.VideoLikeStatusReqDto;
import com.purepoint.springbootpurepoint.video.dto.VideoLikeStatusResDto;
import com.purepoint.springbootpurepoint.video.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
@Slf4j
public class YoutubeServiceTest {

    @Autowired
    private VideoService videoService;

    @Test
    @DisplayName("유튜브 영상 데이터 조회 서비스 테스트")
    public void getYoutubeVideoTest() {
    }

    @Test
    @DisplayName("좋아요 수 업데이트 테스트")
    public void getYoutubeVideoLikesTest() {
    }

    @Test
    @DisplayName("좋아요 상태 조회 테스트")
    public void getYoutubeLikeStatusTest() {
        VideoLikeStatusReqDto videoLikeStatusReqDto = VideoLikeStatusReqDto.builder()
                .videoId("-HpnldGdgbY")
                .userId(UUID.fromString("343a7d2a-d340-4e36-a8cb-c3785131a2cf"))
                .build();
        VideoLikeStatusResDto videoLikeStatusResDto = videoService.getVideoLikeStatus(videoLikeStatusReqDto);

        log.info("getVideoLikeStatus: {}", videoLikeStatusResDto.getVideoLikeStatus());
    }
}
