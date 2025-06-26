package com.challang.backend.review.dto.request;

public record ReviewUpdateRequestDto(
        String content,
        String imageUrl
) {
}