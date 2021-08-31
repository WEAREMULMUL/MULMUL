package com.excmul.follow.application;

import com.excmul.follow.domain.Follow;
import com.excmul.follow.domain.FollowRepository;
import com.excmul.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;

    @Transactional
    public Member followMember(Member fromMember, Member toMember) {
        Optional<Follow> follow = followRepository.findFollowByFromMemberAndToMember(fromMember, toMember);

        if (follow.isEmpty()) {
            followRepository.save(Follow.builder()
                    .fromMember(fromMember)
                    .toMember(toMember)
                    .build()
            );
        }
        
        return toMember;
    }

    @Transactional
    public Member unfollowMember(Member fromMember, Member toMember) {
        Optional<Follow> follow = followRepository.findFollowByFromMemberAndToMember(fromMember, toMember);

        if (follow.isPresent()) {
            followRepository.deleteByFromMemberAndToMember(fromMember, toMember);
        }

        return toMember;
    }

    @Transactional(readOnly = true)
    public int countFollowFromMe(Member fromMember) {
        return followRepository.countByFromMember(fromMember);
    }

    @Transactional(readOnly = true)
    public int countFollowToMe(Member toMember) {
        return followRepository.countByToMember(toMember);
    }

}
