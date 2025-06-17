package com.challang.backend.user.exception;

import com.challang.backend.util.response.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum UserErrorCode implements ResponseStatus {

    // 사용자 관련 예외
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, false, 409, "이미 존재하는 사용자입니다."),
    USER_NICKNAME_ALREADY_EXISTS(HttpStatus.CONFLICT, false, 409, "이미 사용 중인 닉네임입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, false, 404, "해당 사용자를 찾을 수 없습니다."),
    UNDERAGE_SIGNUP_NOT_ALLOWED(HttpStatus.FORBIDDEN, false, 403, "만 19세 미만은 가입할 수 없습니다.");

    private final HttpStatusCode httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private final String message;
}