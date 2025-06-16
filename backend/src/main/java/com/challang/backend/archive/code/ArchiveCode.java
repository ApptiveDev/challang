package com.challang.backend.archive.code;

import com.challang.backend.util.response.ResponseStatus;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ArchiveCode implements ResponseStatus {

    ARCHIVE_SUCCESS(HttpStatus.OK, true, 2400, "아카이브 등록에 성공했습니다."),
    UNARCHIVE_SUCCESS(HttpStatus.OK, true, 2405, "아카이브 해제에 성공했습니다."),

    ALREADY_ARCHIVED(HttpStatus.BAD_REQUEST, false, 400, "이미 아카이빙된 술입니다."),
    ARCHIVE_NOT_FOUND(HttpStatus.NOT_FOUND, false, 404, "해당 아카이브 정보를 찾을 수 없습니다.");

    private final HttpStatusCode httpStatusCode;
    private final boolean isSuccess;
    private final int code;
    private final String message;

}
