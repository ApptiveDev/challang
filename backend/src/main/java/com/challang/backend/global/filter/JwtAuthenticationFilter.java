package com.challang.backend.global.filter;

import static com.challang.backend.auth.jwt.JwtValidationType.VALID_JWT;

import com.challang.backend.auth.jwt.JwtUtil;
import com.challang.backend.auth.service.CustomUserDetailsService;
import com.challang.backend.global.util.RedisUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;


// JWT 토큰을 사용해 인증을 처리하는 필터
@RequiredArgsConstructor

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;
    private final CustomUserDetailsService userDetailsService;


    private static final List<String> SKIP_PATHS = List.of(
            "/api/auth/email/signup",
            "/api/auth/email/signin",
            "/api/auth/email/send",
            "/api/auth/email/check",
            "/api/auth/kakao/signup",
            "/api/auth/kakao/signin",
            "/api/auth/refresh-token"
    );


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return SKIP_PATHS.stream().anyMatch(path::equals);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        try {
            // 1. 토큰 추출
            String token = jwtUtil.resolveToken(request);

            // 2. 토큰 유효성 검증
            if (jwtUtil.validateToken(token) == VALID_JWT) {
                // 3. redis에 저장된 토큰과 비교
//                String jti = jwtUtil.getJti(token);
//                String storedToken = redisUtil.getData(jti);
//                if (storedToken == null || !storedToken.equals(token)) {
//                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않은 토큰입니다.");
//                    return;
//                }

                /// 4. 인증 정보 등록
                Long userId = jwtUtil.getId(token);
                UserDetails userDetails = userDetailsService.loadUserById(userId);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT 토큰이 만료되었습니다.");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "유효하지 않은 JWT 토큰입니다.");
        }
    }


}
