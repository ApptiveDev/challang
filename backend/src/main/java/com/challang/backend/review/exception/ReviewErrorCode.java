package com.challang.backend.review.exception;

import com.challang.backend.util.response.ResponseStatus;
import lombok.*;
import org.springframework.http.*;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements ResponseStatus {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, false, 404, "해당 리뷰를 찾을 수 없습니다."),
    NO_AUTHORITY_FOR_REVIEW(HttpStatus.FORBIDDEN, false, 403, "해당 리뷰에 대한 수정/삭제 권한이 없습니다.");

    private final HttpStatusCode httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private final String message;

}