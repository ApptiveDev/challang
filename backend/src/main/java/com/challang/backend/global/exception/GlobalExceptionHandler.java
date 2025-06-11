package com.challang.backend.global.exception;


import com.challang.backend.util.response.BaseResponse;
import org.springframework.http.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {


    // dto 검증 실패 시
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<ErrorValidationResult>> handleValidationExceptions(
            MethodArgumentNotValidException e) {
        ErrorValidationResult errorValidationResult = new ErrorValidationResult();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorValidationResult.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }

        BaseResponse<ErrorValidationResult> response = new BaseResponse<>(
                HttpStatus.BAD_REQUEST,
                false,
                ErrorValidationResult.ERROR_MESSAGE,
                ErrorValidationResult.ERROR_STATUS_CODE,
                errorValidationResult
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // 커스텀 예외 처리
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse<Void>> handleBaseExceptions(BaseException e) {
        BaseResponse<Void> response = new BaseResponse<>(e.getStatus());
        return ResponseEntity.status(e.getStatus().getHttpStatusCode()).body(response);
    }


    // 로그인 실패 시 (이메일)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<BaseResponse<Void>> handleAuthError(AuthenticationException ex) {
        BaseResponse<Void> response = new BaseResponse<>(
                HttpStatus.UNAUTHORIZED,
                false,
                "인증에 실패하였습니다.",
                401,
                null
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

}
