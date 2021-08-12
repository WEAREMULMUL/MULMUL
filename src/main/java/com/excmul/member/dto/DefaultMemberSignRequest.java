package com.excmul.member.dto;

import com.excmul.member.domain.*;
import com.excmul.member.domain.vo.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class DefaultMemberSignRequest {

    private EmailVo email;
    private PasswordVo password;
    private NameVo name;
    private NicknameVo nickname;
    private GenderVo gender;
    private BirthVo birth;
    private boolean termService;
    private boolean termPrivacy;
    private boolean termLocation;
    private PhoneNumberVo phoneNumber;

    public Member sign() {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickname)
                .gender(gender)
                .birth(birth)
                .phoneNumber(phoneNumber)
                .termService(termService)
                .termPrivacy(termPrivacy)
                .termLocation(termLocation)
                .auth(AuthVo.DEFAULT)
                .role((RoleVo.USER))
                .build();
    }
}
