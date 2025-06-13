package com.challang.backend.review.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ReviewUpdateRequestDto(
        @NotBlank(message = "리뷰 내용은 비울 수 없습니다.")
        String content
) {
}