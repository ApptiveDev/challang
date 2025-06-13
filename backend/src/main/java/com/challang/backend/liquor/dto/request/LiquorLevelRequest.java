package com.challang.backend.liquor.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LiquorLevelRequest(
        @NotBlank(message = "도수 등급 이름은 필수 입력값입니다.")
        String name
) {
}
