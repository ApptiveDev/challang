package com.challang.backend.review.dto.response;

import com.challang.backend.review.entity.Review;
import java.time.LocalDateTime;

public record ReviewResponseDto(
        Long reviewId,
        Long writerId,
        String writerNickname,
        String content,
        String imageUrl,
        LocalDateTime createdAt
) {
    public static ReviewResponseDto from(Review review, String s3BaseUrl) {
        String fullImageUrl = s3BaseUrl + "/" + review.getImageUrl();
        return new ReviewResponseDto(
                review.getId(),
                review.getWriter().getUserId(),
                review.getWriter().getNickname(),
                review.getContent(),
                fullImageUrl,
                review.getCreatedAt()
        );
    }
}