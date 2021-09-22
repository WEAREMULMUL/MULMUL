package com.excmul.member.application;

import com.excmul.auth.dto.AuthPrincipal;
import com.excmul.auth.exception.OAuth2Exception;
import com.excmul.mail.application.MailService;
import com.excmul.mail.domain.Mail;
import com.excmul.member.domain.Member;
import com.excmul.member.domain.MemberRepository;
import com.excmul.member.domain.PasswordChangeToken;
import com.excmul.member.domain.vo.Email;
import com.excmul.member.domain.vo.Nickname;
import com.excmul.member.domain.vo.PhoneNumber;
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
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    private Member findById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(NotFoundMemberException::new);
    }

    @Transactional
    public void createDefaultMember(MemberSignDto request) {
        memberRepository.save(request.toMember(passwordEncoder));
    }

    @Transactional
    public void updateSocialMemberInfo(long memberId, SocialMemberInformationDto socialMemberInformation) {
        Member member = findById(memberId);

        if (!member.isNotCompletedSingUp()) {
            throw new OAuth2Exception(ErrorCode.ALREADY_COMPLETED_SIGN_UP);
        }
        validateDuplicateMemberPrivacy(
                socialMemberInformation.getNickname(),
                socialMemberInformation.getPhoneNumber()
        );

        member.updateSocialMemberInfo(socialMemberInformation);
    }

    private void validateDuplicateMemberPrivacy(Nickname nickname, PhoneNumber phoneNumber) {
        if (existsByNickname(nickname)) {
            throw new DuplicationException(DuplicationException.ErrorCode.DUPLICATION_NICKNAME);
        }
        if (existsByPhoneNumber(phoneNumber)) {
            throw new DuplicationException(DuplicationException.ErrorCode.DUPLICATION_PHONE_NUMBER);
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

    @Transactional
    public void changeHomePagePassword(AuthPrincipal authPrincipal, MemberChangePasswordDto request) {
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
    public Optional<Email> inquiryId(MemberPrivacyDto memberPrivacyDto) {
        return memberRepository.findEmailByPrivacy(
                memberPrivacyDto.getName(), memberPrivacyDto.getBirth(), memberPrivacyDto.getPhoneNumber()
        );
    }

    @Transactional
    public boolean inquiryPw(Email memberEmail, MemberPrivacyDto memberPrivacyDto) {
        Optional<Member> optionalMember = memberRepository.findByEmailAndPrivacy(
                memberEmail, memberPrivacyDto.getName(), memberPrivacyDto.getBirth(), memberPrivacyDto.getPhoneNumber()
        );
        optionalMember.ifPresent(member -> {
            PasswordChangeToken token = member.makePasswordChangeToken();

            Mail mail = token.newMail();
            mailService.send(mail);
        });
        return optionalMember.isPresent();
    }

    @Transactional
    public void edit(String email, MemberInfoEditDto editDto) {
        Member member = memberRepository.findByEmail(new Email(email))
                .orElseThrow(() -> {
                    throw new RuntimeException("해당하는 이메일이 없습니다.");
                });
        member.editMemberInfo(editDto);
    }

    @Transactional
    public void leaveId(long memberId) {
        Member member = findById(memberId);

        member.leave();
    }

    public String getProfileUrl(long id) {
        Member member = findById(id);
        return member.getProfileUrl();
    }

    @Transactional
    public void updateProfileUrl(Long MemberId, String profileUrl) {
        Member member = findById(MemberId);
        member.updateProfileUrl(profileUrl);
    }
}
