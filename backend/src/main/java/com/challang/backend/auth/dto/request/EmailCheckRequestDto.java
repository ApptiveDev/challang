package com.challang.backend.auth.dto.request;

import jakarta.validation.constraints.*;

public record EmailCheckRequestDto(
        @Email
        @NotEmpty(message = "이메일을 입력해 주세요")
        String email,

        @NotEmpty(message = "인증 번호를 입력해 주세요")
        String authNum
) {}
