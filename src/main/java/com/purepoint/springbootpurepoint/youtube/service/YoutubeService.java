package com.purepoint.springbootpurepoint.youtube.service;

import com.purepoint.springbootpurepoint.youtube.dto.YoutubeDto;

import java.util.List;

public interface YoutubeService {
    /**
     * @return 유튜브 영상 데이터 리턴
     */
    List<YoutubeDto> getYoutubeVideo();
}
