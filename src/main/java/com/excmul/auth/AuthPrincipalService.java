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
    
    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        EmailVo email = new EmailVo(userEmail);
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다 ");
                });
        return newAuthPrincipal(member);
    }

    private AuthPrincipal newAuthPrincipal(Member member) {
        return new AuthPrincipal(member.newLoginMember());
    }

}