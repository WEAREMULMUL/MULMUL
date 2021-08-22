package com.excmul.auth.oauth.dto;

import com.excmul.member.domain.Member;
import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.vo.NameVo;
import com.excmul.member.domain.vo.RoleVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
public abstract class SocialMember implements OAuth2User {
    private static final RoleVo DEFAULT_USER_ROLE = RoleVo.USER;

    protected final OAuth2User oAuth2User;
    private final SocialType socialType;

    public final String getName() {
        return oAuth2User.getName();
    }

    public RoleVo getUserRole() {
        return DEFAULT_USER_ROLE;
    }

    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(
                DEFAULT_USER_ROLE.grantedAuthority()
        );
    }

    public abstract EmailVo getEmail();

    public abstract NameVo getUserName();

    public abstract String getPicture();

    public Member toMember() {
        return Member.builder()
                .socialMemberKey(getName())
                .name(getUserName())
                .email(getEmail())
                .build();
    }
}
