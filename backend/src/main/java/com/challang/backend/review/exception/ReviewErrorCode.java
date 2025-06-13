package com.challang.backend.review.exception;

import com.challang.backend.util.response.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode; // HttpStatus 대신 HttpStatusCode import

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements ResponseStatus {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, false, 404, "해당 리뷰를 찾을 수 없습니다."),
    NO_AUTHORITY_FOR_REVIEW(HttpStatus.FORBIDDEN, false, 403, "해당 리뷰에 대한 수정/삭제 권한이 없습니다."),
    LIQUOR_NOT_FOUND(HttpStatus.NOT_FOUND, false, 404, "리뷰를 작성하려는 주류를 찾을 수 없습니다.");

    private final HttpStatusCode httpStatusCode; // HttpStatus -> HttpStatusCode 로 변경
    private final boolean isSuccess;
    private final int code;
    private final String message;

    // ResponseStatus 인터페이스의 모든 추상 메서드를 올바르게 구현
    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public HttpStatusCode getHttpStatusCode() {
        return this.httpStatusCode;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public boolean isSuccess() {
        return this.isSuccess;
    }
}