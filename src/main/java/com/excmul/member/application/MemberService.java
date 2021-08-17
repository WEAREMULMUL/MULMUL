package com.excmul.member.application;

import com.excmul.member.domain.ChangePasswordToken;
import com.excmul.member.domain.ChangePasswordTokenRepository;
import com.excmul.member.domain.Member;
import com.excmul.member.domain.vo.*;
import com.excmul.member.domain.MemberRepository;
import com.excmul.member.dto.MemberSignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.Name;
import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final ChangePasswordTokenRepository changePasswordTokenRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createDefaultMember(MemberSignRequest request) {
        memberRepository.save(request.sign(passwordEncoder));
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

    @Transactional(readOnly = true)
    public Optional<EmailVo> inquiryId(NameVo name, BirthVo birth, PhoneNumberVo phoneNumber) {
        return memberRepository.findByNameAndBirthAndPhoneNumber(
                name, birth, phoneNumber
        ).map(Member::getEmail);
    }

    @Transactional
    public boolean inquiryPw(EmailVo email, NameVo name, BirthVo birth, PhoneNumberVo phoneNumber) {
        Optional<Member> optionalMember = memberRepository.findByEmailAndNameAndBirthAndPhoneNumber(
                email, name, birth, phoneNumber
        );
        optionalMember.ifPresent(member ->
            changePasswordTokenRepository.save(
                    ChangePasswordToken.newInstance(member)
            )
                // TODO :: 메일 전송
        );
        return optionalMember.isPresent();
    }
}
