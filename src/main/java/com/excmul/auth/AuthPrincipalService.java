package com.excmul.auth;

import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.Member;
import com.excmul.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthPrincipalService implements UserDetailsService {

    private final String USER_NOT_FOUND_ERROR_MESSAGE = "해당 사용자를 찾을 수 없습니다 ";
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(newInstanceEmail(userEmail))
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException(USER_NOT_FOUND_ERROR_MESSAGE);
                });
        return newInstanceAuthPrincipal(member);
    }

    private EmailVo newInstanceEmail(String userEmail) {
        return new EmailVo(userEmail);
    }

    private AuthPrincipal newInstanceAuthPrincipal(Member member) {
        return new AuthPrincipal(newInstanceLoginMember(member));
    }

    private LoginMember newInstanceLoginMember(Member member) {
        return LoginMember.builder()
                .id(member.getId())
                .email(member.getEmail())
                .auth(member.getAuth())
                .password(member.getPassword())
                .build();
    }

}