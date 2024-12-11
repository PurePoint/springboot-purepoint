package com.purepoint.springbootpurepoint.review.repository;

import com.purepoint.springbootpurepoint.review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,String> {
}
