package com.excmul.follow.domain;

import com.excmul.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {
    void deleteByFromMemberAndToMember(Member fromMember, Member toMember);

    Optional<Follow> findFollowByFromMemberAndToMember(Member fromMember, Member toMember);

    int countByFromMember(Member fromMember);

    int countByToMember(Member toMember);
}