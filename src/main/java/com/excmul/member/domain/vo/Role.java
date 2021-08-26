package com.excmul.member.domain.vo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@RequiredArgsConstructor
public enum Role {
    GUEST(new SimpleGrantedAuthority("GUEST")),
    USER(new SimpleGrantedAuthority("USER")),
    ADMIN(new SimpleGrantedAuthority("ADMIN"));

    private final GrantedAuthority grantedAuthority;

    public GrantedAuthority grantedAuthority() {
        return grantedAuthority;
    }
}