package com.challang.backend.review.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ReviewCreateRequestDto(
        @NotBlank(message = "리뷰 내용은 필수 입력값입니다.")
        String content,

        @NotBlank(message = "리뷰 이미지는 필수입니다.")
        String imageUrl
) {
}