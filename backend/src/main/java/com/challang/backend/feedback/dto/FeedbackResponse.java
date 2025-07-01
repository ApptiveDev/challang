package com.challang.backend.feedback.dto;

import com.challang.backend.feedback.entity.FeedbackType;

public record FeedbackResponse(
        Long liquorId,
        FeedbackType type
) {
}
