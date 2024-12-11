package com.purepoint.springbootpurepoint.review.mapper;

import com.purepoint.springbootpurepoint.review.domain.Review;
import com.purepoint.springbootpurepoint.review.dto.CreateReviewDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

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
    Review toEntity(CreateReviewDto createReviewDto);
}
