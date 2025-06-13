package com.challang.backend.liquor.code;

import com.challang.backend.util.response.ResponseStatus;
import lombok.*;
import org.springframework.http.*;


@Getter
@AllArgsConstructor
public enum LiquorCode implements ResponseStatus {

    // 주종 관련
    LIQUOR_TYPE_DELETE_SUCCESS(HttpStatus.OK, true, 200, "주종이 성공적으로 삭제되었습니다."),

    LIQUOR_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, false, 404, "해당 주종을 찾을 수 없습니다."),
    LIQUOR_TYPE_ALREADY_EXISTS(HttpStatus.CONFLICT, false, 409, "이미 존재하는 주종입니다."),

    // 도수 관련
    LIQUOR_LEVEL_DELETE_SUCCESS(HttpStatus.OK, true, 200, "도수 등급이 성공적으로 삭제되었습니다."),

    LIQUOR_LEVEL_NOT_FOUND(HttpStatus.NOT_FOUND, false, 404, "해당 도수 등급을 찾을 수 없습니다."),
    LIQUOR_LEVEL_ALREADY_EXISTS(HttpStatus.CONFLICT, false, 409, "이미 존재하는 도수 등급입니다.");


    private final HttpStatusCode httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private final String message;
}
