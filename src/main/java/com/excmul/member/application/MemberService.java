package com.excmul.member.application;

import com.excmul.common.domain.vo.TokenVo;
import com.excmul.common.exception.TokenException;
import com.excmul.mail.application.MailService;
import com.excmul.mail.domain.Mail;
import com.excmul.member.domain.PasswordChangeToken;
import com.excmul.member.domain.PasswordChangeTokenRepository;
import com.excmul.member.domain.Member;
import com.excmul.member.domain.vo.*;
import com.excmul.member.domain.MemberRepository;
import com.excmul.member.dto.MemberSignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordChangeTokenRepository changePasswordTokenRepository;

    private final MailService mailService;

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
        return memberRepository.findEmailByPrivacy(
                name, birth, phoneNumber
        );
    }

    @Transactional
    public boolean inquiryPw(EmailVo email, NameVo name, BirthVo birth, PhoneNumberVo phoneNumber) {
        Optional<Member> optionalMember = memberRepository.findByPrivacy(
                email, name, birth, phoneNumber
        );
        optionalMember.ifPresent(member -> {
            PasswordChangeToken token = member.newChangePasswordToken();
            changePasswordTokenRepository.save(token);

            Mail mail = token.newMail();
            mailService.send(mail);
        });
        return optionalMember.isPresent();
    }

    @Transactional(readOnly = true)
    public boolean isAvailablePasswordChangeToken(TokenVo token) {
        PasswordChangeToken passwordChangeToken = changePasswordTokenRepository.findByToken(token)
                .orElseThrow(() -> new TokenException(TokenException.ErrorCode.NOT_FOUND));

        return passwordChangeToken.isAvailable();
    }

    @Transactional
    public void changePassword(TokenVo token, PasswordVo password) {
        PasswordChangeToken passwordChangeToken = changePasswordTokenRepository.findByTokenWithMember(token)
                .orElseThrow(() -> new TokenException(TokenException.ErrorCode.NOT_FOUND));

        PasswordVo encodedPassword = password.encode(passwordEncoder);
        passwordChangeToken.use(encodedPassword);
    }
}
