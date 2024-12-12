package com.purepoint.springbootpurepoint.review.repository;

import com.purepoint.springbootpurepoint.review.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,String> {

    Page<Review> findAllByVideoId(String videoId, Pageable pageable);
}
