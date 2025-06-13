package com.challang.backend.review.controller;

import com.challang.backend.auth.jwt.CustomUserDetails;
import com.challang.backend.review.dto.request.ReviewCreateRequestDto;
import com.challang.backend.review.dto.request.ReviewUpdateRequestDto;
import com.challang.backend.review.dto.response.ReviewResponseDto;
import com.challang.backend.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
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
    @PostMapping("/liquors/{liquorId}/reviews")
    public ResponseEntity<ReviewResponseDto> createReview(
            @PathVariable Long liquorId,
            @RequestBody ReviewCreateRequestDto request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        ReviewResponseDto response = reviewService.createReview(liquorId, request, userDetails.getUserId());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "특정 주류의 리뷰 목록 조회", description = "주류 ID로 해당 주류의 모든 리뷰를 조회합니다.")
    @GetMapping("/liquors/{liquorId}/reviews")
    public ResponseEntity<List<ReviewResponseDto>> getReviews(@PathVariable Long liquorId) {
        List<ReviewResponseDto> response = reviewService.getReviews(liquorId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "리뷰 수정", description = "자신이 작성한 리뷰를 수정합니다. (인증 필요)")
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<ReviewResponseDto> updateReview(
            @PathVariable Long reviewId,
            @RequestBody ReviewUpdateRequestDto request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        ReviewResponseDto response = reviewService.updateReview(reviewId, request, userDetails.getUserId());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "리뷰 삭제", description = "자신이 작성한 리뷰를 삭제합니다. (인증 필요)")
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable Long reviewId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        reviewService.deleteReview(reviewId, userDetails.getUserId());
        return ResponseEntity.noContent().build();
    }
}