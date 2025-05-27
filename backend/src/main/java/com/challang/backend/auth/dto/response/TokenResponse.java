package com.challang.backend.auth.dto.response;


import lombok.Getter;

public record TokenResponse(
        String accessToken,
        String refreshToken) {
}
