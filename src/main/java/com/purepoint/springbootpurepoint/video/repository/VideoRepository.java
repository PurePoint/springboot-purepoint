package com.purepoint.springbootpurepoint.video.repository;

import com.purepoint.springbootpurepoint.video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, String> {
//    @Query("SELECT v FROM Video v WHERE v.videoTitle LIKE %?1%")
//    Video searchVideoByVideoTitle(String query);

    List<Video> findByVideoTitleContaining(String query);
}
