package com.excmul.member.application;

import com.excmul.auth.dto.AuthPrincipal;
import com.excmul.auth.exception.OAuth2Exception;
import com.excmul.common.domain.vo.TokenVo;
import com.excmul.common.exception.TokenException;
import com.excmul.mail.application.MailService;
import com.excmul.mail.domain.Mail;
import com.excmul.member.domain.Member;
import com.excmul.member.domain.MemberRepository;
import com.excmul.member.domain.PasswordChangeToken;
import com.excmul.member.domain.PasswordChangeTokenRepository;
import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.vo.NicknameVo;
import com.excmul.member.domain.vo.PasswordVo;
import com.excmul.member.domain.vo.PhoneNumberVo;
import com.excmul.member.dto.*;
import com.excmul.member.exception.DuplicationException;
import com.excmul.member.exception.NotFoundMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.excmul.auth.exception.OAuth2Exception.ErrorCode;

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

    @Transactional
    public void updateSocialMemberInfo(long memberId, SocialMemberInformation socialMemberInformation) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundMemberException::new);

        if (!member.isNotCompletedSingUp()) {
            throw new OAuth2Exception(ErrorCode.ALREADY_COMPLETED_SIGN_UP);
        }
        validateDuplicateMemberPrivacy(
                socialMemberInformation.getNickname(),
                socialMemberInformation.getPhoneNumber()
        );

        member.updateSocialMemberInfo(socialMemberInformation);
    }

    private void validateDuplicateMemberPrivacy(NicknameVo nickname, PhoneNumberVo phoneNumber) {
        if (existsByNickname(nickname)) {
            throw new DuplicationException(DuplicationException.ErrorCode.DUPLICATION_NICKNAME);
        }
        if (existsByPhoneNumber(phoneNumber)) {
            throw new DuplicationException(DuplicationException.ErrorCode.DUPLICATION_PHONE_NUMBER);
        }
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

    @Transactional
    public void changeHomePagePassword(AuthPrincipal authPrincipal, MemberChangePasswordRequest request) {
        request.validExistsPassword(passwordEncoder, authPrincipal.getWrappingPassword());
        request.validAfterChangePasswords();
        request.validIsDifferentPassword();

        Member member = memberRepository.findByEmail(authPrincipal.getWrappingUsername())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다 ");
                });
        member.changePassword(request.changePassword(passwordEncoder));
    }

    @Transactional(readOnly = true)
    public Optional<EmailVo> inquiryId(MemberPrivacyDto memberPrivacyDto) {
        return memberRepository.findEmailByPrivacy(
                memberPrivacyDto.getName(), memberPrivacyDto.getBirth(), memberPrivacyDto.getPhoneNumber()
        );
    }

    @Transactional
    public boolean inquiryPw(EmailVo memberEmail, MemberPrivacyDto memberPrivacyDto) {
        Optional<Member> optionalMember = memberRepository.findByEmailAndPrivacy(
                memberEmail, memberPrivacyDto.getName(), memberPrivacyDto.getBirth(), memberPrivacyDto.getPhoneNumber()
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

    @Transactional
    public void edit(String email, EditDto editDto) {
        Member member = memberRepository.findByEmail(new EmailVo(email))
                .orElseThrow(() -> {
                    throw new RuntimeException("해당하는 이메일이 없습니다.");
                });
        member.editMemberInfo(editDto);
    }
}
