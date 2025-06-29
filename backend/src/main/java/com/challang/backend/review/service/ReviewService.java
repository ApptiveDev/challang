package com.challang.backend.review.service;

import com.challang.backend.global.exception.BaseException;
import com.challang.backend.liquor.code.LiquorCode;
import com.challang.backend.liquor.entity.Liquor;
import com.challang.backend.liquor.repository.LiquorRepository;
import com.challang.backend.review.dto.request.*;
import com.challang.backend.review.dto.response.*;
import com.challang.backend.review.entity.Review;
import com.challang.backend.review.exception.ReviewErrorCode;
import com.challang.backend.review.repository.ReviewRepository;
import com.challang.backend.user.entity.User;
import com.challang.backend.user.exception.UserErrorCode;
import com.challang.backend.user.repository.UserRepository;
import com.challang.backend.util.aws.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final LiquorRepository liquorRepository;
    private final S3Service s3Service;

    @Value("${cloud.aws.s3.url}")
    private String s3BaseUrl;

    /**
     * 리뷰 생성
     */
    @Transactional
    public ReviewResponseDto createReview(Long liquorId, ReviewCreateRequestDto request, Long userId) {
        // 1. 사용자 정보 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(UserErrorCode.USER_NOT_FOUND, "유저를 찾을 수 없습니다."));

        // 2. Liquor 정보 조회
        Liquor liquor = liquorRepository.findById(liquorId)
                .orElseThrow(() -> new BaseException(LiquorCode.LIQUOR_NOT_FOUND));

        // 3. Review 엔티티 생성
        Review review = Review.builder()
                .writer(user)
                .liquor(liquor)
                .imageUrl(request.imageUrl())
                .content(request.content())
                .build();

        // 4. DB에 저장
        Review savedReview = reviewRepository.save(review);

        // 5. DTO로 변환하여 반환
        return ReviewResponseDto.from(savedReview, s3BaseUrl);
    }

    /**
     * 특정 주류의 리뷰 목록 조회
     */
    public List<ReviewResponseDto> getReviews(Long liquorId) {
        return reviewRepository.findByLiquorIdOrderByIdDesc(liquorId)
                .stream()
                .map(review -> ReviewResponseDto.from(review, s3BaseUrl))
                .toList();
    }

    /**
     * 리뷰 수정
     */
    @Transactional
    public ReviewResponseDto updateReview(Long reviewId, ReviewUpdateRequestDto request, Long userId) {
        Review review = findReview(reviewId, userId);

        if (request.imageUrl() != null && !Objects.equals(review.getImageUrl(), request.imageUrl())) {
            s3Service.deleteByKey(review.getImageUrl());
        }

        review.update(request);
        return ReviewResponseDto.from(review, s3BaseUrl);
    }

    /**
     * 리뷰 삭제
     */
    @Transactional
    public void deleteReview(Long reviewId, Long userId) {
        Review review = findReview(reviewId, userId);
        s3Service.deleteByKey(review.getImageUrl());
        reviewRepository.delete(review);
    }

    private Review findReview(Long reviewId, Long userId) {
        // 리뷰 조회
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new BaseException(ReviewErrorCode.REVIEW_NOT_FOUND));

        // 수정/삭제 권한 확인
        if (!Objects.equals(review.getWriter().getUserId(), userId)) {
            throw new BaseException(ReviewErrorCode.NO_AUTHORITY_FOR_REVIEW);
        }

        return review;
    }
}
