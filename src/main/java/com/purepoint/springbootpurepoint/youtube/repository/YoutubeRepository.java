package com.purepoint.springbootpurepoint.youtube.repository;

import com.purepoint.springbootpurepoint.youtube.domain.Youtube;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YoutubeRepository extends JpaRepository<Youtube, String> {

}
