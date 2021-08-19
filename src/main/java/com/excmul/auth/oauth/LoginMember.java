package com.excmul.auth;

import com.excmul.member.domain.vo.AuthVo;
import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.vo.PasswordVo;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class LoginMember {
    private final long id;
    private final EmailVo email;
    private final PasswordVo password;
    private final AuthVo auth;

    public EmailVo email() {
        return email;
    }

    public PasswordVo password() {
        return password;
    }

    public AuthVo auth() {
        return auth;
    }
}