package com.challang.backend.feedback.dto;

import com.challang.backend.feedback.entity.FeedbackType;
import jakarta.validation.constraints.*;

public record FeedbackRequest(
        @NotNull(message = "liquorId는 필수입니다.")
        Long liquorId,

        @NotNull(message = "피드백 타입은 필수입니다.")
        FeedbackType type) {
}
