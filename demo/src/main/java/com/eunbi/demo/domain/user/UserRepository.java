package com.eunbi.demo.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // email 을 통해 신규 가입자인지 기존 가입자인지 확인
    Optional<User> findByEmail(String email);
}
