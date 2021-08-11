package com.excmul.member.dto;

import com.excmul.member.domain.*;
import com.excmul.member.domain.vo.*;
import lombok.*;

@Builder
@AllArgsConstructor
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

    public EmailVo email() {
        return email;
    }

    public PasswordVo password() {
        return password;
    }

    public NameVo name() {
        return name;
    }

    public NicknameVo nickname() {
        return nickname;
    }

    public GenderVo gender() {
        return gender;
    }

    public BirthVo birth() {
        return birth;
    }

    public boolean termService() {
        return termService;
    }

    public boolean termPrivacy() {
        return termPrivacy;
    }

    public boolean termLocation() {
        return termLocation;
    }

    public PhoneNumberVo phoneNumber() {
        return phoneNumber;
    }
}
