package com.challang.backend.liquor.repository;

import com.challang.backend.liquor.entity.Liquor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LiquorRepository extends JpaRepository<Liquor, Long> {

    @Query("""
                SELECT DISTINCT l FROM Liquor l
                LEFT JOIN FETCH l.level
                LEFT JOIN FETCH l.type
                LEFT JOIN FETCH l.liquorTags lt
                LEFT JOIN FETCH lt.tag
                WHERE l.id = :id
            """)
    Optional<Liquor> findWithAllRelationsById(@Param("id") Long id);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    @Query("""
                SELECT DISTINCT l FROM Liquor l
                LEFT JOIN FETCH l.level
                LEFT JOIN FETCH l.type
                LEFT JOIN FETCH l.liquorTags lt
                LEFT JOIN FETCH lt.tag
                WHERE (:cursor IS NULL OR l.name < :cursor)
                ORDER BY l.name DESC
            """)
    List<Liquor> findAllWithTagsByCursor(@Param("cursor") String cursor, Pageable pageable);

}
