package com.challang.backend.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record KakaoSignInRequestDto(
        @NotBlank(message = "access token은 필수 입력값입니다.")
        String accessToken
) {
}
