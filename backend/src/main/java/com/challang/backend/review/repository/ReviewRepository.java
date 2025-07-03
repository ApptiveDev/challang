package com.challang.backend.review.repository;

import com.challang.backend.review.entity.Review;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.*;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByLiquorIdOrderByIdDesc(Long liquorId);

    @Modifying
    @Query("DELETE FROM Review r WHERE r.liquor.id = :liquorId")
    void deleteByLiquorId(@Param("liquorId") Long liquorId);
}