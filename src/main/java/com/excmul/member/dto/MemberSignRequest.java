package com.excmul.member.dto;

import com.excmul.member.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberSignRequest {

    private Email email;
    private Password password;
    private Name username;
    private Nickname nickname;
    private Gender gender;
    private Birth birth;
    private PhoneNumber phoneNumber;
    //   private Terms terms;

    /**
     * @return
     */
    public MemberEntity sign() {
        return MemberEntity.builder()
                .email(email)
                .password(password)
                .username(username)
                .role((Role.USER))
                .nickname(nickname)
                .gender(gender)
                .birth(birth)
                .phoneNumber(phoneNumber)
                //    .terms(terms)
                .auth(Auth.DEFAULT)
                .build();
    }

}
