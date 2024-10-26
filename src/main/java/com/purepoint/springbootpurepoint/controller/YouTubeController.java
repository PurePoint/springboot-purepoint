package com.purepoint.springbootpurepoint.controller;

import com.purepoint.springbootpurepoint.service.YouTubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class YouTubeController {
    @Autowired
    private YouTubeService youTubeService;

    @GetMapping("/spring")
    public List<String> test(){
        return youTubeService.searchVideos("클라우드");
    }
}
