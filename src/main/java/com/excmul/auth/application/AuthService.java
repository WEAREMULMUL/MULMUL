package com.excmul.auth.application;

import com.excmul.auth.domain.LoginMember;
import com.excmul.auth.dto.TokenRequest;
import com.excmul.auth.dto.TokenResponse;
import com.excmul.auth.infrastructure.JwtTokenProvider;
import com.excmul.member.domain.Member;
import com.excmul.member.domain.MemberRepository;

import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.vo.PasswordVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public TokenResponse login(TokenRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail()).orElseThrow(AuthorizationException::new);
        PasswordVo passwordVo = new PasswordVo(request.getPassword().password());
        passwordVo.checkPassword(request.getPassword());

        String token = jwtTokenProvider.createToken(request.getEmail());
        return new TokenResponse(token);
    }

    public LoginMember findMemberByToken(String credentials) {
        if (!jwtTokenProvider.validateToken(credentials)) {
            return new LoginMember();
        }

        String email = jwtTokenProvider.getPayload(credentials);
        EmailVo emailVo = new EmailVo(email);
        Member member = memberRepository.findByEmail(emailVo).orElseThrow(RuntimeException::new);
        return new LoginMember(member.getId(), member.getEmail(), member.getRole());
    }
}
