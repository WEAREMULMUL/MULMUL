package com.excmul.member.dto;

import com.excmul.member.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DefaultMemberSignRequest {

    private Email email;
    private Password password;
    private Name name;
    private Nickname nickname;
    private Gender gender;
    private Birth birth;
    private Terms terms;
    private PhoneNumber phoneNumber;

    public MemberEntity sign() {
        return MemberEntity.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickname)
                .gender(gender)
                .birth(birth)
                .phoneNumber(phoneNumber)
                .terms(terms)
                .auth(Auth.DEFAULT)
                .role((Role.USER))
                .build();
    }

}
