package com.challang.backend.liquor.repository;

import com.challang.backend.liquor.entity.LiquorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiquorTypeRepository extends JpaRepository<LiquorType, Long> {

    boolean existsByName(String name);
}
