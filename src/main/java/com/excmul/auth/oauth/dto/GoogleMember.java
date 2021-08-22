package com.excmul.auth.oauth.dto;

import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.vo.NameVo;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class GoogleMember extends SocialMember {
    protected static final SocialType socialType = SocialType.GOOGLE;

    public GoogleMember(final OAuth2User oAuth2User) {
        super(oAuth2User, socialType);
    }

    @Override
    public EmailVo getEmail() {
        return new EmailVo(
                oAuth2User.getAttribute("email")
        );
    }

    @Override
    public NameVo getUserName() {
        return new NameVo(
                oAuth2User.getAttribute("name")
        );
    }

    @Override
    public String getPicture() {
        return oAuth2User.getAttribute("picture");
    }
}
