package com.challang.backend.review.service;

import com.challang.backend.liquor.entity.Liquor;
// import com.challang.backend.liquor.repository.LiquorRepository; // 임시로 주석 처리
import com.challang.backend.review.dto.request.ReviewCreateRequestDto;
import com.challang.backend.review.dto.request.ReviewUpdateRequestDto;
import com.challang.backend.review.dto.response.ReviewResponseDto;
import com.challang.backend.review.entity.Review;
import com.challang.backend.review.exception.ReviewErrorCode;
import com.challang.backend.review.exception.ReviewException;
import com.challang.backend.review.repository.ReviewRepository;
import com.challang.backend.user.entity.User;
import com.challang.backend.user.repository.UserRepository;
import jakarta.persistence.EntityManager; // EntityManager import
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
    // private final LiquorRepository liquorRepository; // LiquorRepository 의존성 제거
    private final EntityManager entityManager; // EntityManager 의존성 추가

    /**
     * 리뷰 생성
     */
    //TODO : 토큰 받아와서 사용자 찾는 로직 (+유저 탈퇴)
    @Transactional
    public ReviewResponseDto createReview(Long liquorId, ReviewCreateRequestDto request, Long userId) {
        // 1. 사용자 정보 조회
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.USER_NOT_FOUND)); // UserErrorCode 정의 필요

        // 2. Liquor 프록시 객체 생성 (DB 조회 없이 ID만으로 관계 설정)
        Liquor liquorProxy = entityManager.getReference(Liquor.class, liquorId);

        // 3. Review 엔티티 생성 (Builder 사용)
        Review review = Review.builder()
                .writer(user)
                .liquor(liquorProxy) // 프록시 객체 사용
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
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));

        if (!Objects.equals(review.getWriter().getUserId(), userId)) {
            throw new ReviewException(ReviewErrorCode.NO_AUTHORITY_FOR_REVIEW);
        }

        review.updateContent(request.content());

        return ReviewResponseDto.from(review);
    }

    /**
     * 리뷰 삭제
     */
    @Transactional
    public void deleteReview(Long reviewId, Long userId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));

        if (!Objects.equals(review.getWriter().getUserId(), userId)) {
            throw new ReviewException(ReviewErrorCode.NO_AUTHORITY_FOR_REVIEW);
        }

        reviewRepository.delete(review);
    }
}
