package com.excmul.member.application;

import com.excmul.member.domain.Email;
import com.excmul.member.domain.MemberRepository;
import com.excmul.member.domain.Nickname;
import com.excmul.member.domain.PhoneNumber;
import com.excmul.member.dto.DefaultMemberSignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


    @Transactional
    public void createDefaultMember(DefaultMemberSignRequest request) {
        checkDuplicateMember(request);
        memberRepository.save(request.sign());
    }

    private void checkDuplicateMember(DefaultMemberSignRequest request) {
        if (existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("잘못된 회원가입");
        }

        if (existsByNickname(request.getNickname())) {
            throw new IllegalArgumentException("잘못된 회원가입");
        }

        if (existsByPhoneNumber(request.getPhoneNumber())) {
            throw new IllegalArgumentException("잘못된 회원가입");
        }
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(Email email) {
        return memberRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean existsByNickname(Nickname nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    @Transactional(readOnly = true)
    public boolean existsByPhoneNumber(PhoneNumber phoneNumber) {
        return memberRepository.existsByPhoneNumber(phoneNumber);
    }
}
