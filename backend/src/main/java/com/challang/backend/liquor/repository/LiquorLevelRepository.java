package com.challang.backend.liquor.repository;

import com.challang.backend.liquor.entity.LiquorLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiquorLevelRepository extends JpaRepository<LiquorLevel, Long> {

    boolean existsByName(String name);

}
