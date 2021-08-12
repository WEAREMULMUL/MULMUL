package com.excmul.member.application;

import com.excmul.member.domain.MemberRepository;
import com.excmul.member.dto.BasicMemberSignRequest;
import com.excmul.member.exception.DuplicationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MemberSignService {
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @Transactional
    public void createDefaultMember(BasicMemberSignRequest request) {
        checkDuplicateDefaultMember(request);
        memberRepository.save(request.sign());
    }

    private void checkDuplicateDefaultMember(BasicMemberSignRequest request) {
        if (memberService.existsByEmail(request.getEmail())) {
            throw new DuplicationException(DuplicationException.ErrorCode.DUPLICATION_EMAIL);
        }

        if (memberService.existsByNickname(request.getNickname())) {
            throw new DuplicationException(DuplicationException.ErrorCode.DUPLICATION_NICKNAME);
        }

        if (memberService.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new DuplicationException(DuplicationException.ErrorCode.DUPLICATION_PHONE_NUMBER);
        }
    }

}
