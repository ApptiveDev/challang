package com.challang.backend.review.repository;

import com.challang.backend.liquor.dto.response.TagStatDto; // TagStatDto import
import com.challang.backend.review.entity.Review;
import com.challang.backend.review.entity.ReviewTag;
import org.springframework.data.domain.Pageable; // Pageable import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface ReviewTagRepository extends JpaRepository<ReviewTag, Long> {

    /**
     * 특정 주류에 대한 태그 통계를 계산하고, 가장 많이 선택된 순으로 3개까지 조회합니다.
     * @param liquorId 주류 ID
     * @param pageable 결과를 3개로 제한하기 위한 Pageable 객체 (e.g., PageRequest.of(0, 3))
     * @return 태그 통계 DTO 리스트
     */
    @Query("SELECT new com.challang.backend.liquor.dto.response.TagStatDto(" +
            "    rt.tag.name AS tagName, " +
            "    (CAST(COUNT(rt) AS double) * 100.0 / " +
            "        (SELECT COUNT(sub) FROM ReviewTag sub WHERE sub.review.liquor.id = :liquorId)" +
            "    )" +
            ") " +
            "FROM ReviewTag rt " +
            "WHERE rt.review.liquor.id = :liquorId " +
            "GROUP BY rt.tag.name " +
            "ORDER BY COUNT(rt) DESC, rt.tag.name ASC")
    List<TagStatDto> findTopTagStatsByLiquorId(@Param("liquorId") Long liquorId, Pageable pageable);

    /**
     * 특정 리뷰에 연결된 모든 ReviewTag를 삭제합니다.
     * @param review 삭제할 기준이 되는 Review 엔티티
     */
    @Modifying // ❗ JPQL로 UPDATE, DELETE 쿼리를 실행할 때 필요합니다.
    @Query("delete from ReviewTag rt where rt.review = :review")
    void deleteByReview(@Param("review") Review review);

}