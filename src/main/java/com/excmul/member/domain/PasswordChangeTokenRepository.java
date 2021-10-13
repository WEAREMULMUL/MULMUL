package com.excmul.member.domain;

import com.excmul.common.domain.vo.TokenSerial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PasswordChangeTokenRepository extends JpaRepository<PasswordChangeToken, Long> {
    Optional<PasswordChangeToken> findByTokenSerial(TokenSerial tokenSerial);

    @Query("SELECT t FROM PasswordChangeToken t JOIN FETCH t.member WHERE t.tokenSerial = :tokenSerial")
    Optional<PasswordChangeToken> findByTokenWithMember(TokenSerial tokenSerial);
}
