package com.challang.backend.auth.dto.request;


import com.challang.backend.user.entity.UserRole;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

public record EmailSignUpRequestDto(
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        @NotBlank(message = "이메일은 필수 입력값입니다.")
        String email,

        @NotBlank(message = "비밀번호는 필수 입력값입니다.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "비밀번호는 8자 이상, 영문과 숫자를 포함해야 합니다.")
        String password,

        @NotBlank(message = "닉네임은 필수 입력값입니다.")
        String nickname,

        @NotNull(message = "성별은 필수 입력값입니다.")
        @Min(value = 0, message = "성별은 0 또는 1이어야 합니다.")
        @Max(value = 1, message = "성별은 0 또는 1이어야 합니다.")
        Integer gender,

        @NotNull(message = "생년월일은 필수 입력값입니다.")

        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate birthDate,

        @NotNull(message = "역할은 필수 입력값입니다.")
        UserRole role
) {
}
