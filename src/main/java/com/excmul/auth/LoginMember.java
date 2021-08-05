package com.excmul.auth;

import com.excmul.member.domain.Auth;
import com.excmul.member.domain.Email;
import com.excmul.member.domain.Password;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Login Member를 물고 있으려구요
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginMember {
    private int id;
    private Email email;
    private Password password;
    private Auth auth;

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Auth getAuth() {
        return auth;
    }
}