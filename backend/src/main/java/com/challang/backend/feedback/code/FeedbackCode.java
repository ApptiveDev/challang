package com.challang.backend.feedback.code;

import com.challang.backend.util.response.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum FeedbackCode implements ResponseStatus {

    FEEDBACK_SAVED(HttpStatus.OK, true, 200, "피드백 저장에 성공했습니다."),
    FEEDBACK_NOT_FOUND(HttpStatus.NOT_FOUND, false, 404, "피드백을 찾을 수 없습니다."),

    INVALID_FEEDBACK_TYPE(HttpStatus.BAD_REQUEST, false, 400, "유효하지 않은 피드백 타입입니다.");

    private final HttpStatusCode httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private final String message;
}
