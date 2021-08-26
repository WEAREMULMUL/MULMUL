package com.excmul.auth.dto;

import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.vo.NameVo;
import com.excmul.member.domain.vo.SocialType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

@RequiredArgsConstructor
public class KakaoAttributes implements SocialAttributes {
    private static final SocialType SOCIAL_TYPE = SocialType.KAKAO;

    private final OAuth2User oAuth2User;

    @Override
    public SocialType socialType() {
        return SOCIAL_TYPE;
    }

    @Override
    public String userKey() {
        return oAuth2User.getName();
    }

    @Override
    public NameVo name() {
        //noinspection unchecked
        Map<String, Object> profile = (Map<String, Object>) account(AttributeKey.PROFILE.key);
        return new NameVo(
                profile.get(
                        AttributeKey.NICKNAME.key
                ).toString()
        );
    }

    @Override
    public EmailVo email() {
        return new EmailVo(
                account(
                        AttributeKey.EMAIL.key
                ).toString()
        );
    }

    private Object account(String key) {
        Map<String, Object> account = oAuth2User.getAttribute(
                AttributeKey.KAKAO_ACCOUNT.key
        );
        //noinspection ConstantConditions
        return account.get(key);
    }

    private enum AttributeKey {
        KAKAO_ACCOUNT("kakao_account"),
        EMAIL("email"),
        PROFILE("profile"),
        NICKNAME("nickname");

        private final String key;

        AttributeKey(String key) {
            this.key = key;
        }
    }
}
