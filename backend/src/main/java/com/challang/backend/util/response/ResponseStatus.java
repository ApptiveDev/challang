package com.challang.backend.util.response;

import org.springframework.http.HttpStatusCode;

public interface ResponseStatus {
    HttpStatusCode getHttpStatusCode();

    boolean isSuccess();

    int getCode();

    String getMessage();
}