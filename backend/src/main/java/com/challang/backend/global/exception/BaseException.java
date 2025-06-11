package com.challang.backend.global.exception;

import com.challang.backend.util.response.ResponseStatus;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private final ResponseStatus status;

    public BaseException(ResponseStatus status) {
        super(status.getMessage());
        this.status = status;
    }

    public BaseException(ResponseStatus status, String detailMessage) {
        super(detailMessage);
        this.status = status;
    }
}
