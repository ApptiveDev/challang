package com.challang.backend.preference.repository;


import com.challang.backend.preference.entity.LiquorPreferenceLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiquorPreferenceLevelRepository extends JpaRepository<LiquorPreferenceLevel, Long> {
}
