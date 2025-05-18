package com.challang.backend.global.config;


import com.challang.backend.auth.jwt.*;
import com.challang.backend.auth.service.CustomUserDetailsService;
import com.challang.backend.global.filter.JwtAuthenticationFilter;
import com.challang.backend.global.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;
    private final CustomJwtAuthenticationEntryPoint customJwtAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    // 인증 제외 경로
    private static final String[] AUTH_WHITELIST = {
            "/api/auth/email/**",
            "/api/auth/kakao/**",
            "/api/auth/refresh-token"
    };

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 인증 요청 처리
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // CSRF 및 기본 form 로그인 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)

                // 세션 사용X
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 예외
                .exceptionHandling(exception -> {
                    exception.authenticationEntryPoint(customJwtAuthenticationEntryPoint);
                    exception.accessDeniedHandler(customAccessDeniedHandler);
                })

                // HTTP 요청에 대한 인가 규칙 설정
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  //TODO: 개발 단계에서는 모두 경로 허용
//                        .requestMatchers(AUTH_WHITELIST).permitAll()
//                        .requestMatchers("/error").permitAll()
//                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/api/**").hasAnyRole("USER", "ADMIN")
//                        .anyRequest().authenticated() // 그 외 요청은 인증 필요
                )

        // JWT 인증 필터 등록
                .addFilterBefore(new JwtAuthenticationFilter(jwtUtil, redisUtil, customUserDetailsService),
                UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

}
