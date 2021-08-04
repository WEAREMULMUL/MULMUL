package com.excmul.auth;


import com.excmul.member.domain.MemberEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class AuthPrincipal implements UserDetails {

    private final String DEFAULT_ROLE = "USER";

    private final MemberEntity memberEntity;

    public AuthPrincipal(MemberEntity member) {
        this.memberEntity = member;
    }

    public MemberEntity getUser() {
        return memberEntity;
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

    /**
     * IS Not Service
     * Just Default USER
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(() -> {
            return DEFAULT_ROLE;
        });
        return collectors;
    }

}