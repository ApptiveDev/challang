package com.challang.backend.tag.code;

import com.challang.backend.util.response.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum TagCode implements ResponseStatus {

    // 태그 관련
    TAG_DELETE_SUCCESS(HttpStatus.OK, true, 200, "태그가 성공적으로 삭제되었습니다."),

    TAG_NOT_FOUND(HttpStatus.NOT_FOUND, false, 404, "해당 태그를 찾을 수 없습니다."),
    TAG_ALREADY_EXISTS(HttpStatus.CONFLICT, false, 409, "이미 존재하는 태그입니다."),

    // 주류 태그 관련
    LIQUOR_TAG_DELETE_SUCCESS(HttpStatus.OK, true, 200, "주류 태그가 성공적으로 삭제되었습니다."),

    INVALID_CORE_TAG_COUNT(HttpStatus.BAD_REQUEST, false, 400, "핵심 태그는 2개 이상, 4개 이하여야 합니다."),
    LIQUOR_TAG_NOT_FOUND(HttpStatus.NOT_FOUND, false, 404, "해당 주류 태그를 찾을 수 없습니다."),
    LIQUOR_TAG_MISMATCH(HttpStatus.BAD_REQUEST, false, 400, "주류와 해당 태그의 정보가 일치하지 않습니다.");

    private final HttpStatusCode httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private final String message;
}
