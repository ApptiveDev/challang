package com.challang.backend.liquor.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LiquorTypeRequest(
        @NotBlank(message = "주종 이름을 필수 입력값입니다.")
        String name
) {
}
