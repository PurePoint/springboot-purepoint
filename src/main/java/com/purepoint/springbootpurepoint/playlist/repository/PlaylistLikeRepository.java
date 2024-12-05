package com.purepoint.springbootpurepoint.playlist.repository;

import com.purepoint.springbootpurepoint.playlist.domain.PlaylistLike;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PlaylistLikeRepository extends CrudRepository<PlaylistLike,String> {

    PlaylistLike findByPlaylistIdAndUserId(String playlistId, UUID userId);
}
