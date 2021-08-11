package com.excmul.member.application;

import com.excmul.member.domain.MemberRepository;
import com.excmul.member.dto.DefaultMemberSignRequest;
import com.excmul.member.exception.MemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.excmul.member.exception.MemberExceptionMessage.*;

@Service
@RequiredArgsConstructor
public class MemberSignService {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @Transactional
    public void createDefaultMember(DefaultMemberSignRequest request) {
        checkDuplicateMember(request);
        memberRepository.save(request.sign());
    }

    private void checkDuplicateMember(DefaultMemberSignRequest request) {
        if (memberService.existsByEmail(request.email())) {
            throw new MemberException(DUPLICATION_EMAIL);
        }

        if (memberService.existsByNickname(request.nickname())) {
            throw new MemberException(DUPLICATION_NICKNAME);
        }

        if (memberService.existsByPhoneNumber(request.phoneNumber())) {
            throw new MemberException(DUPLICATION_PHONE_NUMBER);
        }
    }

}
