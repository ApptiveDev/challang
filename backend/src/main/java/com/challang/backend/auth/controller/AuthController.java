package com.challang.backend.auth.controller;

import com.challang.backend.auth.dto.request.*;
import com.challang.backend.auth.dto.response.TokenResponse;
import com.challang.backend.auth.jwt.CustomUserDetails;
import com.challang.backend.auth.service.AuthService;
import com.challang.backend.util.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth API", description = "사용자 회원가입, 로그인 등 인증 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController extends ResponseUtil {

    private final AuthService authService;

    @Operation(summary = "카카오 계정으로 회원가입", description = "카카오 Access Token과 추가 정보를 받아 회원가입을 처리합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "입력값 유효성 검증 실패 (예: 닉네임 중복, 잘못된 형식 등)"),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 카카오 토큰")
    })
    @PostMapping("/kakao/signup")
    public ResponseEntity<ResponseDto<Boolean>> kakaoSignUp(@RequestBody @Valid KakaoSignUpRequestDto requestDto) {
        boolean success = authService.kakaoSignUp(requestDto);
        return successResponse(success);
    }

    @Operation(summary = "카카오 계정으로 로그인", description = "카카오 Access Token을 사용하여 서비스의 JWT 토큰(Access/Refresh)을 발급받습니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공, 서비스 JWT 토큰 발급"),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 카카오 토큰"),
            @ApiResponse(responseCode = "404", description = "가입되지 않은 사용자 (회원가입 필요)")
    })
    @PostMapping("/kakao/signin")
    public ResponseEntity<ResponseDto<TokenResponse>> kakaoSignIn(
            @RequestBody @Valid KakaoSignInRequestDto requestDto) {
        TokenResponse response = authService.kakaoSignIn(requestDto.accessToken());
        return successResponse(response);
    }

    @Operation(summary = "Access Token 재발급", description = "만료된 Access Token을 Refresh Token을 사용하여 재발급합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "토큰 재발급 성공"),
            @ApiResponse(responseCode = "401", description = "유효하지 않거나 만료된 Refresh Token")
    })
    @PostMapping("/refresh-token")
    public ResponseEntity<ResponseDto<TokenResponse>> refreshAccessToken(
            @Parameter(hidden = true) HttpServletRequest request) { // Swagger에서 불필요한 입력을 받지 않도록 hidden 처리
        TokenResponse response = authService.reissueTokens(request);
        return successResponse(response);
    }

    @Operation(summary = "서비스 로그아웃", description = "서버에 저장된 Refresh Token을 삭제하여 로그아웃 처리합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그아웃 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 혹은 만료된 리프레시 토큰")
    })
    @DeleteMapping("/logout")
    public ResponseEntity<ResponseDto<String>> logout(
            @Parameter(description = "로그인 시 발급받은 Refresh Token", required = true)
            @RequestHeader(name = "Refresh-Token") String refreshToken) {
        boolean logoutSuccess = authService.logout(refreshToken);

        return logoutSuccess
                ? successMessage("로그아웃 성공")
                : errorMessage("로그아웃 실패");
    }

    @Operation(summary = "회원 탈퇴", description = "사용자 계정을 비활성화하고 관련 정보를 삭제합니다. **(Access Token 인증 필요)**")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원 탈퇴 성공"),
            @ApiResponse(responseCode = "401", description = "인증되지 않은 사용자 또는 유효하지 않은 토큰")
    })
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/user")
    public ResponseEntity<ResponseDto<String>> deleteUser(
            @Parameter(description = "로그인 시 발급받은 Refresh Token", required = true)
            @RequestHeader(name = "Refresh-Token") String refreshToken,
            @Parameter(hidden = true)
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
