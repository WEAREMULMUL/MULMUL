package com.excmul.member.application;

import com.excmul.auth.dto.AuthPrincipal;
import com.excmul.auth.exception.OAuth2Exception;
import com.excmul.common.domain.vo.Token;
import com.excmul.common.exception.TokenException;
import com.excmul.follow.domain.Follow;
import com.excmul.mail.application.MailService;
import com.excmul.mail.domain.Mail;
import com.excmul.member.domain.Member;
import com.excmul.member.domain.MemberRepository;
import com.excmul.member.domain.PasswordChangeToken;
import com.excmul.member.domain.PasswordChangeTokenRepository;
import com.excmul.member.domain.vo.Email;
import com.excmul.member.domain.vo.Nickname;
import com.excmul.member.domain.vo.Password;
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
    private final PasswordChangeTokenRepository changePasswordTokenRepository;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createDefaultMember(MemberSignDto request) {
        memberRepository.save(request.toMember(passwordEncoder));
    }

    @Transactional
    public void updateSocialMemberInfo(long memberId, SocialMemberInformationDto socialMemberInformation) {
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
    public void changeHomePagePassword(Email email, Password password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다 ");
                });
        member.changePassword(password.encode(passwordEncoder));
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
            PasswordChangeToken token = member.newChangePasswordToken();
            changePasswordTokenRepository.save(token);

            Mail mail = token.newMail();
            mailService.send(mail);
        });
        return optionalMember.isPresent();
    }

    @Transactional(readOnly = true)
    public boolean isAvailablePasswordChangeToken(Token token) {
        PasswordChangeToken passwordChangeToken = changePasswordTokenRepository.findByToken(token)
                .orElseThrow(() -> new TokenException(TokenException.ErrorCode.NOT_FOUND));

        return passwordChangeToken.isAvailable();
    }

    @Transactional
    public void changePassword(Token token, Password password) {
        PasswordChangeToken passwordChangeToken = changePasswordTokenRepository.findByTokenWithMember(token)
                .orElseThrow(() -> new TokenException(TokenException.ErrorCode.NOT_FOUND));

        Password encodedPassword = password.encode(passwordEncoder);
        passwordChangeToken.use(encodedPassword);
    }

    @Transactional
    public void edit(String email, MemberInfoEditDto editDto) {
        Member member = memberRepository.findByEmail(new Email(email))
                .orElseThrow(() -> {
                    throw new RuntimeException("해당하는 이메일이 없습니다.");
                });
        member.editMemberInfo(editDto);
    }

    @Transactional(readOnly = true)
    public Member findMemberByEmail(Email email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("에러"));
    }

    @Transactional(readOnly = true)
    public Member findMemberById(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("에러"));
    }


    // TODO follow가 된 상태에서 버튼을 누르면 unfollow, follow가 되어 있지 않은 상태에서 버튼을 누르면 follow
    @Transactional
    public void follow(long fromMemberId, long toMemberId) {
        Member fromMember = findMemberById(fromMemberId);
        Member toMember = findMemberById(toMemberId);

        Follow follow = fromMember.newFollow(toMember);

        boolean status = fromMember.isFollowing(follow);

        if (!status) {
            fromMember.addFromFollow(follow);
            toMember.addToFollow(follow);
        }
    }

    @Transactional
    public void unfollow(long fromMemberId, long toMemberId) {
        Member fromMember = findMemberById(fromMemberId);
        Member toMember = findMemberById(toMemberId);

        Follow follow = fromMember.newFollow(toMember);

        boolean status = fromMember.isFollowing(follow);

        if (status) {
            fromMember.deleteFromFollow(follow);
            toMember.deleteToFollow(follow);
        }
    }

}
