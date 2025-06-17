package com.challang.backend.tag.dto.request;

import jakarta.validation.constraints.NotNull;

public record LiquorTagRequest(
        @NotNull(message = "태그 ID는 필수입니다.")
        Long id,

        @NotNull(message = "핵심 코어여부는 필수입니다.")
        boolean isCore
) {
}
