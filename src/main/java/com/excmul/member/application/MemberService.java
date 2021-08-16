package com.excmul.member.application;

import com.excmul.member.domain.Member;
import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.MemberRepository;
import com.excmul.member.domain.vo.NicknameVo;
import com.excmul.member.domain.vo.PasswordVo;
import com.excmul.member.domain.vo.PhoneNumberVo;
import com.excmul.member.dto.MemberSignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void createDefaultMember(MemberSignRequest request) {
        memberRepository.save(request.sign());
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(EmailVo email) {
        return memberRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public boolean existsByNickname(NicknameVo nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    @Transactional(readOnly = true)
    public boolean existsByPhoneNumber(PhoneNumberVo phoneNumber) {
        return memberRepository.existsByPhoneNumber(phoneNumber);
    }
}
