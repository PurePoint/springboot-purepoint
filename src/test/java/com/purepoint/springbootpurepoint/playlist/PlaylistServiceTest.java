package com.purepoint.springbootpurepoint.playlist;

import com.purepoint.springbootpurepoint.video.domain.Video;
import com.purepoint.springbootpurepoint.video.repository.VideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class PlaylistServiceTest {

    @Autowired
    private VideoRepository videoRepository;

    @Test
    @DisplayName("영상이 playlist가 존재하는지 조회하는 서비스 테스트")
    public void getPlaylistIdTest() {
        Video video = videoRepository.findByVideoId("-JHAUaOq6l0");

        log.info("playlistId: {}", video.getPlaylistId());
    }
}
