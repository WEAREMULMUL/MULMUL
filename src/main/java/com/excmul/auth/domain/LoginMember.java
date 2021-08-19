package com.excmul.auth.domain;

import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.vo.RoleVo;
import lombok.Getter;

@Getter
public class LoginMember {
    private int id;
    private EmailVo email;
    private RoleVo role;

    public LoginMember() {
    }

    public LoginMember(int id, EmailVo email, RoleVo role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }
}
