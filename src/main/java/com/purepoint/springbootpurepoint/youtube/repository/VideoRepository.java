package com.purepoint.springbootpurepoint.youtube.repository;

import com.purepoint.springbootpurepoint.youtube.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, String> {
}
