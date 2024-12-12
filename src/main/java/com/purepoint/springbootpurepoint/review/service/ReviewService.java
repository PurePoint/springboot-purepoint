package com.purepoint.springbootpurepoint.review.service;

import com.purepoint.springbootpurepoint.review.domain.Review;
import com.purepoint.springbootpurepoint.review.dto.CreateReviewReqDto;
import com.purepoint.springbootpurepoint.review.dto.GetReviewsPagingResDto;

public interface ReviewService {
    
    // 수강평 작성
    Review createReview(CreateReviewReqDto createReviewReqDto);
    
    // 영상 수강평 조회
    GetReviewsPagingResDto getReview(String videoId, int page, int size);

    // playlist별 수강평 조회

    // 수강평 수정
    
    // 수강평 삭제
}
