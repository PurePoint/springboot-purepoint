package com.purepoint.springbootpurepoint.review.controller;

import com.purepoint.springbootpurepoint.review.domain.Review;
import com.purepoint.springbootpurepoint.review.dto.CreateReviewReqDto;
import com.purepoint.springbootpurepoint.review.dto.GetReviewsPagingResDto;
import com.purepoint.springbootpurepoint.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@Tag(name = "수강평 서비스 API", description = "수강평 관리를 위한 API를 제공합니다.")
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(summary = "수강평을 작성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 수강평 작성")})
    @PostMapping("/create-review")
    public ResponseEntity<Review> createReview(@RequestBody CreateReviewReqDto createReviewReqDto) {
        return ResponseEntity.ok(reviewService.createReview(createReviewReqDto));
    }

    @Operation(summary = "수강평을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 수강평 조회")})
    @GetMapping("/reviews")
    public ResponseEntity<GetReviewsPagingResDto> getReview(@RequestParam String videoId, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(reviewService.getReview(videoId, page, size));
    }
}
