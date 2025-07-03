package com.challang.backend.tag.repository;

import com.challang.backend.tag.entity.LiquorTag;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LiquorTagRepository extends JpaRepository<LiquorTag, Long> {

    List<LiquorTag> findByLiquorId(Long liquorId);
    long countByLiquorIdAndIsCoreTrue(Long liquorId);


    @Modifying
    @Query("DELETE FROM LiquorTag lt WHERE lt.liquor.id = :liquorId")
    void deleteByLiquorId(@Param("liquorId") Long liquorId);
}
