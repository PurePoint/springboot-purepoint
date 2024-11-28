package com.purepoint.springbootpurepoint.community.repository;

import com.purepoint.springbootpurepoint.community.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;


public interface CommunityRepository extends JpaRepository<Community, UUID> {

    @Query("SELECT c FROM Community c " +
            "LEFT JOIN FETCH c.comments cm " +
            "LEFT JOIN FETCH cm.user u " +
            "WHERE c.postId = :postId")
    Community findWithCommentsAndUserByPostId(@Param("postId") UUID postId);
}
