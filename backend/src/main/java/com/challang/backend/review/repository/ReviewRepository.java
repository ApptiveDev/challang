package com.challang.backend.review.repository;

import com.challang.backend.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 특정 주류(Liquor)에 달린 모든 리뷰를 찾기 위한 메서드
    List<Review> findByLiquorIdOrderByIdDesc(Long liquorId);
}