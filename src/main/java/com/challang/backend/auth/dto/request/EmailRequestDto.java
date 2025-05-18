package com.challang.backend.auth.dto.request;

import jakarta.validation.constraints.*;

public record EmailRequestDto(
        @Email
        @NotEmpty(message = "이메일을 입력해 주세요")
        String email) {
}
