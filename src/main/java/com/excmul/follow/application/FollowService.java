package com.excmul.follow.application;

import com.excmul.follow.domain.Follow;
import com.excmul.follow.domain.FollowRepository;
import com.excmul.member.domain.Member;
import com.excmul.member.domain.MemberRepository;
import com.excmul.member.domain.vo.EmailVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;

    @Transactional
    public EmailVo followMember(EmailVo fromEmail, EmailVo toEmail) {
        selfFollow(fromEmail, toEmail);

        Optional<Member> fromMember = memberRepository.findByEmail(fromEmail);
        Optional<Member> toMember = memberRepository.findByEmail(toEmail);
        Optional<Follow> follow = followRepository.findFollowByFromMemberAndToMember(fromMember.get(), toMember.get());

        if (follow.isEmpty()) {
            followRepository.save(Follow.builder()
                    .fromMember(fromMember.get())
                    .toMember(toMember.get())
                    .build()
            );
        }
        return toEmail;
    }

    @Transactional
    public EmailVo unfollowMember(EmailVo fromEmail, EmailVo toEmail) {
        selfFollow(fromEmail, toEmail);

        Optional<Member> fromMember = memberRepository.findByEmail(fromEmail);
        Optional<Member> toMember = memberRepository.findByEmail(toEmail);
        Optional<Follow> follow = followRepository.findFollowByFromMemberAndToMember(fromMember.get(), toMember.get());

        if (follow.isPresent()) {
            followRepository.deleteByFromMemberAndToMember(fromMember.get(), toMember.get());
        }

        return toEmail;
    }

    private void selfFollow(EmailVo fromEmail, EmailVo toEmail) {
        if (fromEmail.equals(toEmail)) {
            throw new IllegalArgumentException("같은 이메일입니다!!!");
        }
    }

    @Transactional(readOnly = true)
    public int countFollowFromMe(EmailVo fromEmail) {
        Optional<Member> fromMember = memberRepository.findByEmail(fromEmail);
        return followRepository.countByFromMember(fromMember.get());
    }

    @Transactional(readOnly = true)
    public int countFollowToMe(EmailVo toEmail) {
        Optional<Member> toMember = memberRepository.findByEmail(toEmail);
        return followRepository.countByToMember(toMember.get());
    }

}
