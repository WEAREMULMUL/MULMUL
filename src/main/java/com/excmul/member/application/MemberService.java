package com.excmul.member.application;

import com.excmul.member.domain.Email;
import com.excmul.member.domain.MemberRepository;
import com.excmul.member.dto.MemberDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    private final MemberRepository userRepository;

    public MemberService(MemberRepository memberRepository) {
        this.userRepository = memberRepository;
    }

    @Transactional
    public void createUser(MemberDto userDto) {
        userRepository.save(userDto.toUserEntity());
    }

    @Transactional(readOnly = true)
    public void existEmail(Email email) {

    }
}
