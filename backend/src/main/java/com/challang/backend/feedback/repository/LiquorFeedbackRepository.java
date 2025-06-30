package com.challang.backend.feedback.repository;

import com.challang.backend.feedback.entity.LiquorFeedback;
import com.challang.backend.liquor.entity.Liquor;
import com.challang.backend.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LiquorFeedbackRepository extends JpaRepository<LiquorFeedback, Long> {
    List<LiquorFeedback> findByUser(User user);

    Optional<LiquorFeedback> findByUserAndLiquor(User user, Liquor liquor);

}
