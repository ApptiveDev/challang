package com.challang.backend.liquor.code;

import com.challang.backend.util.response.ResponseStatus;
import lombok.*;
import org.springframework.http.*;


@Getter
@AllArgsConstructor
public enum LiquorCode implements ResponseStatus {

    // 주류 관련
    LIQUOR_DELETE_SUCCESS(HttpStatus.OK, true, 200, "주류 삭제에 성공했습니다."),

    LIQUOR_NOT_FOUND(HttpStatus.NOT_FOUND, false,404,"주류 정보를 찾을 수 없습니다."),
    LIQUOR_ALREADY_EXISTS(HttpStatus.CONFLICT, false,409,"이미 등록된 주류입니다."),

    // 주종 관련
    LIQUOR_TYPE_DELETE_SUCCESS(HttpStatus.OK, true,200,"주종이 성공적으로 삭제되었습니다."),

    LIQUOR_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, false,404,"해당 주종을 찾을 수 없습니다."),
    LIQUOR_TYPE_ALREADY_EXISTS(HttpStatus.CONFLICT, false,409,"이미 존재하는 주종입니다."),

    // 도수 관련
    LIQUOR_LEVEL_DELETE_SUCCESS(HttpStatus.OK, true,200,"도수 등급이 성공적으로 삭제되었습니다."),

    INVALID_ABV_RANGE(HttpStatus.BAD_REQUEST, false, 400, "최소 도수는 최대 도수보다 작아야 합니다."),
    INVALID_ABV_VALUE(HttpStatus.BAD_REQUEST, false, 400, "도수는 0 이상 100 이하의 값이어야 합니다."),

    LIQUOR_LEVEL_NOT_FOUND(HttpStatus.NOT_FOUND, false,404,"해당 도수 등급을 찾을 수 없습니다."),
    LIQUOR_LEVEL_ALREADY_EXISTS(HttpStatus.CONFLICT, false,409,"이미 존재하는 도수 등급입니다.");


    private final HttpStatusCode httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private final String message;
}
