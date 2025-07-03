package com.challang.backend.preference.exception;

import com.challang.backend.util.response.ResponseStatus;
import lombok.*;
import org.springframework.http.*;

@Getter
@AllArgsConstructor
public enum PreferenceErrorCode implements ResponseStatus {

    // 주종
    PREFERENCE_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, false, 404, "해당 주종을 찾을 수 없습니다."),

    // 도수
    PREFERENCE_LEVEL_NOT_FOUND(HttpStatus.NOT_FOUND, false, 404, "해당 도수를 찾을 수 없습니다."),

    // 태그
    PREFERENCE_TAG_NOT_FOUND(HttpStatus.NOT_FOUND, false, 404, "해당 스타일를 찾을 수 없습니다.");

    private final HttpStatusCode httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private final String message;
}