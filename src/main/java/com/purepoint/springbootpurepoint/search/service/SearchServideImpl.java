package com.purepoint.springbootpurepoint.search.service;

import com.purepoint.springbootpurepoint.playlist.dto.PlaylistDto;
import com.purepoint.springbootpurepoint.playlist.service.PlaylistService;
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
    private final PlaylistService playlistService;

    @Override
    public List<VideoDto> searchVideo(String query) {
        return videoService.searchYoutubeVideo(query);
    }

    @Override
    public List<PlaylistDto> searchPlaylist(String query) {
        return playlistService.searchYoutubePlaylist(query);
    }


}

