package com.purepoint.springbootpurepoint.search.controller;

import com.purepoint.springbootpurepoint.search.service.SearchService;
import com.purepoint.springbootpurepoint.video.dto.VideoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/")
    public ResponseEntity<List<VideoDto>> search(@RequestParam("query") String query) {
        return searchService.searchVideo(query) != null ? ResponseEntity.ok(searchService.searchVideo(query)) : ResponseEntity.notFound().build();
    }

}
