package com.purepoint.springbootpurepoint.review;

import com.purepoint.springbootpurepoint.review.dto.CreateReviewDto;
import com.purepoint.springbootpurepoint.review.repository.ReviewRepository;
import com.purepoint.springbootpurepoint.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest
@Slf4j
@Transactional
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @DisplayName("수강평 작성 서비스 테스트")
    public void createReviewTest() {
        CreateReviewDto createReviewDto = CreateReviewDto.builder()
                .userId(UUID.fromString("ce4bb5a1-5ffe-4137-bdfc-c4959c184a7e"))
                .videoId("-JHAUaOq6l0")
                .reviewRating(4)
                .reviewContent("강의에 대한 수강평입니다.")
                .build();

        reviewService.createReview(createReviewDto);

        log.info("createReview: {}", reviewRepository.findAll());
    }
}
