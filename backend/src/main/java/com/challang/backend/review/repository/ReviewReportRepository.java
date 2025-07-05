package com.challang.backend.review.repository;

import com.challang.backend.review.entity.Review;
import com.challang.backend.review.entity.ReviewReport;
import com.challang.backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewReportRepository extends JpaRepository<ReviewReport, Long> {

    /**
     * 사용자와 리뷰로 이미 신고 기록이 있는지 확인합니다.
     */
    boolean existsByUserAndReview(User user, Review review);
}