package com.purepoint.springbootpurepoint.community.repository;

import com.purepoint.springbootpurepoint.community.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {

    List<Comment> findByPostId(UUID postId);
}
