package com.excmul.auth;


import com.excmul.member.domain.Auth;
import com.excmul.member.domain.Email;
import com.excmul.member.domain.MemberEntity;
import com.excmul.member.domain.Password;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Getter
public class AuthPrincipal implements UserDetails {

    private MemberEntity memberEntity;

    public AuthPrincipal(MemberEntity memberEntity) {
        this.memberEntity = memberEntity;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return memberEntity.getPassword().getValue();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return memberEntity.getEmail().toString();
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
        collectors.add(memberEntity.getAuth()::getValue);
        return collectors;
    }

}