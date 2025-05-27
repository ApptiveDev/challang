package com.challang.backend.util.response;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.*;

import java.io.IOException;

public abstract class ResponseUtil {

    protected <T> ResponseEntity<ResponseDto<T>> createResponse(String messageCode, String messageStatus, T data, HttpStatus status) {
        ResponseDto<T> response = new ResponseDto<>(messageCode, messageStatus, data);
        return new ResponseEntity<>(response, status);
    }

    protected <T> ResponseEntity<ResponseDto<T>> successResponse(T data) {
        return createResponse("200", "SUCCESS", data, HttpStatus.OK);
    }

    protected ResponseEntity<ResponseDto<String>> successMessage(String message) {
        return createResponse("200", "SUCCESS", message, HttpStatus.OK);
    }

    protected <T> ResponseEntity<ResponseDto<T>> errorMessage(String messageStatus, T data) {
        return createResponse("400", messageStatus, data, HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<ResponseDto<String>> errorMessage(String messageStatus) {
        return createResponse("400", messageStatus, null, HttpStatus.BAD_REQUEST);
    }

    protected <T> ResponseEntity<ResponseDto<T>> errorResponse(String messageStatus) {
        return createResponse("400", messageStatus, null, HttpStatus.BAD_REQUEST);
    }

    public void writeResponse(HttpServletResponse response, ResponseDto<?> responseDto, HttpStatus status) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status.value());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            response.getWriter().write(objectMapper.writeValueAsString(responseDto));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
