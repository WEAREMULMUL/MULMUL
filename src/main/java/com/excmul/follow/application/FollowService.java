package com.excmul.follow.application;

import com.excmul.follow.domain.Follow;
import com.excmul.follow.domain.FollowRepository;
import com.excmul.member.application.MemberService;
import com.excmul.member.domain.Member;
import com.excmul.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final MemberService memberService;

    @Transactional
    public void followMember(long fromMemberId, long toMemberId) {
        Member fromMember = memberService.findMemberById(fromMemberId);
        Member toMember = memberService.findMemberById(toMemberId);
        followRepository.save(fromMember.newFollow(toMember));
    }

    @Transactional
    public void unfollowMember(long fromMemberId, long toMemberId) {
        Member fromMember = memberService.findMemberById(fromMemberId);
        Member toMember = memberService.findMemberById(toMemberId);
        followRepository.deleteByFromMemberAndToMember(fromMember, toMember);
    }

    @Transactional(readOnly = true)
    public int countFollowFromMe(long fromMemberId) {
        Member fromMember = memberService.findMemberById(fromMemberId);
        return followRepository.countByFromMember(fromMember);
    }

    @Transactional(readOnly = true)
    public int countFollowToMe(long toMemberId) {
        Member toMember = memberService.findMemberById(toMemberId);
        return followRepository.countByToMember(toMember);
    }

}
