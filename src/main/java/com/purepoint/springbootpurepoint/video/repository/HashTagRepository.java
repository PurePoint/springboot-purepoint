package com.purepoint.springbootpurepoint.video.repository;

import com.purepoint.springbootpurepoint.video.domain.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {
    HashTag findHashTagsByHashTagKo(String hashTagKo);
    HashTag findHashTagsByHashTagEn(String hashTagEn);
}
