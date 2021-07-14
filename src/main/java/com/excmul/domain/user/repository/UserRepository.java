package com.excmul.domain.user.repository;

import com.excmul.domain.user.vo.UserVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserVo, Long> {
    Optional<UserVo> findByEmail(String email);
}
