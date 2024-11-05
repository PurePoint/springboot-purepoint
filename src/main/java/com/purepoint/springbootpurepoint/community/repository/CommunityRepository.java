package com.purepoint.springbootpurepoint.community.repository;

import com.purepoint.springbootpurepoint.community.domain.Community;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;


public interface CommunityRepository extends JpaRepository<Community, UUID> {

    Optional<Community> findByIdOrderByPostAtDesc(UUID boardId, Sort sort);

    Optional<Community> findByIdOrderByPostViewDesc(UUID boardId, Sort sort);
}
