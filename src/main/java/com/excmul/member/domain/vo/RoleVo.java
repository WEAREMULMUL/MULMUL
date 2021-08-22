package com.excmul.member.domain.vo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@RequiredArgsConstructor
public enum RoleVo {
    GUEST("손님", new SimpleGrantedAuthority("GUEST")),
    USER("사용자", new SimpleGrantedAuthority("USER")),
    ADMIN("관리자", new SimpleGrantedAuthority("ADMIN"));

    private final String role;
    private final GrantedAuthority grantedAuthority;

    public GrantedAuthority grantedAuthority() {
        return grantedAuthority;
    }
}