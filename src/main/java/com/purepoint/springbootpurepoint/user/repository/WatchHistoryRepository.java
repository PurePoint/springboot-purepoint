package com.purepoint.springbootpurepoint.user.repository;

import com.purepoint.springbootpurepoint.user.domain.WatchHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WatchHistoryRepository extends JpaRepository<WatchHistory, Long> {
    List<WatchHistory> findAllByUserId(UUID userId);
}
