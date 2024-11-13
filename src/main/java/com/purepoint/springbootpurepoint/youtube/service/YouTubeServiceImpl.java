package com.purepoint.springbootpurepoint.youtube.service;

import com.purepoint.springbootpurepoint.youtube.domain.Youtube;
import com.purepoint.springbootpurepoint.youtube.dto.YoutubeDto;
import com.purepoint.springbootpurepoint.youtube.mapper.YoutubeMapper;
import com.purepoint.springbootpurepoint.youtube.repository.YoutubeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YouTubeServiceImpl implements YoutubeService {

    private final YoutubeRepository youtubeRepository;
    private final YoutubeMapper youtubeMapper = YoutubeMapper.INSTANCE;

    public List<YoutubeDto> getYoutubeVideo() {
        List<Youtube> youtube = youtubeRepository.findAll();
        return youtubeMapper.toDto(youtube);
    }


}
