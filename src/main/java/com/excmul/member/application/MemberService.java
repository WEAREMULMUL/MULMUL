package com.excmul.member.application;

import com.excmul.auth.LoginMember;
import com.excmul.member.domain.Member;
import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.MemberRepository;
import com.excmul.member.domain.vo.NicknameVo;
import com.excmul.member.domain.vo.PasswordVo;
import com.excmul.member.domain.vo.PhoneNumberVo;
import com.excmul.member.dto.MemberChangePasswordRequest;
import com.excmul.member.dto.MemberSignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createDefaultMember(MemberSignRequest request) {
<<<<<<< HEAD
        memberRepository.save(request.sign(passwordEncoder));
=======
        Member signMember = request.sign(passwordEncoder);

        memberRepository.save(signMember);
>>>>>>> 45411320c0ee2c79afa90449b2f191576f96034f
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
    public void changePassword(LoginMember loginMember, MemberChangePasswordRequest request) {
        request.validExistsPassword(passwordEncoder, loginMember.password());
        request.validAfterChangePasswords();
        request.validIsDifferentPassword();

        Member member = memberRepository.findByEmail(loginMember.email())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다 ");
                });
        member.changePassword(request.changePassword(passwordEncoder));
    }

}
