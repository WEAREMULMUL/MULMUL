package com.excmul.member.dto;

import com.excmul.member.domain.*;
import com.excmul.member.domain.vo.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Setter
@Getter
@NoArgsConstructor
public class MemberSignRequest {

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

    public Member sign(PasswordEncoder passwordEncoder) {
        PasswordVo encodedPassword = password.encode(passwordEncoder);

        return Member.builder()
                .email(email)
                .name(name)
                .password(encodedPassword)
                .nickname(nickname)
                .gender(gender)
                .birth(birth)
                .phoneNumber(phoneNumber)
                .termService(termService)
                .termPrivacy(termPrivacy)
                .termLocation(termLocation)
                .auth(AuthVo.BASIC)
                .role(RoleVo.USER)
                .build();
    }
}
