package com.excmul.auth;

import com.excmul.member.domain.Auth;
import com.excmul.member.domain.Email;
import com.excmul.member.domain.MemberEntity;
import com.excmul.member.domain.Password;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class AuthPrincipal implements UserDetails {

    private Email email;
    private Password password;
    private Auth auth;

    public AuthPrincipal(MemberEntity member) {
        this.email = member.getEmail();
        this.password = member.getPassword();
        this.auth = member.getAuth();
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return password.getValue();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return email.toString();
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
        collectors.add(auth::getValue);
        return collectors;
    }

}