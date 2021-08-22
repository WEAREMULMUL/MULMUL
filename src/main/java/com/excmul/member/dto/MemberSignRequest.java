package com.excmul.member.dto;

import com.excmul.member.domain.*;
import com.excmul.member.domain.vo.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Setter
@Getter
@NoArgsConstructor
public class MemberSignRequest {

    @NonNull
    private EmailVo email;
    @NonNull
    private PasswordVo password;
    @NonNull
    private NameVo name;
    @NonNull
    private NicknameVo nickname;
    @NonNull
    private GenderVo gender;
    @NonNull
    private BirthVo birth;
    @NonNull
    private PhoneNumberVo phoneNumber;
    @NonNull
    private boolean termService;
    @NonNull
    private boolean termPrivacy;
    @NonNull
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
                .socialType(SocialType.BASIC)
                .role(RoleVo.USER)
                .build();
    }
}
