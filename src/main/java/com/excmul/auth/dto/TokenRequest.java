package com.excmul.auth.dto;

import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.vo.PasswordVo;
import lombok.Getter;

@Getter
public class TokenRequest {
    private EmailVo email;
    private PasswordVo password;

    public TokenRequest() {
    }

    public TokenRequest(EmailVo email, PasswordVo password) {
        this.email = email;
        this.password = password;
    }

}
