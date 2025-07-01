package com.challang.backend.feedback.entity;

import com.challang.backend.feedback.code.FeedbackCode;
import com.challang.backend.global.exception.BaseException;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "피드백 타입 (GOOD: 좋음, BAD: 나쁨)")
public enum FeedbackType {
    GOOD(0, "Good"),
    BAD(1, "Bad");

    private final int code;
    private final String label;

    FeedbackType(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public String getLabel() {
        return label;
    }

    public static FeedbackType fromCode(int code) {
        for (FeedbackType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new BaseException(FeedbackCode.INVALID_FEEDBACK_TYPE);
    }
}
