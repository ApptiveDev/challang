package com.challang.backend.auth.dto.request;

import com.challang.backend.user.entity.UserRole;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record KakaoSignUpRequestDto(
        @NotBlank(message = "access token은 필수 입력값입니다.")
        String accessToken,

        @NotBlank(message = "닉네임은 필수 입력값입니다.")
        String nickname,

        @NotNull(message = "성별은 필수 입력값입니다.")
        @Min(value = 0, message = "성별은 0 또는 1만 가능합니다.")
        @Max(value = 1, message = "성별은 0 또는 1만 가능합니다.")
        Integer gender,

        @Past(message = "생년월일은 과거 날짜여야 합니다.")
        @NotNull(message = "생년월일은 필수 입력값입니다.")
        LocalDate birthDate,

        @NotNull(message = "역할은 필수 입력값입니다.")
        UserRole role
) {

}
