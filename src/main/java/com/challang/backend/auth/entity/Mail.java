package com.challang.backend.auth.entity;

import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "email", timeToLive = 360)
public class Mail {

    @Id
    private Long id;

    @Indexed
    private String mail;

    private Integer number;

    private LocalDateTime regDate;

    public Mail(String mail, int number) {
        this.mail = mail;
        this.number = number;
        this.regDate = LocalDateTime.now();
    }
}