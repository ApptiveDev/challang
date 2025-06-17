package com.challang.backend.review.dto.request;

import jakarta.validation.constraints.NotBlank;

// record를 사용해 불변 객체로 간단히 정의
public record ReviewCreateRequestDto(
        @NotBlank(message = "리뷰 내용은 필수 입력값입니다.")
        String content
) {
}