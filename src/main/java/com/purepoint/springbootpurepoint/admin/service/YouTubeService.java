package com.purepoint.springbootpurepoint.admin.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Service
public class YouTubeService {

    // 시크릿 키
    @Value("${youtube.api.key}")
    private String apiKey;

    private static final String APPLICATION_NAME = "YouTube Data API";
    private static final Long MAX_RESULTS = 10L;


    // YouTube API 클라이언트 서비스 생성
    private YouTube getService() throws GeneralSecurityException, IOException {
        return new YouTube.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                request -> {})
                .setApplicationName(APPLICATION_NAME)
                .build();
    }


    // YouTube API를 통해 동영상 검색
    public List<String> searchVideos(String query) {
        List<String> videoTitles = new ArrayList<>();

        try {
            YouTube youtubeService = getService();
            YouTube.Search.List search = youtubeService.search().list("id,snippet");
            search.setKey(apiKey);
            search.setQ(query);
            search.setType("video");
            search.setMaxResults(MAX_RESULTS);

            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResults = searchResponse.getItems();

            for (SearchResult result : searchResults) {
                videoTitles.add(result.getSnippet().getTitle());
            }
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }

        return videoTitles;
    }
}
