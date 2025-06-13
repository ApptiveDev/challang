package com.challang.backend.review.dto.request;

import jakarta.validation.constraints.NotBlank;

// record를 사용하면 간결하게 DTO를 정의할 수 있습니다.
public record ReportRequestDto(
        @NotBlank(message = "신고 사유는 필수 입력값입니다.")
        String reason
) {
}