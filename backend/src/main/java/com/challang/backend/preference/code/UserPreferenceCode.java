package com.challang.backend.preference.code;

import com.challang.backend.util.response.ResponseStatus;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum UserPreferenceCode implements ResponseStatus {

    PREFERENCE_SAVE_SUCCESS(HttpStatus.OK, true, 200, "선호 설정이 저장되었습니다.");

    private final HttpStatusCode httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private final String message;
}