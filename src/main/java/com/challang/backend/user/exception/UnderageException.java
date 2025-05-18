package com.challang.backend.user.exception;

public class UnderageException extends RuntimeException {
    public UnderageException(int requiredAge) {
        super("만 " + requiredAge + "세 미만은 가입할 수 없습니다.");
    }
}
