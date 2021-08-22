package com.excmul.auth.dto;

import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.vo.NameVo;
import com.excmul.member.domain.vo.SocialType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

@RequiredArgsConstructor
public class GoogleAttributes implements SocialAttributes {
    private static final SocialType SOCIAL_TYPE = SocialType.GOOGLE;

    private final OAuth2User oAuth2User;

    public EmailVo email() {
        return new EmailVo(
                oAuth2User.getAttribute("email")
        );
    }

    public SocialType socialType() {
        return SOCIAL_TYPE;
    }

    @Override
    public String userKey() {
        return oAuth2User.getName();
    }

    @Override
    public NameVo name() {
        return new NameVo(
                oAuth2User.getAttribute("name")
        );
    }
}
