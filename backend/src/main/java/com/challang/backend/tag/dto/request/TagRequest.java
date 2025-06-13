package com.challang.backend.tag.dto.request;

import jakarta.validation.constraints.NotBlank;

public record TagRequest(
        @NotBlank(message = "태그 이름은 필수 입력값입니다.")
        String name
) {
}
