package com.purepoint.springbootpurepoint.playlist.repository;

import com.purepoint.springbootpurepoint.playlist.domain.Playlist;
import com.purepoint.springbootpurepoint.video.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, String> {

    List<Playlist> findByPlaylistTitleContaining(String query);
}
