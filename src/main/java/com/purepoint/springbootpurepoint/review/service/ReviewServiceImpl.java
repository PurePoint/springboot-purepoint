package com.purepoint.springbootpurepoint.review.service;

import com.purepoint.springbootpurepoint.review.domain.Review;
import com.purepoint.springbootpurepoint.review.dto.CreateReviewDto;
import com.purepoint.springbootpurepoint.review.mapper.ReviewMapper;
import com.purepoint.springbootpurepoint.review.repository.ReviewRepository;
import com.purepoint.springbootpurepoint.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final UserRepository userRepository;
    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;

    // 수강평 작성
    @Override
    public Review createReview(CreateReviewDto createReviewDto) {
        userRepository.findById(createReviewDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return reviewRepository.save(reviewMapper.toEntity(createReviewDto));
    }
}
