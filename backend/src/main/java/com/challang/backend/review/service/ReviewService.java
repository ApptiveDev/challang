package com.challang.backend.review.service;

import com.challang.backend.global.exception.BaseException;
import com.challang.backend.liquor.entity.Liquor;
import com.challang.backend.liquor.entity.LiquorType;
import com.challang.backend.liquor.repository.LiquorTypeRepository;
import com.challang.backend.review.dto.request.ReviewCreateRequestDto;
import com.challang.backend.review.dto.request.ReviewUpdateRequestDto;
import com.challang.backend.review.dto.response.ReviewResponseDto;
import com.challang.backend.review.entity.Review;
import com.challang.backend.review.exception.ReviewErrorCode;
import com.challang.backend.review.exception.ReviewException;
import com.challang.backend.review.repository.ReviewRepository;
import com.challang.backend.user.entity.User;
import com.challang.backend.user.exception.UserErrorCode;
import com.challang.backend.user.repository.UserRepository;
import jakarta.persistence.EntityManager; // EntityManager import
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
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
     private final LiquorTypeRepository liquorRepository; // LiquorRepository 의존성 제거
    private final EntityManager entityManager; // EntityManager 의존성 추가
    private final LiquorTypeRepository liquorTypeRepository;

    /**
     * 리뷰 생성
     */
    //TODO : 토큰 받아와서 사용자 찾는 로직 (+유저 탈퇴)
    @Transactional
    public ReviewResponseDto createReview(Long liquorId, ReviewCreateRequestDto request, Long userId) {
        // 1. 사용자 정보 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(UserErrorCode.USER_NOT_FOUND, "유저를 찾을 수 없습니다."));

        // 2. Liquor 정보 조회 (임시 프록시 방식 대신 Repository를 사용하는 최종 방식으로 변경)
        LiquorType liquor = liquorRepository.findById(liquorId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.LIQUOR_NOT_FOUND));

        // 3. Review 엔티티 생성
        Review review = Review.builder()
                .writer(user)
                .liquor(liquor)
                .content(request.content())
                .build();

        // 4. DB에 저장
        Review savedReview = reviewRepository.save(review);

        // 5. DTO로 변환하여 반환
        return ReviewResponseDto.from(savedReview);
    }

    /**
     * 특정 주류의 리뷰 목록 조회
     */
    public List<ReviewResponseDto> getReviews(Long liquorId) {
        return reviewRepository.findByLiquorIdOrderByIdDesc(liquorId)
                .stream()
                .map(ReviewResponseDto::from)
                .collect(Collectors.toList());
    }

    /**
     * 리뷰 수정
     */
    @Transactional
    public ReviewResponseDto updateReview(Long reviewId, ReviewUpdateRequestDto request, Long userId) {
        Review review = findReview(reviewId, userId);

        review.updateContent(request.content());

        return ReviewResponseDto.from(review);
    }

    /**
     * 리뷰 삭제
     */
    @Transactional
    public void deleteReview(Long reviewId, Long userId) {
        Review review = findReview(reviewId, userId);

        reviewRepository.delete(review);
    }

    private Review findReview(Long reviewId, Long userId) {
        // 리뷰 조회
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));

        // 수정/삭제 권한 확인
        if (!Objects.equals(review.getWriter().getUserId(), userId)) {
            throw new ReviewException(ReviewErrorCode.NO_AUTHORITY_FOR_REVIEW);
        }

        return review;
    }
}
