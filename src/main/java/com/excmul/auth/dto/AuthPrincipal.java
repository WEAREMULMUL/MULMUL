package com.excmul.auth.dto;

import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.vo.PasswordVo;
import com.excmul.member.domain.vo.RoleVo;
import com.excmul.member.domain.vo.SocialType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.transaction.NotSupportedException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Builder
@RequiredArgsConstructor
public class AuthPrincipal implements UserDetails, OAuth2User {
    @Getter private final long id;
    private final EmailVo email;
    private final PasswordVo password;
    @Getter private final RoleVo role;
    @Getter private final SocialType socialType;

    public String getUsername() {
        return email.value();
    }

    public String getPassword() {
        return password.value();
    }

    public EmailVo getWrappingUsername() {
        return email;
    }

    public PasswordVo getWrappingPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                role.grantedAuthority()
        );
    }

    public boolean isSocial() {
        return socialType != SocialType.BASIC;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @SneakyThrows
    @Deprecated
    @Override
    public Map<String, Object> getAttributes() {
        throw new NotSupportedException();
    }

    @SneakyThrows
    @Deprecated
    @Override
    public String getName() {
        throw new NotSupportedException();
    }
}