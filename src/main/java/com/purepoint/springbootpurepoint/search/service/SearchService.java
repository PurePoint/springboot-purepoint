package com.purepoint.springbootpurepoint.search.service;

import com.purepoint.springbootpurepoint.playlist.dto.PlaylistDto;
import com.purepoint.springbootpurepoint.video.dto.VideoDto;

import java.util.List;

public interface SearchService {

    // 해시태그 검색
        // 비디오에서 키워드 목록 출력 (자바) x2
        // En = java

    List<VideoDto> searchVideo(String query);

    List<PlaylistDto> searchPlaylist(String query);

}
