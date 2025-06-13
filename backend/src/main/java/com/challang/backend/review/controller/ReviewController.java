package com.challang.backend.review.controller;

import com.challang.backend.auth.jwt.CustomUserDetails;
import com.challang.backend.review.dto.request.ReviewCreateRequestDto;
import com.challang.backend.review.dto.request.ReviewUpdateRequestDto;
import com.challang.backend.review.dto.response.ReviewResponseDto;
import com.challang.backend.review.service.ReviewService;
import com.challang.backend.util.response.BaseResponse; // BaseResponse import
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Review API", description = "리뷰 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 생성", description = "특정 주류에 대한 리뷰를 작성합니다. (인증 필요)")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "리뷰 작성 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "404", description = "사용자 또는 주류 정보를 찾을 수 없음")
    })
    @PostMapping("/liquors/{liquorId}/reviews")
    public ResponseEntity<BaseResponse<ReviewResponseDto>> createReview(
            @PathVariable Long liquorId,
            @RequestBody ReviewCreateRequestDto request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails) {
        ReviewResponseDto responseDto = reviewService.createReview(liquorId, request, userDetails.getUserId());
        // BaseResponse 생성자를 직접 호출하여 성공 응답을 생성합니다.
        return ResponseEntity.ok(new BaseResponse<>(responseDto));
    }

    @Operation(summary = "특정 주류의 리뷰 목록 조회", description = "주류 ID로 해당 주류의 모든 리뷰를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공")
    })
    @GetMapping("/liquors/{liquorId}/reviews")
    public ResponseEntity<BaseResponse<List<ReviewResponseDto>>> getReviews(@PathVariable Long liquorId) {
        List<ReviewResponseDto> responseDtoList = reviewService.getReviews(liquorId);
        // BaseResponse 생성자를 직접 호출하여 성공 응답을 생성합니다.
        return ResponseEntity.ok(new BaseResponse<>(responseDtoList));
    }

    @Operation(summary = "리뷰 수정", description = "자신이 작성한 리뷰를 수정합니다. (인증 필요)")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "리뷰 수정 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "수정 권한 없음"),
            @ApiResponse(responseCode = "404", description = "리뷰를 찾을 수 없음")
    })
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<BaseResponse<ReviewResponseDto>> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewUpdateRequestDto request,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails) {
        ReviewResponseDto responseDto = reviewService.updateReview(reviewId, request, userDetails.getUserId());
        // BaseResponse 생성자를 직접 호출하여 성공 응답을 생성합니다.
        return ResponseEntity.ok(new BaseResponse<>(responseDto));
    }

    @Operation(summary = "리뷰 삭제", description = "자신이 작성한 리뷰를 삭제합니다. (인증 필요)")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "리뷰 삭제 성공"),
            @ApiResponse(responseCode = "401", description = "인증 실패"),
            @ApiResponse(responseCode = "403", description = "삭제 권한 없음"),
            @ApiResponse(responseCode = "404", description = "리뷰를 찾을 수 없음")
    })
    @DeleteMapping("/reviews/{reviewId}")
    // 반환 타입의 제네릭을 <String>으로 변경하여 성공 메시지를 담습니다.
    public ResponseEntity<BaseResponse<String>> deleteReview(
            @PathVariable Long reviewId,
            @Parameter(hidden = true) @AuthenticationPrincipal CustomUserDetails userDetails) {
        reviewService.deleteReview(reviewId, userDetails.getUserId());
        // AuthController의 logout과 같이, 성공 메시지를 담은 BaseResponse를 생성합니다.
        return ResponseEntity.ok(new BaseResponse<>("리뷰가 성공적으로 삭제되었습니다."));
    }
}
