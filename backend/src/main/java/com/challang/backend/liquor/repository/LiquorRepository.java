package com.challang.backend.liquor.repository;

import com.challang.backend.liquor.entity.Liquor;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiquorRepository extends JpaRepository<Liquor, Long> {

    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Long id);

    List<Liquor> findAllByOrderByNameDesc(Pageable pageable);
    List<Liquor> findByNameLessThanOrderByNameDesc(String name, Pageable pageable);
}
