package com.challang.backend.review.dto.response;

import com.challang.backend.review.entity.Review;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record ReviewResponseDto(
        Long reviewId,
        Long writerId,
        String writerNickname,
        String content,
        String imageUrl,
        LocalDateTime createdAt,
        Double rating,
        int likeCount,
        int dislikeCount,
        List<String> hashtags
) {
    public static ReviewResponseDto from(Review review, String s3BaseUrl) {
        String fullImageUrl = s3BaseUrl + "/" + review.getImageUrl();

        List<String> tagNames = review.getReviewTags().stream()
                .map(reviewTag -> reviewTag.getTag().getName())
                .collect(Collectors.toList());

        return new ReviewResponseDto(
                review.getId(),
                review.getWriter().getUserId(),
                review.getWriter().getNickname(),
                review.getContent(),
                fullImageUrl,
                review.getCreatedAt(),
                review.getRating(),
                review.getLikeCount(),
                review.getDislikeCount(),
                tagNames
        );
    }
}