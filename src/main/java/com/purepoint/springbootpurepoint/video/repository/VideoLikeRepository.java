package com.purepoint.springbootpurepoint.video.repository;

import com.purepoint.springbootpurepoint.video.domain.VideoLike;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface VideoLikeRepository extends CrudRepository<VideoLike,String> {

    VideoLike findByVideoIdAndUserId(String videoId, UUID userId);

    List<VideoLike> findAllByVideoId(String videoId);
}
