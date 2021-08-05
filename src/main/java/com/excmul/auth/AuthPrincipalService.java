package com.excmul.auth;

import com.excmul.member.domain.Email;
import com.excmul.member.domain.MemberEntity;
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberEntity memberEntity = memberRepository.findByEmail(getEmail(email)).orElseThrow(() -> {
            throw new UsernameNotFoundException(USER_NOT_FOUND_ERROR_MESSAGE);
        });
        return getAuthPrincipal(memberEntity);
    }

    private AuthPrincipal getAuthPrincipal(MemberEntity memberEntity) {
        return new AuthPrincipal(loginMember(memberEntity));
    }


    private LoginMember loginMember(MemberEntity memberEntity) {
        return LoginMember.builder()
                .id(memberEntity.getId())
                .email(memberEntity.getEmail())
                .auth(memberEntity.getAuth())
                .password(memberEntity.getPassword())
                .build();
    }

    private Email getEmail(String email) {
        return new Email(email);
    }
}