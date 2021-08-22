package com.excmul.member.domain.vo;


import com.excmul.auth.exception.OAuth2Exception;
import com.excmul.auth.dto.GoogleAttributes;
import com.excmul.auth.dto.SocialAttributes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Arrays;
import java.util.function.Function;

@RequiredArgsConstructor
public enum SocialType {
    BASIC("", null),
    GOOGLE("google", GoogleAttributes::new);

    private final String registrationId;
    private final Function<OAuth2User, SocialAttributes> newSocialMemberFunction;

    public SocialAttributes toSocialAttributes(OAuth2User oAuth2User) {
        return newSocialMemberFunction.apply(oAuth2User);
    }

    public static SocialType of(String registrationId) {
        return Arrays.stream(values())
                .filter(iSocialType -> iSocialType.registrationId.equals(registrationId))
                .findFirst()
                .orElseThrow(() -> new OAuth2Exception(OAuth2Exception.ErrorCode.NOT_FOUND_SOCIAL_TYPE));
    }
}