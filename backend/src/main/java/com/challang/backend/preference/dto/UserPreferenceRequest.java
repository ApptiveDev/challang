package com.challang.backend.preference.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public record UserPreferenceRequest(

        @NotNull(message = "선호 주종은 필수입니다.")
        @Size(max = 3, message = "선호 주종은 최대 3개까지 선택할 수 있습니다.")
        List<Long> typeIds,

        @NotNull(message = "도수 태그는 필수입니다.")
        Long levelId,

        @NotNull(message = "스타일 태그는 필수입니다.")
        @Size(min = 5, message = "스타일 태그는 최소 5개 이상 선택해야 합니다.")
        List<Long> tagIds
) {

}