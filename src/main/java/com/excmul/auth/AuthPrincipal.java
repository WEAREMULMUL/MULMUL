package com.excmul.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


public class AuthPrincipal implements UserDetails {

    private LoginMember loginMember;

    public AuthPrincipal(LoginMember loginMember) {
        this.loginMember = loginMember;
    }


    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return loginMember.getPassword().getValue();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return loginMember.getEmail().toString();
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
        collectors.add(loginMember.getAuth()::getValue);
        return collectors;
    }

}