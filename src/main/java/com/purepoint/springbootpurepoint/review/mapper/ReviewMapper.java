package com.purepoint.springbootpurepoint.review.mapper;

import com.purepoint.springbootpurepoint.review.domain.Review;
import com.purepoint.springbootpurepoint.review.dto.CreateReviewReqDto;
import com.purepoint.springbootpurepoint.review.dto.GetReviewsPagingResDto;
import com.purepoint.springbootpurepoint.review.dto.GetReviewsResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mappings({
            @Mapping(target = "reviewId", ignore = true),
            @Mapping(source = "videoId", target = "videoId"),
            @Mapping(source = "reviewContent", target = "reviewContent"),
            @Mapping(source = "reviewRating", target = "reviewRating"),
            @Mapping(target = "reviewAt", ignore = true),
            @Mapping(target = "reviewUpdateAt", ignore = true),
            @Mapping(target = "reviewDeleteAt", ignore = true),
            @Mapping(source = "userId", target = "userId")
    })
    Review toEntity(CreateReviewReqDto createReviewReqDto);

    @Mappings({
            @Mapping(source = "review.reviewContent", target = "reviewContent"),
            @Mapping(source = "review.reviewRating", target = "reviewRating"),
            @Mapping(source = "review.reviewAt", target = "reviewAt"),
            @Mapping(source = "review.reviewUpdateAt", target = "reviewUpdateAt")
    })
    GetReviewsResDto toDto(Review review, String nickname);

    default List<GetReviewsResDto> toDto(List<Review> reviews, List<String> nicknames) {
        if(reviews == null || nicknames == null || reviews.size() != nicknames.size()) {
            throw new IllegalArgumentException("Reviews and nicknames must have same size.");
        }
        List<GetReviewsResDto> result = new ArrayList<>();
        for (int i = 0; i < reviews.size(); i++) {
            result.add(toDto(reviews.get(i), nicknames.get(i)));
        }
        return result;
    }

    default GetReviewsPagingResDto toGetReviewsPagingDto(List<GetReviewsResDto> getReviewsResDtos, Page<Review> reviews) {
        return GetReviewsPagingResDto.builder()
                .content(getReviewsResDtos)
                .totalElements(reviews.getTotalElements())
                .currentPage(reviews.getNumber())
                .pageSize(reviews.getSize())
                .totalPages(reviews.getTotalPages())
                .build();
    }
}
