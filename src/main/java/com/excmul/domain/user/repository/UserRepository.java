package com.excmul.domain.user.repository;

import com.excmul.domain.user.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}


// 구글, 카카오, 페이스북, 네이버 -> OAUth2.0