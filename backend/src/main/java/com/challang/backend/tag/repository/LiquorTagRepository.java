package com.challang.backend.tag.repository;

import com.challang.backend.tag.entity.LiquorTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LiquorTagRepository extends JpaRepository<LiquorTag, Long> {

    @Modifying
    @Query("DELETE FROM LiquorTag lt WHERE lt.liquor.id = :liquorId")
    void deleteByLiquorId(@Param("liquorId") Long liquorId);
}
