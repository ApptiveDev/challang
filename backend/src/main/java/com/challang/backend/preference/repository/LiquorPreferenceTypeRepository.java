package com.challang.backend.preference.repository;


import com.challang.backend.liquor.entity.LiquorType;
import com.challang.backend.user.entity.User;
import com.challang.backend.preference.entity.LiquorPreferenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiquorPreferenceTypeRepository extends JpaRepository<LiquorPreferenceType, Long> {
    boolean existsByUserAndLiquorType(User user, LiquorType liquorType);

}
