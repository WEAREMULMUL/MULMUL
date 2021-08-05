package com.excmul.member.domain;

import com.excmul.auth.AuthPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {

    Optional<MemberEntity> findByEmail(Email email);

    boolean existsByEmail(Email email);

    boolean existsByNickname(Nickname nickname);

    boolean existsByPhoneNumber(PhoneNumber phoneNumber);
}
