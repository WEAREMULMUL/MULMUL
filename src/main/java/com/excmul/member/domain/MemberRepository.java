package com.excmul.member.domain;

import com.excmul.member.domain.vo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByEmail(EmailVo email);

    boolean existsByEmail(EmailVo email);

    boolean existsByNickname(NicknameVo nickname);

    boolean existsByPhoneNumber(PhoneNumberVo phoneNumber);

    @Query("SELECT m.email FROM Member m WHERE m.name = :name AND m.birth = :birth AND m.phoneNumber = :phoneNumber")
    Optional<EmailVo> findEmailByPrivacy(NameVo name, BirthVo birth, PhoneNumberVo phoneNumber);

    @Query("SELECT m FROM Member m WHERE m.email = :email AND m.name = :name AND m.birth = :birth AND m.phoneNumber = :phoneNumber")
    Optional<Member> findByPrivacy(EmailVo email, NameVo name, BirthVo birth, PhoneNumberVo phoneNumber);
}
