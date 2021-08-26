package com.excmul.member.domain;

import com.excmul.common.domain.vo.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PasswordChangeTokenRepository extends JpaRepository<PasswordChangeToken, Long> {
    Optional<PasswordChangeToken> findByToken(Token token);

    @Query("SELECT t FROM PasswordChangeToken t JOIN FETCH t.member WHERE t.token = :token")
    Optional<PasswordChangeToken> findByTokenWithMember(Token token);
}
