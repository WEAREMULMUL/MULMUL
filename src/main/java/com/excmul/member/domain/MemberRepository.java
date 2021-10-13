package com.excmul.member.domain;

import com.excmul.member.domain.vo.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(Email email);

    Optional<Member> findById(Long id);

    boolean existsByEmail(Email email);

    boolean existsByNickname(Nickname nickname);

    boolean existsByPhoneNumber(PhoneNumber phoneNumber);

    @Query("SELECT m.email FROM Member m WHERE m.name = :name AND m.birth = :birth AND m.phoneNumber = :phoneNumber")
    Optional<Email> findEmailByPrivacy(Name name, Birth birth, PhoneNumber phoneNumber);

    @Query("SELECT m FROM Member m WHERE m.email = :email AND m.name = :name AND m.birth = :birth AND m.phoneNumber = :phoneNumber")
    Optional<Member> findByEmailAndPrivacy(Email email, Name name, Birth birth, PhoneNumber phoneNumber);
}
