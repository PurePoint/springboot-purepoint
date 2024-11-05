package com.purepoint.springbootpurepoint.community.repository;

import com.purepoint.springbootpurepoint.community.domain.Community;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface CommunityRepository extends JpaRepository<Community, UUID> {

    Optional<Community> findByBoardIdOrderByPostAtDesc(UUID boardId, Sort sort);

    Optional<Community> findByBoardIdOrderByPostViewDesc(UUID boardId, Sort sort);
}
