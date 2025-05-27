package com.challang.backend.user.repository;


import com.challang.backend.user.entity.User;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByOauthId(String oauthId);

    boolean existsByOauthId(String oauthId);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);


}
