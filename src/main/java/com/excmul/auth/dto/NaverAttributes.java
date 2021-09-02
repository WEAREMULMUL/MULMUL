package com.excmul.auth.dto;

import com.excmul.member.domain.vo.Email;
import com.excmul.member.domain.vo.Name;
import com.excmul.member.domain.vo.SocialType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

@RequiredArgsConstructor
public class NaverAttributes implements SocialAttributes {
    private static final SocialType SOCIAL_TYPE = SocialType.NAVER;

    private final OAuth2User oAuth2User;

    @Override
    public SocialType socialType() {
        return SOCIAL_TYPE;
    }

    @Override
    public String userKey() {
        return response(
                AttributeKey.ID.key
        );
    }

    @Override
    public Name name() {
        return new Name(
                response(
                        AttributeKey.NAME.key
                )
        );
    }

    @Override
    public Email email() {
        return new Email(
                response(
                        AttributeKey.EMAIL.key
                )
        );
    }

    private String response(String key) {
        Map<String, String> response =
                this.oAuth2User
                        .getAttribute(
                                AttributeKey.RESPONSE.key
                        );
        return response.get(key);
    }

    private enum AttributeKey {
        RESPONSE("response"),
        ID("id"),
        NAME("name"),
        EMAIL("email");

        private final String key;

        AttributeKey(String key) {
            this.key = key;
        }
    }
}
