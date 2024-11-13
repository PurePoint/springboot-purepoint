package com.purepoint.springbootpurepoint.youtube;

import com.purepoint.springbootpurepoint.youtube.dto.YoutubeDto;
import com.purepoint.springbootpurepoint.youtube.service.YouTubeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class YoutubeServiceTest {

    @Autowired
    private YouTubeServiceImpl youtubeService;

    @Test
    @DisplayName("유튜브 영상 데이터 조회 서비스 테스트")
    public void getYoutubeVideoTest() {

        List<YoutubeDto> results = youtubeService.getYoutubeVideo();

        for(int i=0; i<results.size(); i++){
            log.info("유튜브 영상 ID: " + results.get(i).getVideoId());
            log.info("유튜브 영상 제목: " + results.get(i).getVideoTitle());
            log.info("유튜브 영상 설명: " + results.get(i).getVideoDescription());
            log.info("유튜브 영상 게시시간: " + results.get(i).getVideoPublishedAt());
            log.info("유튜브 영상 썸네일: " + results.get(i).getVideoThumbnail());
        }
    }
}
