package com.excmul.member.domain;

import com.excmul.member.domain.vo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findByEmail(EmailVo email);

    boolean existsByEmail(EmailVo email);

    boolean existsByNickname(NicknameVo nickname);

    boolean existsByPhoneNumber(PhoneNumberVo phoneNumber);

    Optional<Member> findByNameAndBirthAndPhoneNumber(NameVo name, BirthVo birth, PhoneNumberVo phoneNumber);

    Optional<Member> findByEmailAndNameAndBirthAndPhoneNumber(EmailVo email, NameVo name, BirthVo birth, PhoneNumberVo phoneNumber);
}
