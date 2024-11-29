package com.purepoint.springbootpurepoint.search.service;

import com.purepoint.springbootpurepoint.video.dto.VideoDto;
import com.purepoint.springbootpurepoint.video.repository.HashTagRepository;
import com.purepoint.springbootpurepoint.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServideImpl implements SearchService{

    private final HashTagRepository hashTagRepository;
    private final VideoService videoService;

    @Override
    public List<VideoDto> searchVideo(String query) {
        return videoService.searchYoutubeVideo(query);
    }
}

