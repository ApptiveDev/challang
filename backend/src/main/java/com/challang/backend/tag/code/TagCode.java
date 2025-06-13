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
    TAG_ALREADY_EXISTS(HttpStatus.CONFLICT, false, 409, "이미 존재하는 태그입니다.");

    private final HttpStatusCode httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private final String message;
}
