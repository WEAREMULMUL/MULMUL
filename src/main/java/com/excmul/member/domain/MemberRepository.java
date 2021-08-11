package com.excmul.member.domain;

import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.vo.NicknameVo;
import com.excmul.member.domain.vo.PhoneNumberVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByEmail(EmailVo email);

    boolean existsByEmail(EmailVo email);

    boolean existsByNickname(NicknameVo nickname);

    boolean existsByPhoneNumber(PhoneNumberVo phoneNumber);
}
