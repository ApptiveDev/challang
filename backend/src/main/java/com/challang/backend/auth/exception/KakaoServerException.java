package com.challang.backend.auth.exception;

public class KakaoServerException extends RuntimeException {
    public KakaoServerException(String message) {
        super(message);
    }
}