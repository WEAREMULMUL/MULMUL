package com.excmul.member.domain;

import com.excmul.common.domain.vo.TokenVo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangePasswordTokenRepository extends JpaRepository<ChangePasswordToken, Long> {
    ChangePasswordToken findByToken(TokenVo token);
}
