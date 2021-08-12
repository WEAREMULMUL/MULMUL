package com.excmul.auth.OAuth2;

import com.excmul.member.domain.Member;
import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.vo.NameVo;
import com.excmul.member.domain.vo.NicknameVo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class OAuthAttribute {
    private final Map<String, Object> attributes;
    private final String nameAttributeKey;
    private final NicknameVo nickname;
    private final EmailVo email;

    public static OAuthAttribute of(String registerationId, String userNameAttributeName, Map<String, Object> attributes) {

        /*
        if(registerationId.equals("kakao")){
                return ofKaKao(userNameAttributeName, attributes);
           }
         */
        return ofKaKao(userNameAttributeName, attributes);


    }

    private static OAuthAttribute ofKaKao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");

        return new OAuthAttribute(
                attributes,
                userNameAttributeName,
                new NicknameVo((String) profile.get("nickname")),
                new EmailVo((String) kakao_account.get("email"))
        );
    }

    private Member toEntity() {
        return Member.builder()
                .nickname(nickname)
                .email(email)
                .build();
    }

}
