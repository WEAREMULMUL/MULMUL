package com.excmul.auth;

import com.excmul.member.domain.vo.PasswordVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class AuthPrincipal implements UserDetails {

    private final LoginMember loginMember;

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return loginMember.password().value();
    }

    public LoginMember loginMember() {
        return loginMember;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return loginMember.email().toString();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(loginMember.auth()::value);
        return collectors;
    }

}