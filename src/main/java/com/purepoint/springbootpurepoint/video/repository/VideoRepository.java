package com.purepoint.springbootpurepoint.video.repository;

import com.purepoint.springbootpurepoint.video.domain.Video;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, String> {
//    @Query("SELECT v FROM Video v WHERE v.videoTitle LIKE %?1%")
//    Video searchVideoByVideoTitle(String query);

    @NonNull
    Page<Video> findAll(@NonNull Pageable pageable);
    Page<Video> findByVideoTitleContaining(String query, Pageable pageable);

    List<Video> findByVideoTitleContaining(String query);

    @Query("SELECT v FROM Video v WHERE v.playlistId = :playlistId ORDER BY v.videoPosition ASC")
    List<Video> findByPlaylistIdOrderByVideoPositionAsc(@Param("playlistId") String playlistId);
}
