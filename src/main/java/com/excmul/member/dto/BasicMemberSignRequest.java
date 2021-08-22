package com.excmul.member.dto;

import com.excmul.member.domain.*;
import com.excmul.member.domain.vo.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class BasicMemberSignRequest {

    private EmailVo email;
    private PasswordVo password;
    private NameVo name;
    private NicknameVo nickname;
    private GenderVo gender;
    private BirthVo birth;
    private PhoneNumberVo phoneNumber;
    private boolean termService;
    private boolean termPrivacy;
    private boolean termLocation;

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
                .socialType(SocialType.BASIC)
                .role(RoleVo.USER)
                .build();
    }

}
