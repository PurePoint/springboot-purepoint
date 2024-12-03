package com.purepoint.springbootpurepoint.search.controller;

import com.purepoint.springbootpurepoint.playlist.dto.PlaylistDto;
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

    @GetMapping("/videos")
    public ResponseEntity<List<VideoDto>> searchVideo(@RequestParam("query") String query) {
        return searchService.searchVideo(query) != null ? ResponseEntity.ok(searchService.searchVideo(query)) : ResponseEntity.notFound().build();
    }

    @GetMapping("/playlists")
    public ResponseEntity<List<PlaylistDto>> search(@RequestParam("query") String query) {
        return searchService.searchPlaylist(query) != null ? ResponseEntity.ok(searchService.searchPlaylist(query)) : ResponseEntity.notFound().build();
    }

}
