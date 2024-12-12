package com.purepoint.springbootpurepoint.review.service;

import com.purepoint.springbootpurepoint.review.domain.Review;
import com.purepoint.springbootpurepoint.review.dto.CreateReviewReqDto;
import com.purepoint.springbootpurepoint.review.dto.GetReviewsPagingResDto;
import com.purepoint.springbootpurepoint.review.dto.GetReviewsResDto;
import com.purepoint.springbootpurepoint.review.mapper.ReviewMapper;
import com.purepoint.springbootpurepoint.review.repository.ReviewRepository;
import com.purepoint.springbootpurepoint.user.domain.User;
import com.purepoint.springbootpurepoint.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final UserRepository userRepository;
    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;

    // 수강평 작성
    @Override
    public Review createReview(CreateReviewReqDto createReviewReqDto) {
        userRepository.findById(createReviewReqDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return reviewRepository.save(reviewMapper.toEntity(createReviewReqDto));
    }

    @Override
    public GetReviewsPagingResDto getReview(String videoId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Review> reviews = reviewRepository.findAllByVideoId(videoId, pageable);

        List<String> nicknames = reviews.stream()
                .map(review -> userRepository.findById(review.getUserId())
                        .map(User::getNickname)
                        .orElse("Unknown"))
                .toList();

        List<GetReviewsResDto> getReviewsResDtos = reviewMapper.toDto(reviews.getContent(), nicknames);

        return reviewMapper.toGetReviewsPagingDto(getReviewsResDtos, reviews);
    }
}
