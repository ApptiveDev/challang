package com.challang.backend.user.exception;

public class UserNickNameAlreadyExistsException extends RuntimeException{
    public UserNickNameAlreadyExistsException(String message) {
        super(message);
    }
}
