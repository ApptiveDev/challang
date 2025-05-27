package com.challang.backend.auth.entity;


import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 24 * 1000L * 7)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {
    @Id
    private Long id;

    private String refreshToken;

    @Builder
    private Token(Long id, String refreshToken) {
        this.id = id;
        this.refreshToken = refreshToken;
    }
}
