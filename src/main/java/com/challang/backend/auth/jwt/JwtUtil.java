package com.challang.backend.auth.jwt;

import static com.challang.backend.auth.constant.AuthConstant.*;

import com.challang.backend.auth.service.CustomUserDetailsService;
import com.challang.backend.global.util.RedisUtil;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.*;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtUtil {

    private final SecretKey secretKey;
    private final Long accessExpiredTime;
    private final Long refreshExpiredTime;

    private final RedisUtil redisUtil;
    private final CustomUserDetailsService userDetailsService;


    // 시크릿 키 암호화하여 키 생성
    public JwtUtil(@Value("${jwt.secret}") String secret,
                   @Value("${jwt.access-expired-time}") long accessExpiredTime,
                   @Value("${jwt.refresh-expired-time}") long refreshExpiredTime,
                   RedisUtil redisUtil, CustomUserDetailsService userDetailsService) {
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
        this.accessExpiredTime = accessExpiredTime;
        this.refreshExpiredTime = refreshExpiredTime;
        this.redisUtil = redisUtil;
        this.userDetailsService = userDetailsService;
    }

    public String createAccessToken(Authentication authentication) {
        return generateToken(authentication, ACCESS_TOKEN, accessExpiredTime);
    }

    public String createRefreshToken(Authentication authentication) {
        return generateToken(authentication, REFRESH_TOKEN, refreshExpiredTime);
    }

    private String generateToken(Authentication authentication, String category, long expiredTime) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String jti = UUID.randomUUID().toString();

        return Jwts.builder().id(jti)
                .subject(userDetails.getUserId().toString())
                .claim(CATEGORY, category)
//                .claim(EMAIL, userDetails.getEmail())
                .claim(ROLE, userDetails.getUser().getRole().name())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredTime))
                .signWith(secretKey)
                .compact();
    }


    // access인지 refresh인지
    public String getCategory(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
                .get(CATEGORY, String.class);
    }

    public String getJti(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getId();
    }

    public Long getId(String token) {
        Claims payload = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return Long.valueOf(payload.getSubject());
    }


    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
                .get(ROLE, String.class);
    }

//    public String getEmail(String token) {
//        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload()
//                .get(EMAIL, String.class);
//    }


    public JwtValidationType validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            return JwtValidationType.VALID_JWT;
        } catch (SecurityException | MalformedJwtException e) {
            return JwtValidationType.INVALID_JWT_TOKEN;
        } catch (ExpiredJwtException ex) {
            return JwtValidationType.EXPIRED_JWT_TOKEN;
        } catch (UnsupportedJwtException ex) {
            return JwtValidationType.UNSUPPORTED_JWT_TOKEN;
        } catch (IllegalArgumentException ex) {
            return JwtValidationType.EMPTY_JWT;
        }
    }

    // access token 추출
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith(BEARER)) {
            return bearerToken.substring(7);
        }

        return null;
    }

    // 리프레시 토큰 검증
    public boolean validateRefreshToken(String refreshToken) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(refreshToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        return request.getHeader("Refresh-Token");
    }

    // refresh token 사용해서 새로운 access 토큰 생성
    public String createTokenFromRefreshToken(String refreshToken) {
        if (!validateRefreshToken(refreshToken)) {
            throw new ExpiredJwtException(null, null, "만료된 토큰 값입니다.");
        }

        if (!REFRESH_TOKEN.equals(getCategory(refreshToken))) {
            throw new RuntimeException("refresh token이 아닙니다.");
        }

        String jti = getJti(refreshToken);
        if (!redisUtil.existData(jti)) {
            throw new RuntimeException("이미 만료된 refresh token입니다.");
        }

        Long id = getId(refreshToken);
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserById(id);
        return createAccessToken(
                new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()));
    }

}
