package com.challang.backend.auth.controller;

import com.challang.backend.auth.dto.request.*;
import com.challang.backend.auth.dto.response.TokenResponse;
import com.challang.backend.auth.jwt.CustomUserDetails;
import com.challang.backend.auth.service.AuthService;
import com.challang.backend.auth.service.EmailVerificationService;
import com.challang.backend.util.response.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController extends ResponseUtil {

    private final AuthService authService;
//    private final EmailVerificationService emailVerificationService;


    @PostMapping("/kakao/signup")
    public ResponseEntity<ResponseDto<Boolean>> kakaoSignUp(@RequestBody @Valid KakaoSignUpRequestDto requestDto) {
        boolean success = authService.kakaoSignUp(requestDto);
        return successResponse(success);
    }

    @PostMapping("/kakao/signin")
    public ResponseEntity<ResponseDto<TokenResponse>> kakaoSignIn(
            @RequestBody @Valid KakaoSignInRequestDto requestDto) {
        TokenResponse response = authService.kakaoSignIn(requestDto.accessToken());
        return successResponse(response);
    }


    @PostMapping("/refresh-token")
    public ResponseEntity<ResponseDto<TokenResponse>> refreshAccessToken(HttpServletRequest request) {
        TokenResponse response = authService.reissueTokens(request);
        return successResponse(response);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<ResponseDto<String>> logout(@RequestHeader(name = "Refresh-Token") String refreshToken) {
        boolean logoutSuccess = authService.logout(refreshToken);

        return logoutSuccess
                ? successMessage("로그아웃 성공")
                : errorMessage("로그아웃 실패");
    }


    @DeleteMapping("/user")
    public ResponseEntity<ResponseDto<String>> deleteUser(
            @RequestHeader(name = "Refresh-Token") String refreshToken,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        authService.deleteUser(userDetails.getUserId(), refreshToken);
        return successMessage("회원 탈퇴 완료");
    }

// 이메일 회원가입 기능 제외에 따라 주석 처리
//    @PostMapping("/email/signup")
//    public ResponseEntity<ResponseDto<Boolean>> emailSignUp(@RequestBody @Valid EmailSignUpRequestDto requestDto) {
//        boolean success = authService.emailSignUp(requestDto);
//        return successResponse(success);
//    }
//
//    @PostMapping("/email/signin")
//    public ResponseEntity<ResponseDto<TokenResponse>> emailSignIn(
//            @RequestBody @Valid EmailSignInRequestDto requestDto) {
//        TokenResponse response = authService.emailSignIn(requestDto);
//        return successResponse(response);
//    }


//    @PostMapping("/email/send")
//    public ResponseEntity<ResponseDto<String>> sendAuthEmail(
//            @RequestBody @Valid EmailRequestDto emailDto) {
//        emailVerificationService.sendAuthEmail(emailDto.email());
//        return successMessage("인증 이메일 전송 완료");
//    }
//
//
//    @PostMapping("/email/check")
//    public ResponseEntity<ResponseDto<String>> verifyAuthNumber(
//            @RequestBody @Valid EmailCheckRequestDto requestDto) {
//        boolean isVerified = emailVerificationService.verifyAuthNumber(
//                requestDto.email(), requestDto.authNum());
//
//        return isVerified
//                ? successMessage("인증 성공")
//                : errorMessage("인증 실패");
//    }


}
