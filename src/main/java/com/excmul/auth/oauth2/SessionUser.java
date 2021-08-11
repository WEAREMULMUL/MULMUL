package com.excmul.auth.oauth2;

import com.excmul.member.domain.vo.AuthVo;
import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.vo.PasswordVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class SessionUser implements Serializable {
    private int id;
    private EmailVo email;
    private PasswordVo password;
    private AuthVo auth;

    public SessionUser(User user) {
    }


    public int id() {
        return id;
    }

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
