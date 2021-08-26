package com.excmul.auth.dto;

import com.excmul.member.domain.vo.Email;
import com.excmul.member.domain.vo.Name;
import com.excmul.member.domain.vo.SocialType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

@RequiredArgsConstructor
public class GoogleAttributes implements SocialAttributes {
    private static final SocialType SOCIAL_TYPE = SocialType.GOOGLE;

    private final OAuth2User oAuth2User;

    public Email email() {
        return new Email(
                oAuth2User.getAttribute(AttributeKey.EMAIL.key)
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
    public Name name() {
        return new Name(
                oAuth2User.getAttribute(AttributeKey.NAME.key)
        );
    }

    private enum AttributeKey {
        NAME("name"),
        EMAIL("email");

        private final String key;

        AttributeKey(String key) {
            this.key = key;
        }
    }
}
