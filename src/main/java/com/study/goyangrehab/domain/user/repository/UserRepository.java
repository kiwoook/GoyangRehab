package com.study.goyangrehab.domain.user.repository;

import com.study.goyangrehab.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUserId(String userId);
    Boolean existsByName(String name);
}
