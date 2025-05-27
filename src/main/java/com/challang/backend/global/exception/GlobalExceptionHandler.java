package com.challang.backend.global.exception;


import com.challang.backend.auth.exception.InvalidAccessTokenException;
import com.challang.backend.auth.exception.KakaoServerException;
import com.challang.backend.auth.exception.RefreshTokenNotFoundException;
import com.challang.backend.user.exception.UnderageException;
import com.challang.backend.user.exception.UserAlreadyExistsException;
import com.challang.backend.user.exception.UserNickNameAlreadyExistsException;
import com.challang.backend.user.exception.UserNotFoundException;
import com.challang.backend.util.response.*;
import org.springframework.http.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseUtil {


    // dto 검증 실패 시
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<ErrorValidationResult>> invalidRequestHandler(MethodArgumentNotValidException e) {
        ErrorValidationResult errorValidationResult = new ErrorValidationResult();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorValidationResult.addValidation(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return errorMessage("VALIDATION_ERROR", errorValidationResult);
    }

    // 로그인 실패 시 (이메일)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseDto<String>> handleAuthError(AuthenticationException ex) {
        return createResponse("401", "UNAUTHORIZED", null, HttpStatus.UNAUTHORIZED);
    }

    // user 관련
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ResponseDto<String>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ResponseDto<String> response = new ResponseDto<>(
                "400",
                "FAILURE",
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNickNameAlreadyExistsException.class)
    public ResponseEntity<ResponseDto<String>> handleUserNickNameAlreadyExistsException(
            UserNickNameAlreadyExistsException ex) {
        ResponseDto<String> response = new ResponseDto<>(
                "400",
                "FAILURE",
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseDto<String>> handleUserNotFoundException(UserNotFoundException ex) {
        ResponseDto<String> response = new ResponseDto<>(
                "404",
                "NOT_FOUND",
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnderageException.class)
    public ResponseEntity<ResponseDto<String>> handleUnderage(UnderageException ex) {
        return createResponse("400", "UNDERAGE", ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ResponseDto<String>> handleRefreshTokenNotFound(RefreshTokenNotFoundException ex) {
        return createResponse("404", "REFRESH_TOKEN_NOT_FOUND", ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(InvalidAccessTokenException.class)
    @ResponseBody
    public ResponseEntity<ResponseDto<String>> handleInvalidAccessToken(InvalidAccessTokenException ex) {
        return createResponse("401", "INVALID_ACCESS_TOKEN", ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(KakaoServerException.class)
    @ResponseBody
    public ResponseEntity<ResponseDto<String>> handleKakaoServerError(KakaoServerException ex) {
        return createResponse("500", "KAKAO_SERVER_ERROR", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
