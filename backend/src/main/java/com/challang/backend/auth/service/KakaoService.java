package com.challang.backend.auth.service;

import com.challang.backend.auth.dto.response.*;
import com.challang.backend.auth.exception.AuthErrorCode;
import com.challang.backend.global.exception.BaseException;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpStatusCode;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoService {

    private String clientId;
    private final String KAUTH_TOKEN_URL_HOST;
    private final String KAUTH_USER_URL_HOST;

    @Autowired
    public KakaoService(@Value("${kakao.client-id}") String clientId) {
        this.clientId = clientId;
        KAUTH_TOKEN_URL_HOST = "https://kauth.kakao.com";
        KAUTH_USER_URL_HOST = "https://kapi.kakao.com";
    }


    // TODO: 잘못된 액세스 토큰일 경우, 500이라고 띄어짐 => 공부 후 수정 필요
    // access 토큰으로 사용자 정보 가져오기
    public KakaoUserInfoResponseDto getUserInfo(String accessToken) {
        return WebClient.create(KAUTH_USER_URL_HOST)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        clientResponse -> Mono.error(new BaseException(AuthErrorCode.INVALID_KAKAO_ACCESS_TOKEN)))
                .onStatus(HttpStatusCode::is5xxServerError,
                        clientResponse -> Mono.error(new BaseException(AuthErrorCode.KAKAO_SERVER_ERROR)))
                .bodyToMono(KakaoUserInfoResponseDto.class)
                .block();
    }

}

