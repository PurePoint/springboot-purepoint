package com.purepoint.springbootpurepoint.review.service;

import com.purepoint.springbootpurepoint.review.domain.Review;
import com.purepoint.springbootpurepoint.review.dto.CreateReviewDto;

public interface ReviewService {
    
    // 수강평 작성
    Review createReview(CreateReviewDto createReviewDto);
    
    // 영상별 수강평 조회
    
    // playlist별 수강평 조회
    
    // 수강평 수정
    
    // 수강평 삭제
}
