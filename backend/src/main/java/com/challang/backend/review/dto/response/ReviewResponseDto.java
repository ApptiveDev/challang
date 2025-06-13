package com.challang.backend.review.dto.response;

import com.challang.backend.review.entity.Review;
import java.time.LocalDateTime;

public record ReviewResponseDto(
        Long reviewId,
        Long writerId,
        String writerNickname,
        String content,
        LocalDateTime createdAt
) {
    // Entity를 DTO로 변환하는 정적 팩토리 메서드
    public static ReviewResponseDto from(Review review) {
        return new ReviewResponseDto(
                review.getId(),
                review.getWriter().getUserId(),
                review.getWriter().getNickname(),
                review.getContent(),
                review.getCreatedAt()
        );
    }
}