package com.excmul.auth.oauth2;

import com.excmul.member.domain.Member;
import com.excmul.member.domain.vo.AuthVo;
import com.excmul.member.domain.vo.EmailVo;
import com.excmul.member.domain.vo.PasswordVo;
import com.excmul.member.domain.vo.RoleVo;
import lombok.*;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OAuth2Principal {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private EmailVo email;
    private PasswordVo password;
    private AuthVo auth;


    public static OAuth2Principal of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        return ofKakao(userNameAttributeName, attributes);
    }

    private static OAuth2Principal ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuth2Principal.builder()
                .email(new EmailVo((String) response.get("email")))
                .password(new PasswordVo("!Computer123"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .role(RoleVo.USER)
                .build();
    }


}